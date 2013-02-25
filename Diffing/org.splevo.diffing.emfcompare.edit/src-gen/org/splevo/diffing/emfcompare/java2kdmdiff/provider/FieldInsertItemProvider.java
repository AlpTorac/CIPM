/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.splevo.diffing.emfcompare.edit.images.ImageUtil;
import org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a
 * {@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class FieldInsertItemProvider extends FieldChangeItemProvider implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public FieldInsertItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addFieldLeftPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Field Left feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addFieldLeftPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_FieldInsert_fieldLeft_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_FieldInsert_fieldLeft_feature",
                        "_UI_FieldInsert_type"), Java2KDMDiffPackage.Literals.FIELD_INSERT__FIELD_LEFT, true, false,
                true, null, null, null));
    }

    /**
     * This returns FieldInsert.gif. <!-- begin-user-doc --> Customized to provide type specific
     * insert icon. <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public Object getImage(Object object) {
        FieldInsert fieldInsert = (FieldInsert) object;
        if (fieldInsert.getFieldLeft() != null) {
            return ImageUtil.getASTInsertIcon(fieldInsert.getFieldLeft(), this);
        } else {
            return ImageUtil.composeInsertIcon(this, ImageUtil.ICON_FIELD);
        }
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated not
     */
    @Override
    public String getText(Object object) {
        FieldInsert fieldInsert = (FieldInsert) object;

        StringBuilder nameBuilder = new StringBuilder();
        if (fieldInsert.getFieldLeft() != null) {

            if (fieldInsert.getFieldLeft().getName() != null) {
                nameBuilder.append(fieldInsert.getFieldLeft().getName());
            }
            if (fieldInsert.getFieldLeft().getFragments().size() > 0) {
                for (VariableDeclarationFragment fragment : fieldInsert.getFieldLeft().getFragments()) {

                    if (nameBuilder.length() > 0) {
                        nameBuilder.append(", ");
                    }
                    nameBuilder.append(fragment.getName());
                }
            }
        }

        return getString("_UI_FieldInsert_type") + " " + nameBuilder.toString();
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}
     * . <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that
     * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
