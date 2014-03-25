/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/
package org.splevo.ui.vpexplorer.providers;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The ContentProvider for the VPExplorer.
 */
public class VPExplorerContentProvider extends TreeNodeContentProvider {

    private static Logger logger = Logger.getLogger(VPExplorerContentProvider.class);

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        logger.info("New VPM Model received: " + newInput);
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof VariationPointModel) {
            VariationPointModel vpm = (VariationPointModel) parentElement;
            return vpm.getVariationPointGroups().toArray();

        } else if (parentElement instanceof VariationPointGroup) {
            VariationPointGroup group = (VariationPointGroup) parentElement;
            return group.getVariationPoints().toArray();

        } else if (parentElement instanceof VariationPoint) {
            return ((VariationPoint) parentElement).getVariants().toArray();

        } else if (parentElement instanceof Variant) {
            EList<SoftwareElement> implementingElements = ((Variant) parentElement).getImplementingElements();
            List<SourceLocation> locations = new LinkedList<SourceLocation>();
            for (SoftwareElement element : implementingElements) {
                locations.add(element.getSourceLocation());
            }
            return locations.toArray();
        } else {
            logger.warn("Unhandled Parent Element: " + parentElement.getClass().getSimpleName());
        }
        return null;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof Variant) {
            return ((Variant) element).getVariationPoint();
        } else if (element instanceof SourceLocation) {
            return ((SourceLocation) element).eContainer();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return (getChildren(element) != null);
    }

}
