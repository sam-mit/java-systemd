/*
 * Java-systemd implementation
 * Copyright (c) 2016 Markus Enax
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of either the GNU Lesser General Public License Version 2 or the
 * Academic Free Licence Version 2.1.
 *
 * Full licence texts are included in the COPYING file with this program.
 */

package de.thjom.java.systemd;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import org.freedesktop.dbus.exceptions.DBusException;

import de.thjom.java.systemd.interfaces.SocketInterface;
import de.thjom.java.systemd.types.AddressFamilyRestriction;
import de.thjom.java.systemd.types.AppArmorProfile;
import de.thjom.java.systemd.types.BlockIOBandwidth;
import de.thjom.java.systemd.types.BlockIODeviceWeight;
import de.thjom.java.systemd.types.DeviceAllowControl;
import de.thjom.java.systemd.types.EnvironmentFile;
import de.thjom.java.systemd.types.ExecutionInfo;
import de.thjom.java.systemd.types.ListenInfo;
import de.thjom.java.systemd.types.SELinuxContext;
import de.thjom.java.systemd.types.SmackProcessLabel;
import de.thjom.java.systemd.types.SystemCallFilter;

public class Socket extends Unit {

    public static final String SERVICE_NAME = Systemd.SERVICE_NAME + ".Socket";
    public static final String UNIT_SUFFIX = ".socket";

    public static class Property extends InterfaceAdapter.Property {

        public static final String ACCEPT = "Accept";
        public static final String AMBIENT_CAPABILITIES = "AmbientCapabilities";
        public static final String APP_ARMOR_PROFILE = "AppArmorProfile";
        public static final String BACKLOG = "Backlog";
        public static final String BIND_IPV6_ONLY = "BindIPv6Only";
        public static final String BIND_TO_DEVICE = "BindToDevice";
        public static final String BLOCK_IOACCOUNTING = "BlockIOAccounting";
        public static final String BLOCK_IODEVICE_WEIGHT = "BlockIODeviceWeight";
        public static final String BLOCK_IOREAD_BANDWIDTH = "BlockIOReadBandwidth";
        public static final String BLOCK_IOWEIGHT = "BlockIOWeight";
        public static final String BLOCK_IOWRITE_BANDWIDTH = "BlockIOWriteBandwidth";
        public static final String BROADCAST = "Broadcast";
        public static final String CPUACCOUNTING = "CPUAccounting";
        public static final String CPUAFFINITY = "CPUAffinity";
        public static final String CPUQUOTA_PER_SEC_USEC = "CPUQuotaPerSecUSec";
        public static final String CPUSCHEDULING_POLICY = "CPUSchedulingPolicy";
        public static final String CPUSCHEDULING_PRIORITY = "CPUSchedulingPriority";
        public static final String CPUSCHEDULING_RESET_ON_FORK = "CPUSchedulingResetOnFork";
        public static final String CPUSHARES = "CPUShares";
        public static final String CPUUSAGE_NSEC = "CPUUsageNSec";
        public static final String CAPABILITIES = "Capabilities";
        public static final String CAPABILITY_BOUNDING_SET = "CapabilityBoundingSet";
        public static final String CONTROL_PID = "ControlPID";
        public static final String DEFER_ACCEPT_USEC = "DeferAcceptUSec";
        public static final String DELEGATE = "Delegate";
        public static final String DEVICE_ALLOW = "DeviceAllow";
        public static final String DEVICE_POLICY = "DevicePolicy";
        public static final String DIRECTORY_MODE = "DirectoryMode";
        public static final String ENVIRONMENT = "Environment";
        public static final String ENVIRONMENT_FILES = "EnvironmentFiles";
        public static final String EXEC_START_POST = "ExecStartPost";
        public static final String EXEC_START_PRE = "ExecStartPre";
        public static final String EXEC_STOP_POST = "ExecStopPost";
        public static final String EXEC_STOP_PRE = "ExecStopPre";
        public static final String FILE_DESCRIPTOR_NAME = "FileDescriptorName";
        public static final String FREE_BIND = "FreeBind";
        public static final String GROUP = "Group";
        public static final String IOSCHEDULING = "IOScheduling";
        public static final String IPTOS = "IPTOS";
        public static final String IPTTL = "IPTTL";
        public static final String IGNORE_SIGPIPE = "IgnoreSIGPIPE";
        public static final String INACCESSIBLE_DIRECTORIES = "InaccessibleDirectories";
        public static final String KEEP_ALIVE = "KeepAlive";
        public static final String KEEP_ALIVE_INTERVAL_USEC = "KeepAliveIntervalUSec";
        public static final String KEEP_ALIVE_PROBES = "KeepAliveProbes";
        public static final String KEEP_ALIVE_TIME_USEC = "KeepAliveTimeUSec";
        public static final String KILL_MODE = "KillMode";
        public static final String KILL_SIGNAL = "KillSignal";
        public static final String LIMIT_AS = "LimitAS";
        public static final String LIMIT_ASSOFT = "LimitASSoft";
        public static final String LIMIT_CORE = "LimitCORE";
        public static final String LIMIT_CORESOFT = "LimitCORESoft";
        public static final String LIMIT_CPU = "LimitCPU";
        public static final String LIMIT_CPUSOFT = "LimitCPUSoft";
        public static final String LIMIT_DATA = "LimitDATA";
        public static final String LIMIT_DATASOFT = "LimitDATASoft";
        public static final String LIMIT_FSIZE = "LimitFSIZE";
        public static final String LIMIT_FSIZESOFT = "LimitFSIZESoft";
        public static final String LIMIT_LOCKS = "LimitLOCKS";
        public static final String LIMIT_LOCKSSOFT = "LimitLOCKSSoft";
        public static final String LIMIT_MEMLOCK = "LimitMEMLOCK";
        public static final String LIMIT_MEMLOCKSOFT = "LimitMEMLOCKSoft";
        public static final String LIMIT_MSGQUEUE = "LimitMSGQUEUE";
        public static final String LIMIT_MSGQUEUESOFT = "LimitMSGQUEUESoft";
        public static final String LIMIT_NICE = "LimitNICE";
        public static final String LIMIT_NICESOFT = "LimitNICESoft";
        public static final String LIMIT_NOFILE = "LimitNOFILE";
        public static final String LIMIT_NOFILESOFT = "LimitNOFILESoft";
        public static final String LIMIT_NPROC = "LimitNPROC";
        public static final String LIMIT_NPROCSOFT = "LimitNPROCSoft";
        public static final String LIMIT_RSS = "LimitRSS";
        public static final String LIMIT_RSSSOFT = "LimitRSSSoft";
        public static final String LIMIT_RTPRIO = "LimitRTPRIO";
        public static final String LIMIT_RTPRIOSOFT = "LimitRTPRIOSoft";
        public static final String LIMIT_RTTIME = "LimitRTTIME";
        public static final String LIMIT_RTTIMESOFT = "LimitRTTIMESoft";
        public static final String LIMIT_SIGPENDING = "LimitSIGPENDING";
        public static final String LIMIT_SIGPENDINGSOFT = "LimitSIGPENDINGSoft";
        public static final String LIMIT_STACK = "LimitSTACK";
        public static final String LIMIT_STACKSOFT = "LimitSTACKSoft";
        public static final String LISTEN = "Listen";
        public static final String MARK = "Mark";
        public static final String MAX_CONNECTIONS = "MaxConnections";
        public static final String MEMORY_ACCOUNTING = "MemoryAccounting";
        public static final String MEMORY_CURRENT = "MemoryCurrent";
        public static final String MEMORY_LIMIT = "MemoryLimit";
        public static final String MESSAGE_QUEUE_MAX_MESSAGES = "MessageQueueMaxMessages";
        public static final String MESSAGE_QUEUE_MESSAGE_SIZE = "MessageQueueMessageSize";
        public static final String MOUNT_FLAGS = "MountFlags";
        public static final String NACCEPTED = "NAccepted";
        public static final String NCONNECTIONS = "NConnections";
        public static final String NICE = "Nice";
        public static final String NO_DELAY = "NoDelay";
        public static final String NO_NEW_PRIVILEGES = "NoNewPrivileges";
        public static final String NON_BLOCKING = "NonBlocking";
        public static final String OOMSCORE_ADJUST = "OOMScoreAdjust";
        public static final String PAMNAME = "PAMName";
        public static final String PASS_CREDENTIALS = "PassCredentials";
        public static final String PASS_ENVIRONMENT = "PassEnvironment";
        public static final String PASS_SECURITY = "PassSecurity";
        public static final String PERSONALITY = "Personality";
        public static final String PIPE_SIZE = "PipeSize";
        public static final String PRIORITY = "Priority";
        public static final String PRIVATE_DEVICES = "PrivateDevices";
        public static final String PRIVATE_NETWORK = "PrivateNetwork";
        public static final String PRIVATE_TMP = "PrivateTmp";
        public static final String PROTECT_HOME = "ProtectHome";
        public static final String PROTECT_SYSTEM = "ProtectSystem";
        public static final String READ_ONLY_DIRECTORIES = "ReadOnlyDirectories";
        public static final String READ_WRITE_DIRECTORIES = "ReadWriteDirectories";
        public static final String RECEIVE_BUFFER = "ReceiveBuffer";
        public static final String REMOVE_ON_STOP = "RemoveOnStop";
        public static final String RESTRICT_ADDRESS_FAMILIES = "RestrictAddressFamilies";
        public static final String RESULT = "Result";
        public static final String REUSE_PORT = "ReusePort";
        public static final String ROOT_DIRECTORY = "RootDirectory";
        public static final String RUNTIME_DIRECTORY = "RuntimeDirectory";
        public static final String RUNTIME_DIRECTORY_MODE = "RuntimeDirectoryMode";
        public static final String SELINUX_CONTEXT = "SELinuxContext";
        public static final String SAME_PROCESS_GROUP = "SameProcessGroup";
        public static final String SECURE_BITS = "SecureBits";
        public static final String SEND_BUFFER = "SendBuffer";
        public static final String SEND_SIGHUP = "SendSIGHUP";
        public static final String SEND_SIGKILL = "SendSIGKILL";
        public static final String SLICE = "Slice";
        public static final String SMACK_LABEL = "SmackLabel";
        public static final String SMACK_LABEL_IPIN = "SmackLabelIPIn";
        public static final String SMACK_LABEL_IPOUT = "SmackLabelIPOut";
        public static final String SMACK_PROCESS_LABEL = "SmackProcessLabel";
        public static final String SOCKET_GROUP = "SocketGroup";
        public static final String SOCKET_MODE = "SocketMode";
        public static final String SOCKET_PROTOCOL = "SocketProtocol";
        public static final String SOCKET_USER = "SocketUser";
        public static final String STANDARD_ERROR = "StandardError";
        public static final String STANDARD_INPUT = "StandardInput";
        public static final String STANDARD_OUTPUT = "StandardOutput";
        public static final String STARTUP_BLOCK_IOWEIGHT = "StartupBlockIOWeight";
        public static final String STARTUP_CPUSHARES = "StartupCPUShares";
        public static final String SUPPLEMENTARY_GROUPS = "SupplementaryGroups";
        public static final String SYMLINKS = "Symlinks";
        public static final String SYSLOG_FACILITY = "SyslogFacility";
        public static final String SYSLOG_IDENTIFIER = "SyslogIdentifier";
        public static final String SYSLOG_LEVEL = "SyslogLevel";
        public static final String SYSLOG_LEVEL_PREFIX = "SyslogLevelPrefix";
        public static final String SYSLOG_PRIORITY = "SyslogPriority";
        public static final String SYSTEM_CALL_ARCHITECTURES = "SystemCallArchitectures";
        public static final String SYSTEM_CALL_ERROR_NUMBER = "SystemCallErrorNumber";
        public static final String SYSTEM_CALL_FILTER = "SystemCallFilter";
        public static final String TTYPATH = "TTYPath";
        public static final String TTYRESET = "TTYReset";
        public static final String TTYVHANGUP = "TTYVHangup";
        public static final String TTYVTDISALLOCATE = "TTYVTDisallocate";
        public static final String TASKS_ACCOUNTING = "TasksAccounting";
        public static final String TASKS_CURRENT = "TasksCurrent";
        public static final String TASKS_MAX = "TasksMax";
        public static final String TIMEOUT_USEC = "TimeoutUSec";
        public static final String TIMER_SLACK_NSEC = "TimerSlackNSec";
        public static final String TRANSPARENT = "Transparent";
        public static final String UMASK = "UMask";
        public static final String USER = "User";
        public static final String UTMP_IDENTIFIER = "UtmpIdentifier";
        public static final String UTMP_MODE = "UtmpMode";
        public static final String WORKING_DIRECTORY = "WorkingDirectory";
        public static final String WRITABLE = "Writable";

        private Property() {
            super();
        }

        public static final String[] getAllNames() {
            return getAllNames(Property.class);
        }

    }

    private Socket(final Manager manager, final SocketInterface iface, final String name) throws DBusException {
        super(manager, iface, name);

        this.properties = Properties.create(dbus, iface.getObjectPath(), SERVICE_NAME);
    }

    static Socket create(final Manager manager, String name) throws DBusException {
        name = Unit.normalizeName(name, UNIT_SUFFIX);

        String objectPath = Unit.OBJECT_PATH + Systemd.escapePath(name);
        SocketInterface iface = manager.dbus.getRemoteObject(Systemd.SERVICE_NAME, objectPath, SocketInterface.class);

        return new Socket(manager, iface, name);
    }

    @Override
    public SocketInterface getInterface() {
        return (SocketInterface) super.getInterface();
    }

    public boolean isAccept() {
        return properties.getBoolean(Property.ACCEPT);
    }

    public BigInteger getAmbientCapabilities() {
        return properties.getBigInteger(Property.AMBIENT_CAPABILITIES);
    }

    public AppArmorProfile getAppArmorProfile() {
        Object[] array = (Object[]) properties.getVariant(Property.APP_ARMOR_PROFILE).getValue();

        return new AppArmorProfile(array);
    }

    public long getBacklog() {
        return properties.getLong(Property.BACKLOG);
    }

    public String getBindIPv6Only() {
        return properties.getString(Property.BIND_IPV6_ONLY);
    }

    public String getBindToDevice() {
        return properties.getString(Property.BIND_TO_DEVICE);
    }

    public boolean isBlockIOAccounting() {
        return properties.getBoolean(Property.BLOCK_IOACCOUNTING);
    }

    public List<BlockIODeviceWeight> getBlockIODeviceWeight() {
        return BlockIODeviceWeight.list(properties.getVector(Property.BLOCK_IODEVICE_WEIGHT));
    }

    public List<BlockIOBandwidth> getBlockIOReadBandwidth() {
        return BlockIOBandwidth.list(properties.getVector(Property.BLOCK_IOREAD_BANDWIDTH));
    }

    public BigInteger getBlockIOWeight() {
        return properties.getBigInteger(Property.BLOCK_IOWEIGHT);
    }

    public List<BlockIOBandwidth> getBlockIOWriteBandwidth() {
        return BlockIOBandwidth.list(properties.getVector(Property.BLOCK_IOWRITE_BANDWIDTH));
    }

    public boolean isBroadcast() {
        return properties.getBoolean(Property.BROADCAST);
    }

    public boolean isCPUAccounting() {
        return properties.getBoolean(Property.CPUACCOUNTING);
    }

    public byte[] getCPUAffinity() {
        return (byte[]) properties.getVariant(Property.CPUAFFINITY).getValue();
    }

    public BigInteger getCPUQuotaPerSecUSec() {
        return properties.getBigInteger(Property.CPUQUOTA_PER_SEC_USEC);
    }

    public int getCPUSchedulingPolicy() {
        return properties.getInteger(Property.CPUSCHEDULING_POLICY);
    }

    public int getCPUSchedulingPriority() {
        return properties.getInteger(Property.CPUSCHEDULING_PRIORITY);
    }

    public boolean isCPUSchedulingResetOnFork() {
        return properties.getBoolean(Property.CPUSCHEDULING_RESET_ON_FORK);
    }

    public BigInteger getCPUShares() {
        return properties.getBigInteger(Property.CPUSHARES);
    }

    public BigInteger getCPUUsageNSec() {
        return properties.getBigInteger(Property.CPUUSAGE_NSEC);
    }

    public String getCapabilities() {
        return properties.getString(Property.CAPABILITIES);
    }

    public BigInteger getCapabilityBoundingSet() {
        return properties.getBigInteger(Property.CAPABILITY_BOUNDING_SET);
    }

    public long getControlPID() {
        return properties.getLong(Property.CONTROL_PID);
    }

    public long getDeferAcceptUSec() {
        return properties.getLong(Property.DEFER_ACCEPT_USEC);
    }

    public boolean isDelegate() {
        return properties.getBoolean(Property.DELEGATE);
    }

    public List<DeviceAllowControl> getDeviceAllow() {
        return DeviceAllowControl.list(properties.getVector(Property.DEVICE_ALLOW));
    }

    public String getDevicePolicy() {
        return properties.getString(Property.DEVICE_POLICY);
    }

    public long getDirectoryMode() {
        return properties.getLong(Property.DIRECTORY_MODE);
    }

    public Vector<String> getEnvironment() {
        return properties.getVector(Property.ENVIRONMENT);
    }

    public List<EnvironmentFile> getEnvironmentFiles() {
        return EnvironmentFile.list(properties.getVector(Property.ENVIRONMENT_FILES));
    }

    public List<ExecutionInfo> getExecStartPost() {
        return ExecutionInfo.list(properties.getVector(Property.EXEC_START_POST));
    }

    public List<ExecutionInfo> getExecStartPre() {
        return ExecutionInfo.list(properties.getVector(Property.EXEC_START_PRE));
    }

    public List<ExecutionInfo> getExecStopPost() {
        return ExecutionInfo.list(properties.getVector(Property.EXEC_STOP_POST));
    }

    public List<ExecutionInfo> getExecStopPre() {
        return ExecutionInfo.list(properties.getVector(Property.EXEC_STOP_PRE));
    }

    public String getFileDescriptorName() {
        return properties.getString(Property.FILE_DESCRIPTOR_NAME);
    }

    public boolean isFreeBind() {
        return properties.getBoolean(Property.FREE_BIND);
    }

    public String getGroup() {
        return properties.getString(Property.GROUP);
    }

    public int getIOScheduling() {
        return properties.getInteger(Property.IOSCHEDULING);
    }

    public int getIPTOS() {
        return properties.getInteger(Property.IPTOS);
    }

    public int getIPTTL() {
        return properties.getInteger(Property.IPTTL);
    }

    public boolean isIgnoreSIGPIPE() {
        return properties.getBoolean(Property.IGNORE_SIGPIPE);
    }

    public Vector<String> getInaccessibleDirectories() {
        return properties.getVector(Property.INACCESSIBLE_DIRECTORIES);
    }

    public boolean isKeepAlive() {
        return properties.getBoolean(Property.KEEP_ALIVE);
    }

    public long getKeepAliveIntervalUSec() {
        return properties.getLong(Property.KEEP_ALIVE_INTERVAL_USEC);
    }

    public long getKeepAliveProbes() {
        return properties.getLong(Property.KEEP_ALIVE_PROBES);
    }

    public long getKeepAliveTimeUSec() {
        return properties.getLong(Property.KEEP_ALIVE_TIME_USEC);
    }

    public String getKillMode() {
        return properties.getString(Property.KILL_MODE);
    }

    public int getKillSignal() {
        return properties.getInteger(Property.KILL_SIGNAL);
    }

    public BigInteger getLimitAS() {
        return properties.getBigInteger(Property.LIMIT_AS);
    }

    public BigInteger getLimitASSoft() {
        return properties.getBigInteger(Property.LIMIT_ASSOFT);
    }

    public BigInteger getLimitCORE() {
        return properties.getBigInteger(Property.LIMIT_CORE);
    }

    public BigInteger getLimitCORESoft() {
        return properties.getBigInteger(Property.LIMIT_CORESOFT);
    }

    public BigInteger getLimitCPU() {
        return properties.getBigInteger(Property.LIMIT_CPU);
    }

    public BigInteger getLimitCPUSoft() {
        return properties.getBigInteger(Property.LIMIT_CPUSOFT);
    }

    public BigInteger getLimitDATA() {
        return properties.getBigInteger(Property.LIMIT_DATA);
    }

    public BigInteger getLimitDATASoft() {
        return properties.getBigInteger(Property.LIMIT_DATASOFT);
    }

    public BigInteger getLimitFSIZE() {
        return properties.getBigInteger(Property.LIMIT_FSIZE);
    }

    public BigInteger getLimitFSIZESoft() {
        return properties.getBigInteger(Property.LIMIT_FSIZESOFT);
    }

    public BigInteger getLimitLOCKS() {
        return properties.getBigInteger(Property.LIMIT_LOCKS);
    }

    public BigInteger getLimitLOCKSSoft() {
        return properties.getBigInteger(Property.LIMIT_LOCKSSOFT);
    }

    public BigInteger getLimitMEMLOCK() {
        return properties.getBigInteger(Property.LIMIT_MEMLOCK);
    }

    public BigInteger getLimitMEMLOCKSoft() {
        return properties.getBigInteger(Property.LIMIT_MEMLOCKSOFT);
    }

    public BigInteger getLimitMSGQUEUE() {
        return properties.getBigInteger(Property.LIMIT_MSGQUEUE);
    }

    public BigInteger getLimitMSGQUEUESoft() {
        return properties.getBigInteger(Property.LIMIT_MSGQUEUESOFT);
    }

    public BigInteger getLimitNICE() {
        return properties.getBigInteger(Property.LIMIT_NICE);
    }

    public BigInteger getLimitNICESoft() {
        return properties.getBigInteger(Property.LIMIT_NICESOFT);
    }

    public BigInteger getLimitNOFILE() {
        return properties.getBigInteger(Property.LIMIT_NOFILE);
    }

    public BigInteger getLimitNOFILESoft() {
        return properties.getBigInteger(Property.LIMIT_NOFILESOFT);
    }

    public BigInteger getLimitNPROC() {
        return properties.getBigInteger(Property.LIMIT_NPROC);
    }

    public BigInteger getLimitNPROCSoft() {
        return properties.getBigInteger(Property.LIMIT_NPROCSOFT);
    }

    public BigInteger getLimitRSS() {
        return properties.getBigInteger(Property.LIMIT_RSS);
    }

    public BigInteger getLimitRSSSoft() {
        return properties.getBigInteger(Property.LIMIT_RSSSOFT);
    }

    public BigInteger getLimitRTPRIO() {
        return properties.getBigInteger(Property.LIMIT_RTPRIO);
    }

    public BigInteger getLimitRTPRIOSoft() {
        return properties.getBigInteger(Property.LIMIT_RTPRIOSOFT);
    }

    public BigInteger getLimitRTTIME() {
        return properties.getBigInteger(Property.LIMIT_RTTIME);
    }

    public BigInteger getLimitRTTIMESoft() {
        return properties.getBigInteger(Property.LIMIT_RTTIMESOFT);
    }

    public BigInteger getLimitSIGPENDING() {
        return properties.getBigInteger(Property.LIMIT_SIGPENDING);
    }

    public BigInteger getLimitSIGPENDINGSoft() {
        return properties.getBigInteger(Property.LIMIT_SIGPENDINGSOFT);
    }

    public BigInteger getLimitSTACK() {
        return properties.getBigInteger(Property.LIMIT_STACK);
    }

    public BigInteger getLimitSTACKSoft() {
        return properties.getBigInteger(Property.LIMIT_STACKSOFT);
    }

    public List<ListenInfo> getListen() {
        return ListenInfo.list(properties.getVector(Property.LISTEN));
    }

    public int getMark() {
        return properties.getInteger(Property.MARK);
    }

    public long getMaxConnections() {
        return properties.getLong(Property.MAX_CONNECTIONS);
    }

    public boolean isMemoryAccounting() {
        return properties.getBoolean(Property.MEMORY_ACCOUNTING);
    }

    public BigInteger getMemoryCurrent() {
        return properties.getBigInteger(Property.MEMORY_CURRENT);
    }

    public BigInteger getMemoryLimit() {
        return properties.getBigInteger(Property.MEMORY_LIMIT);
    }

    public long getMessageQueueMaxMessages() {
        return properties.getLong(Property.MESSAGE_QUEUE_MAX_MESSAGES);
    }

    public long getMessageQueueMessageSize() {
        return properties.getLong(Property.MESSAGE_QUEUE_MESSAGE_SIZE);
    }

    public BigInteger getMountFlags() {
        return properties.getBigInteger(Property.MOUNT_FLAGS);
    }

    public long getNAccepted() {
        return properties.getLong(Property.NACCEPTED);
    }

    public long getNConnections() {
        return properties.getLong(Property.NCONNECTIONS);
    }

    public int getNice() {
        return properties.getInteger(Property.NICE);
    }

    public boolean isNoDelay() {
        return properties.getBoolean(Property.NO_DELAY);
    }

    public boolean isNoNewPrivileges() {
        return properties.getBoolean(Property.NO_NEW_PRIVILEGES);
    }

    public boolean isNonBlocking() {
        return properties.getBoolean(Property.NON_BLOCKING);
    }

    public int getOOMScoreAdjust() {
        return properties.getInteger(Property.OOMSCORE_ADJUST);
    }

    public String getPAMName() {
        return properties.getString(Property.PAMNAME);
    }

    public boolean isPassCredentials() {
        return properties.getBoolean(Property.PASS_CREDENTIALS);
    }

    public Vector<String> getPassEnvironment() {
        return properties.getVector(Property.PASS_ENVIRONMENT);
    }

    public boolean isPassSecurity() {
        return properties.getBoolean(Property.PASS_SECURITY);
    }

    public String getPersonality() {
        return properties.getString(Property.PERSONALITY);
    }

    public BigInteger getPipeSize() {
        return properties.getBigInteger(Property.PIPE_SIZE);
    }

    public int getPriority() {
        return properties.getInteger(Property.PRIORITY);
    }

    public boolean isPrivateDevices() {
        return properties.getBoolean(Property.PRIVATE_DEVICES);
    }

    public boolean isPrivateNetwork() {
        return properties.getBoolean(Property.PRIVATE_NETWORK);
    }

    public boolean isPrivateTmp() {
        return properties.getBoolean(Property.PRIVATE_TMP);
    }

    public String getProtectHome() {
        return properties.getString(Property.PROTECT_HOME);
    }

    public String getProtectSystem() {
        return properties.getString(Property.PROTECT_SYSTEM);
    }

    public Vector<String> getReadOnlyDirectories() {
        return properties.getVector(Property.READ_ONLY_DIRECTORIES);
    }

    public Vector<String> getReadWriteDirectories() {
        return properties.getVector(Property.READ_WRITE_DIRECTORIES);
    }

    public BigInteger getReceiveBuffer() {
        return properties.getBigInteger(Property.RECEIVE_BUFFER);
    }

    public boolean isRemoveOnStop() {
        return properties.getBoolean(Property.REMOVE_ON_STOP);
    }

    public AddressFamilyRestriction getRestrictAddressFamilies() {
        Object[] array = (Object[]) properties.getVariant(Property.RESTRICT_ADDRESS_FAMILIES).getValue();

        return new AddressFamilyRestriction(array);
    }

    public String getResult() {
        return properties.getString(Property.RESULT);
    }

    public boolean getReusePort() {
        return properties.getBoolean(Property.REUSE_PORT);
    }

    public String getRootDirectory() {
        return properties.getString(Property.ROOT_DIRECTORY);
    }

    public Vector<String> getRuntimeDirectory() {
        return properties.getVector(Property.RUNTIME_DIRECTORY);
    }

    public long getRuntimeDirectoryMode() {
        return properties.getLong(Property.RUNTIME_DIRECTORY_MODE);
    }

    public SELinuxContext getSELinuxContext() {
        Object[] array = (Object[]) properties.getVariant(Property.SELINUX_CONTEXT).getValue();

        return new SELinuxContext(array);
    }

    public boolean isSameProcessGroup() {
        return properties.getBoolean(Property.SAME_PROCESS_GROUP);
    }

    public int getSecureBits() {
        return properties.getInteger(Property.SECURE_BITS);
    }

    public BigInteger getSendBuffer() {
        return properties.getBigInteger(Property.SEND_BUFFER);
    }

    public boolean isSendSIGHUP() {
        return properties.getBoolean(Property.SEND_SIGHUP);
    }

    public boolean isSendSIGKILL() {
        return properties.getBoolean(Property.SEND_SIGKILL);
    }

    public String getSlice() {
        return properties.getString(Property.SLICE);
    }

    public String getSmackLabel() {
        return properties.getString(Property.SMACK_LABEL);
    }

    public String getSmackLabelIPIn() {
        return properties.getString(Property.SMACK_LABEL_IPIN);
    }

    public String getSmackLabelIPOut() {
        return properties.getString(Property.SMACK_LABEL_IPOUT);
    }

    public SmackProcessLabel getSmackProcessLabel() {
        Object[] array = (Object[]) properties.getVariant(Property.SMACK_PROCESS_LABEL).getValue();

        return new SmackProcessLabel(array);
    }

    public String getSocketGroup() {
        return properties.getString(Property.SOCKET_GROUP);
    }

    public long getSocketMode() {
        return properties.getLong(Property.SOCKET_MODE);
    }

    public int getSocketProtocol() {
        return properties.getInteger(Property.SOCKET_PROTOCOL);
    }

    public String getSocketUser() {
        return properties.getString(Property.SOCKET_USER);
    }

    public String getStandardError() {
        return properties.getString(Property.STANDARD_ERROR);
    }

    public String getStandardInput() {
        return properties.getString(Property.STANDARD_INPUT);
    }

    public String getStandardOutput() {
        return properties.getString(Property.STANDARD_OUTPUT);
    }

    public BigInteger getStartupBlockIOWeight() {
        return properties.getBigInteger(Property.STARTUP_BLOCK_IOWEIGHT);
    }

    public BigInteger getStartupCPUShares() {
        return properties.getBigInteger(Property.STARTUP_CPUSHARES);
    }

    public Vector<String> getSupplementaryGroups() {
        return properties.getVector(Property.SUPPLEMENTARY_GROUPS);
    }

    public Vector<String> getSymlinks() {
        return properties.getVector(Property.SYMLINKS);
    }

    public int getSyslogFacility() {
        return properties.getInteger(Property.SYSLOG_FACILITY);
    }

    public String getSyslogIdentifier() {
        return properties.getString(Property.SYSLOG_IDENTIFIER);
    }

    public int getSyslogLevel() {
        return properties.getInteger(Property.SYSLOG_LEVEL);
    }

    public boolean isSyslogLevelPrefix() {
        return properties.getBoolean(Property.SYSLOG_LEVEL_PREFIX);
    }

    public int getSyslogPriority() {
        return properties.getInteger(Property.SYSLOG_PRIORITY);
    }

    public Vector<String> getSystemCallArchitectures() {
        return properties.getVector(Property.SYSTEM_CALL_ARCHITECTURES);
    }

    public int getSystemCallErrorNumber() {
        return properties.getInteger(Property.SYSTEM_CALL_ERROR_NUMBER);
    }

    public SystemCallFilter getSystemCallFilter() {
        Object[] array = (Object[]) properties.getVariant(Property.SYSTEM_CALL_FILTER).getValue();

        return new SystemCallFilter(array);
    }

    public String getTTYPath() {
        return properties.getString(Property.TTYPATH);
    }

    public boolean isTTYReset() {
        return properties.getBoolean(Property.TTYRESET);
    }

    public boolean isTTYVHangup() {
        return properties.getBoolean(Property.TTYVHANGUP);
    }

    public boolean isTTYVTDisallocate() {
        return properties.getBoolean(Property.TTYVTDISALLOCATE);
    }

    public boolean isTasksAccounting() {
        return properties.getBoolean(Property.TASKS_ACCOUNTING);
    }

    public BigInteger getTasksCurrent() {
        return properties.getBigInteger(Property.TASKS_CURRENT);
    }

    public BigInteger getTasksMax() {
        return properties.getBigInteger(Property.TASKS_MAX);
    }

    public long getTimeoutUSec() {
        return properties.getLong(Property.TIMEOUT_USEC);
    }

    public long getTimerSlackNSec() {
        return properties.getLong(Property.TIMER_SLACK_NSEC);
    }

    public boolean isTransparent() {
        return properties.getBoolean(Property.TRANSPARENT);
    }

    public long getUMask() {
        return properties.getLong(Property.UMASK);
    }

    public String getUser() {
        return properties.getString(Property.USER);
    }

    public String getUtmpIdentifier() {
        return properties.getString(Property.UTMP_IDENTIFIER);
    }

    public String getUtmpMode() {
        return properties.getString(Property.UTMP_MODE);
    }

    public String getWorkingDirectory() {
        return properties.getString(Property.WORKING_DIRECTORY);
    }

    public boolean isWritable() {
        return properties.getBoolean(Property.WRITABLE);
    }

}