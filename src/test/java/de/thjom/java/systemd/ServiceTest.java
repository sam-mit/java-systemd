/*
 * Java-systemd implementation
 * Copyright (c) 2016 Markus Enax
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of either the GNU Lesser General Public License Version 2 or the
 * Academic Free Licence Version 3.0.
 *
 * Full licence texts are included in the COPYING file with this program.
 */

package de.thjom.java.systemd;

import org.freedesktop.dbus.exceptions.DBusException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.thjom.java.systemd.features.IoAccounting;
import de.thjom.java.systemd.features.IpAccounting;
import de.thjom.java.systemd.interfaces.ServiceInterface;

public class ServiceTest extends UnitTest {

    @Mock
    private ServiceInterface siface;

    private Service service;

    @Override
    @BeforeClass
    public void setup() {
        super.setup();

        try {
            Mockito.when(siface.getObjectPath()).thenReturn("foo");
            Mockito.when(dbus.getRemoteObject(Mockito.eq(Systemd.SERVICE_NAME), Mockito.anyString(), Mockito.eq(ServiceInterface.class))).thenReturn(siface);
        }
        catch (DBusException e) {
            Assert.fail(e.getMessage(), e);
        }

        setupPropertyMocks(Service.class, Service.SERVICE_NAME, Service.Property.getAllNames());

        nonVariantProperties.add(Service.Property.APP_ARMOR_PROFILE);
        nonVariantProperties.add(Service.Property.BLOCK_IO_DEVICE_WEIGHT);
        nonVariantProperties.add(Service.Property.BLOCK_IO_READ_BANDWIDTH);
        nonVariantProperties.add(Service.Property.BLOCK_IO_WRITE_BANDWIDTH);
        nonVariantProperties.add(Service.Property.DEVICE_ALLOW);
        nonVariantProperties.add(Service.Property.ENVIRONMENT_FILES);
        nonVariantProperties.add(Service.Property.EXEC_RELOAD);
        nonVariantProperties.add(Service.Property.EXEC_START);
        nonVariantProperties.add(Service.Property.EXEC_START_POST);
        nonVariantProperties.add(Service.Property.EXEC_START_PRE);
        nonVariantProperties.add(Service.Property.EXEC_STOP);
        nonVariantProperties.add(Service.Property.EXEC_STOP_POST);
        nonVariantProperties.add(Service.Property.RESTRICT_ADDRESS_FAMILIES);
        nonVariantProperties.add(Service.Property.SELINUX_CONTEXT);
        nonVariantProperties.add(Service.Property.SMACK_PROCESS_LABEL);
        nonVariantProperties.add(Service.Property.SYSTEM_CALL_FILTER);

        nonVariantProperties.add(IpAccounting.Property.IP_ADDRESS_ALLOW);
        nonVariantProperties.add(IpAccounting.Property.IP_ADDRESS_DENY);

        nonVariantProperties.add(IoAccounting.Property.IO_DEVICE_WEIGHT);
        nonVariantProperties.add(IoAccounting.Property.IO_READ_BANDWIDTH_MAX);
        nonVariantProperties.add(IoAccounting.Property.IO_READ_IOPS_MAX);
        nonVariantProperties.add(IoAccounting.Property.IO_WRITE_BANDWIDTH_MAX);
        nonVariantProperties.add(IoAccounting.Property.IO_WRITE_IOPS_MAX);
    }

    @Test(description="Tests basic manager accessibility.")
    public void testAccess() {
        try {
            service = systemd.getManager().getService("dbus");
        }
        catch (DBusException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(service);
    }

    @Test(dependsOnMethods={ "testAccess" }, description="Tests property access of service interface.")
    public void testProperties() {
        testUnitProperties(service, Service.Property.getAllNames());
    }

}
