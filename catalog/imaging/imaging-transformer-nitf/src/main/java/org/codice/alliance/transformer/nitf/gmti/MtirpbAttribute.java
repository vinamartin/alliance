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
package org.codice.alliance.transformer.nitf.gmti;

import java.io.Serializable;
import java.util.function.Function;

import org.codice.alliance.transformer.nitf.common.NitfAttribute;
import org.codice.alliance.transformer.nitf.common.TreUtility;
import org.codice.imaging.nitf.core.tre.Tre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.data.AttributeDescriptor;
import ddf.catalog.data.impl.AttributeDescriptorImpl;
import ddf.catalog.data.impl.BasicTypes;

public enum MtirpbAttribute implements NitfAttribute<Tre> {
    DESTINATION_POINT("destination-point",
            "MTI_DP",
            tre -> TreUtility.getTreValue(tre, "MTI_DP")),
    PATCH_NUMBER("patch-number", "PATCH_NO", tre -> TreUtility.getTreValue(tre, "PATCH_NO")),
    WIDE_AREA_MTI_FRAME_NUMBER("wide-area-mti-frame-number",
            "WAMTI_FRAME_NO",
            tre -> TreUtility.getTreValue(tre, "WAMTI_FRAME_NO")),
    WIDE_AREA_MTI_BAR_NUMBER("wide-area-mti-bar-number",
            "WAMTI_BAR_NO",
            tre -> TreUtility.getTreValue(tre, "WAMTI_BAR_NO")),
    SCAN_DATE_AND_TIME("scan-date-and-time",
            "DATIME",
            tre -> TreUtility.getTreValue(tre, "DATIME")),
    AIRCRAFT_LOCATION("aircraft-location",
            "ACFT_LOC",
            tre -> TreUtility.getTreValue(tre, "ACFT_LOC")),
    AIRCRAFT_ALTITUDE("aircraft-altitude",
            "ACFT_ALT",
            tre -> TreUtility.getTreValue(tre, "ACFT_ALT")),
    AIRCRAFT_ALTITUDE_UNITS("aircraft-altitude-unit-of-measure",
            "ACFT_ALT_UNIT",
            tre -> TreUtility.getTreValue(tre, "ACFT_ALT_UNIT")),
    AIRCRAFT_HEADING("aircraft-heading",
            "ACFT_HEADING",
            tre -> TreUtility.getTreValue(tre, "ACFT_HEADING")),
    MTI_LR("mti-left-or-right", "MTI_LR", tre -> TreUtility.getTreValue(tre, "MTI_LR")),
    SQUINT_ANGLE("squint-angle",
            "SQUINT_ANGLE",
            tre -> TreUtility.getTreValue(tre, "SQUINT_ANGLE")),
    COSINE_OF_GRAZE_ANGLE("cosine-of-graze-angle",
            "COSGRZ",
            tre -> TreUtility.getTreValue(tre, "COSGRZ")),
    NUMBER_OF_VALID_TARGETS("number-of-valid-targets",
            "NO_VALID_TARGETS",
            tre -> TreUtility.getTreValue(tre, "NO_VALID_TARGETS"));

    private static final Logger LOGGER = LoggerFactory.getLogger(MtirpbAttribute.class);

    private static final String ATTRIBUTE_NAME_PREFIX = "nitf.mtirpb.";

    private String shortName;

    private String longName;

    private Function<Tre, Serializable> accessorFunction;

    private AttributeDescriptor attributeDescriptor;

    MtirpbAttribute(String longName, String shortName,
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
