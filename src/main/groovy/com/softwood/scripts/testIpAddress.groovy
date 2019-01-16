package com.softwood.scripts

import com.softwood.domain.customUserTypes.IpAddress

import java.nio.ByteBuffer
import java.nio.ByteOrder

InetAddress ip = Inet4Address.getLocalHost()
String host = ip.hostAddress

println "host = $host "


ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.BIG_ENDIAN)

buffer.put ([0,0,0,0] as byte[])
buffer.put (ip.getAddress() )
buffer.position (0)

long ipAsLong = buffer.getLong()

println "ipAsLong : $ipAsLong"
IpAddress ipAdd = new IpAddress (ipAddress:ip)

println ipAdd.getAddress()