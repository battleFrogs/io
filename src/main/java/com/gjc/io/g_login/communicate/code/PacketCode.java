package com.gjc.io.g_login.communicate.code;

import com.gjc.io.g_login.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.g_login.communicate.packet.impl.LoginResponsePacket;
import com.gjc.io.g_login.communicate.packet.Packet;
import com.gjc.io.g_login.communicate.serialize.JSONSerializer;
import com.gjc.io.g_login.communicate.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.gjc.io.g_login.communicate.constant.Command.*;

public class PacketCode {


    public static PacketCode getInstance() {
        return InnerPackCodeC.INSTANCE.getInstance();
    }

    private enum InnerPackCodeC {
        INSTANCE,
        ;
        private PacketCode packetCodeC;

        private InnerPackCodeC() {
            packetCodeC = new PacketCode();
        }

        public PacketCode getInstance() {
            return packetCodeC;
        }

    }

    private static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {

        // 1.创建ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();

        // 序列化java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4);

        byteBuf.skipBytes(1);

        byte serializeAlgorithm  = byteBuf.readByte();
        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;

    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

}
