package org.splevo.vpm.variability;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.splevo.vpm.software.SoftwareElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variation Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getVariants <em>Variants</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getLocation <em>Location</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getGroup <em>Group</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getBindingTime <em>Binding Time</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getExtendibility <em>Extendibility</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint()
 * @model
 * @generated
 */
public interface VariationPoint extends EObject {
    /**
     * Returns the value of the '<em><b>Variants</b></em>' containment reference list.
     * The list contents are of type {@link org.splevo.vpm.variability.Variant}.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.variability.Variant#getVariationPoint <em>Variation Point</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variants</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variants</em>' containment reference list.
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_Variants()
     * @see org.splevo.vpm.variability.Variant#getVariationPoint
     * @model opposite="variationPoint" containment="true"
     * @generated
     */
    EList<Variant> getVariants();

    /**
     * Returns the value of the '<em><b>Location</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The software entity, the variation is enclosed by.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Location</em>' reference.
     * @see #setLocation(SoftwareElement)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_Location()
     * @model required="true"
     * @generated
     */
    SoftwareElement getLocation();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPoint#getLocation <em>Location</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Location</em>' reference.
     * @see #getLocation()
     * @generated
     */
    void setLocation(SoftwareElement value);

    /**
     * Returns the value of the '<em><b>Group</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.variability.VariationPointGroup#getVariationPoints <em>Variation Points</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The variation point group containing the variation point.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Group</em>' container reference.
     * @see #setGroup(VariationPointGroup)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_Group()
     * @see org.splevo.vpm.variability.VariationPointGroup#getVariationPoints
     * @model opposite="variationPoints" required="true" transient="false"
     * @generated
     */
    VariationPointGroup getGroup();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPoint#getGroup <em>Group</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group</em>' container reference.
     * @see #getGroup()
     * @generated
     */
    void setGroup(VariationPointGroup value);

    /**
     * Returns the value of the '<em><b>Variability Type</b></em>' attribute.
     * The default value is <code>"XOR"</code>.
     * The literals are from the enumeration {@link org.splevo.vpm.variability.VariabilityType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variability Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variability Type</em>' attribute.
     * @see org.splevo.vpm.variability.VariabilityType
     * @see #setVariabilityType(VariabilityType)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_VariabilityType()
     * @model default="XOR" required="true"
     * @generated
     */
    VariabilityType getVariabilityType();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPoint#getVariabilityType <em>Variability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variability Type</em>' attribute.
     * @see org.splevo.vpm.variability.VariabilityType
     * @see #getVariabilityType()
     * @generated
     */
    void setVariabilityType(VariabilityType value);

    /**
     * Returns the value of the '<em><b>Binding Time</b></em>' attribute.
     * The default value is <code>"LoadTime"</code>.
     * The literals are from the enumeration {@link org.splevo.vpm.variability.BindingTime}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Binding Time</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Binding Time</em>' attribute.
     * @see org.splevo.vpm.variability.BindingTime
     * @see #setBindingTime(BindingTime)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_BindingTime()
     * @model default="LoadTime" required="true"
     * @generated
     */
    BindingTime getBindingTime();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPoint#getBindingTime <em>Binding Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Binding Time</em>' attribute.
     * @see org.splevo.vpm.variability.BindingTime
     * @see #getBindingTime()
     * @generated
     */
    void setBindingTime(BindingTime value);

    /**
     * Returns the value of the '<em><b>Extendibility</b></em>' attribute.
     * The default value is <code>"Open"</code>.
     * The literals are from the enumeration {@link org.splevo.vpm.variability.Extendibility}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extendibility</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extendibility</em>' attribute.
     * @see org.splevo.vpm.variability.Extendibility
     * @see #setExtendibility(Extendibility)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_Extendibility()
     * @model default="Open" required="true"
     * @generated
     */
    Extendibility getExtendibility();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPoint#getExtendibility <em>Extendibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Extendibility</em>' attribute.
     * @see org.splevo.vpm.variability.Extendibility
     * @see #getExtendibility()
     * @generated
     */
    void setExtendibility(Extendibility value);

} // VariationPoint
