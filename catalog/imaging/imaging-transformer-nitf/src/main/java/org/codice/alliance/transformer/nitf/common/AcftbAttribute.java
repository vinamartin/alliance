/**
 * Copyright (c) Codice Foundation
 * <p/>
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.alliance.transformer.nitf.common;

import java.io.Serializable;
import java.util.function.Function;

import org.codice.imaging.nitf.core.tre.Tre;

import ddf.catalog.data.AttributeDescriptor;
import ddf.catalog.data.impl.AttributeDescriptorImpl;
import ddf.catalog.data.impl.BasicTypes;

public enum AcftbAttribute implements NitfAttribute<Tre> {
    AIRCRAFT_MISSION_ID("aircraft-mission-id",
            "AC_MSN_ID",
            tre -> TreUtility.getTreValue(tre, "AC_MSN_ID")),

    AIRCRAFT_TAIL_NUMBER("aircraft-tail-number",
            "AC_TAIL_NO",
            tre -> TreUtility.getTreValue(tre, "AC_TAIL_NO")),

    AIRCRAFT_TAKEOFF("aircraft-take-off", "AC_TO", tre -> TreUtility.getTreValue(tre, "AC_TO")),

    SENSOR_ID_TYPE("sensor-id-type",
            "SENSOR_ID_TYPE",
            tre -> TreUtility.getTreValue(tre, "SENSOR_ID_TYPE")),

    SENSOR_ID("sensor-id", "SENSOR_ID", tre -> TreUtility.getTreValue(tre, "SENSOR_ID")),

    SCENE_SOURCE("scene-source",
            "SCENE_SOURCE",
            tre -> TreUtility.getTreValue(tre, "SCENE_SOURCE")),

    SCENE_NUMBER("scene-number", "SCNUM", tre -> TreUtility.getTreValue(tre, "SCNUM")),

    PROCESSING_DATE("processing-date", "PDATE", tre -> TreUtility.getTreValue(tre, "PDATE")),

    IMMEDIATE_SCENE_HOST("immediate-scene-host",
            "IMHOSTNO",
            tre -> TreUtility.getTreValue(tre, "IMHOSTNO")),

    IMMEDIATE_SCENE_REQUEST_ID("immediate-scene-request-id",
            "IMREQID",
            tre -> TreUtility.getTreValue(tre, "IMREQID")),

    MISSION_PLAN_MODE("mission-plan-mode", "MPLAN", tre -> TreUtility.getTreValue(tre, "MPLAN"));

    private static final String ATTRIBUTE_NAME_PREFIX = "nitf.acftb.";

    private String shortName;

    private String longName;

    private Function<Tre, Serializable> accessorFunction;

    private AttributeDescriptor attributeDescriptor;

    AcftbAttribute(String longName, String shortName,
            Function<Tre, Serializable> accessorFunction) {
        this.longName = longName;
        this.shortName = shortName;
        this.accessorFunction = accessorFunction;
        this.attributeDescriptor = new AttributeDescriptorImpl(ATTRIBUTE_NAME_PREFIX + longName,
                true,
                true,
                false,
                true,
                BasicTypes.STRING_TYPE);
    }

    @Override
    public String getLongName() {
        return this.longName;
    }

    @Override
    public String getShortName() {
        return this.shortName;
    }

    @Override
    public Function<Tre, Serializable> getAccessorFunction() {
        return this.accessorFunction;
    }

    @Override
    public AttributeDescriptor getAttributeDescriptor() {
        return this.attributeDescriptor;
    }
}
