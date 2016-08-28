/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     UT-Battelle, LLC. - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.january.geometry.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.GeometryPackage;
import org.eclipse.january.geometry.Triangle;
import org.eclipse.january.geometry.Vertex;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Triangle</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.january.geometry.impl.TriangleImpl#getNormal
 * <em>Normal</em>}</li>
 * <li>{@link org.eclipse.january.geometry.impl.TriangleImpl#getVertices
 * <em>Vertices</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TriangleImpl extends MinimalEObjectImpl.Container
		implements Triangle {
	/**
	 * The cached value of the '{@link #getNormal() <em>Normal</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNormal()
	 * @generated
	 * @ordered
	 */
	protected Vertex normal;

	/**
	 * The cached value of the '{@link #getVertices() <em>Vertices</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVertices()
	 * @generated
	 * @ordered
	 */
	protected EList<Vertex> vertices;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected TriangleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeometryPackage.Literals.TRIANGLE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Vertex getNormal() {
		if (normal != null) {
			return normal;
		} else {
			normal = GeometryFactory.eINSTANCE.createVertex();
			return normal;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetNormal(Vertex newNormal,
			NotificationChain msgs) {
		Vertex oldNormal = normal;
		normal = newNormal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, GeometryPackage.TRIANGLE__NORMAL,
					oldNormal, newNormal);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setNormal(Vertex newNormal) {
		if (newNormal != normal) {
			NotificationChain msgs = null;
			if (normal != null)
				msgs = ((InternalEObject) normal)
						.eInverseRemove(this,
								EOPPOSITE_FEATURE_BASE
										- GeometryPackage.TRIANGLE__NORMAL,
								null, msgs);
			if (newNormal != null)
				msgs = ((InternalEObject) newNormal)
						.eInverseAdd(this,
								EOPPOSITE_FEATURE_BASE
										- GeometryPackage.TRIANGLE__NORMAL,
								null, msgs);
			msgs = basicSetNormal(newNormal, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeometryPackage.TRIANGLE__NORMAL, newNormal, newNormal));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Vertex> getVertices() {
		if (vertices == null) {
			vertices = new EObjectContainmentEList<Vertex>(Vertex.class, this,
					GeometryPackage.TRIANGLE__VERTICES);
		}
		return vertices;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean equals(Object otherObject) {

		// To be equal, the other object must be a triangle
		if (otherObject instanceof Triangle) {
			Triangle otherTriangle = (Triangle) otherObject;

			// Check that the normal vectors are equal
			if (normal != null) {
				if (normal.equals(otherTriangle.getNormal())) {

					// Get the other triangle's vertices
					EList<Vertex> otherVertices = otherTriangle.getVertices();

					// The triangles must both be either initialized (ie having
					// three vertices) or not to be equal
					if (getVertices().size() == otherVertices.size()) {

						// If any vertex is in one list but not the other, the
						// triangles are not equal
						for (Vertex vertex : vertices) {
							if (!otherVertices.contains(vertex)) {
								return false;
							}
						}

						// All tests passed, the triangles are equal
						return true;
					}

				}
			} else {
				Vertex otherNormal = otherTriangle.getNormal();

				// If both normals are null, the triangles are equal
				if (otherNormal == null) {
					return true;
				}
			}
		}
		// One of the tests failed, so the triangles are not equal
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public int hashCode() {

		// Add the normal's hash code
		int hash = 31;

		if (normal != null) {
			hash = hash * 31 + normal.hashCode();
		}

		// The list of hash codes from the vertices
		ArrayList<Integer> vertexHashes = new ArrayList<Integer>();

		// Get each vertex's hash code
		for (Vertex vertex : getVertices()) {
			vertexHashes.add(vertex.hashCode());
		}

		// We must reorder the hashes so that two triangles will have the same
		// hash code if they have the same vertices, regardless of order in the
		// list
		Collections.sort(vertexHashes);

		// Add each vertex's hash, multiplying by 31 each time.
		for (Integer vHash : vertexHashes) {
			hash = hash * 31 + vHash;
		}

		return hash;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GeometryPackage.TRIANGLE__NORMAL:
			return basicSetNormal(null, msgs);
		case GeometryPackage.TRIANGLE__VERTICES:
			return ((InternalEList<?>) getVertices()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GeometryPackage.TRIANGLE__NORMAL:
			return getNormal();
		case GeometryPackage.TRIANGLE__VERTICES:
			return getVertices();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GeometryPackage.TRIANGLE__NORMAL:
			setNormal((Vertex) newValue);
			return;
		case GeometryPackage.TRIANGLE__VERTICES:
			getVertices().clear();
			getVertices().addAll((Collection<? extends Vertex>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GeometryPackage.TRIANGLE__NORMAL:
			setNormal((Vertex) null);
			return;
		case GeometryPackage.TRIANGLE__VERTICES:
			getVertices().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GeometryPackage.TRIANGLE__NORMAL:
			return normal != null;
		case GeometryPackage.TRIANGLE__VERTICES:
			return vertices != null && !vertices.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID) {
		case GeometryPackage.TRIANGLE___EQUALS__OBJECT:
			return equals(arguments.get(0));
		case GeometryPackage.TRIANGLE___HASH_CODE:
			return hashCode();
		}
		return super.eInvoke(operationID, arguments);
	}

} // TriangleImpl