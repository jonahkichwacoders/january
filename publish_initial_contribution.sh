#!/bin/bash

set -e

ROOT=/scratch/january/git 
PROJECTS="dawnsci ice eavp"

# All done, time to zip and send it
cd $ROOT/january
git archive --output=january.zip HEAD

read -p "Push tagged version to jonah's github (you may want to look at january.zip before you answer)? [y,n] " -n 1 -r
echo    # (optional) move to a new line
if [[ $REPLY =~ ^[Yy]$ ]]
then
  TAG="january_initial_contribution_`date -u +%Y%m%d%H%M%S`"
  for PROJ in $PROJECTS
  do
    cd $ROOT/$PROJ
    git tag -a $TAG -m"January Initial Contribution"
    git push jonah $TAG
  done

  cd $ROOT/january
  git tag -a $TAG -m"January Initial Contribution"
  git push jonah $TAG
  
fi

