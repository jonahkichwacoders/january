#!/bin/bash

set -e

ROOT=/scratch/january/git
PROJECTS="dawnsci ice eavp"

# check clean
[[ ! -e $ROOT/january ]] || (echo "january should not exist" && exit 1)

for PROJ in $PROJECTS
do
  cd $ROOT/$PROJ
  [[ -z $(git status -s) ]] || (echo "$PROJ should be clean" && exit 1)
 
  [[ ! -e $ROOT/$PROJ.rewrite ]] || (echo "$PROJ.rewrite should not exist" && exit 1)
done

# Clone existing january
cd $ROOT
git clone git@github.com:eclipse/january.git january
cd $ROOT/january
git remote add jonah git@github.com:jonahkichwacoders/january.git
git fetch jonah

for PROJ in $PROJECTS
do
  # prepare a place to rewrite
  cd $ROOT
  git clone $ROOT/$PROJ $ROOT/$PROJ.rewrite
  cd $ROOT/$PROJ.rewrite

  # Generate everyname that the given files have ever had
  find org.eclipse.january* -type f | while read f
  do
    echo "fetching all previous names for file $f" > /dev/null 1>&2
    git log --follow --name-only --pretty=format: -- "$f" | cat
  done | sort -u > $ROOT/$PROJ.files
  grep -q " " $ROOT/$PROJ.files && echo "TODO support spaces in filename" && exit 1

  # Clean up all history we don't care about
  git filter-branch --index-filter "git rm --cached -qr --ignore-unmatch -- . && git reset -q \$GIT_COMMIT -- `tr '\n' ' ' < $ROOT/$PROJ.files`" --prune-empty -- --all

  # At this point we are still left with lots of useless merge commits, remove them
  # (Doing this step reduces total number of commits from ~1200 to ~700 across all January)
  rm -r .git/refs/original
  git filter-branch --prune-empty --parent-filter 'sed "s/-p //g" | xargs -r git show-branch --independent | sed "s/\</-p /g"'

  # At this point we are left with a few files that were previously identified as ancestors of January files, 
  # but still exist at HEAD, delete them now
  shopt -s extglob
  git rm -r `find !(org.eclipse.january*) -maxdepth 0 -type d `
  shopt -u extglob
  git commit -m "
Remove non-January files that were ancestors of January files

The filtering of the branches found these files were ancestors
of January files, but they continued to exist after the
January file was created. This is possibly as a result of
a copy rather than a move.
"

  cd $ROOT/january
  git remote add $PROJ $ROOT/$PROJ.rewrite
  git fetch $PROJ HEAD
  git checkout -b $PROJ FETCH_HEAD
  git checkout master
  git merge --allow-unrelated-histories $PROJ -m"Add $PROJ to January"

done


#########################
# Useful commands

# Cleanup previous run
#   rm -rf january/ *.rewrite *.files

# Check all file types to make sure nothing unexpected:
#   find org.eclipse.january* -type f | file --files-from - | awk -F: '{ print $2 ":" $1}' | sed '-es,^ *,,g' | sort
# Add in to get rid of CRLFs
#  | grep CRLF | cut -f2 -d: | xargs dos2unix
