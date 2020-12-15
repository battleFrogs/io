package com.gjc.io.o_group_create.communicate.code;

import com.gjc.io.o_group_create.communicate.packet.Packet;
import com.gjc.io.o_group_create.communicate.packet.impl.*;
import com.gjc.io.o_group_create.communicate.serialize.JSONSerializer;
import com.gjc.io.o_group_create.communicate.serialize.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.gjc.io.o_group_create.communicate.constant.Command.*;

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

    public static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public void encode(ByteBuf byteBuf, Packet packet) {

//        // 1.创建ByteBuf 对象
//        ByteBuf byteBuf = byteBufAllocator.ioBuffer();

        // 序列化java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

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
