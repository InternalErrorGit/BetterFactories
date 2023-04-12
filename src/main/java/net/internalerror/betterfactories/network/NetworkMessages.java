package net.internalerror.betterfactories.network;

import lombok.experimental.UtilityClass;
import net.internalerror.betterfactories.network.packet.EnergySyncS2CPacket;
import net.internalerror.betterfactories.network.packet.ItemStackSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

@UtilityClass
public final class NetworkMessages {
  
  private static SimpleChannel channel;
  private static int packetId = 0;
  
  private static int nextPacketId() {
    return packetId++;
  }
  
  public static void register(){
    channel = NetworkRegistry.ChannelBuilder
        .named(new ResourceLocation(MOD_ID, "messages"))
        .networkProtocolVersion(() -> "1.0")
        .clientAcceptedVersions(s -> true)
        .serverAcceptedVersions(s -> true)
        .simpleChannel();
    
    channel.messageBuilder(EnergySyncS2CPacket.class, nextPacketId(), NetworkDirection.PLAY_TO_CLIENT)
        .decoder(EnergySyncS2CPacket::new)
        .encoder(EnergySyncS2CPacket::toBytes)
        .consumerMainThread(EnergySyncS2CPacket::handle)
        .add();
    
    channel.messageBuilder(ItemStackSyncS2CPacket.class, nextPacketId(), NetworkDirection.PLAY_TO_CLIENT)
        .decoder(ItemStackSyncS2CPacket::new)
        .encoder(ItemStackSyncS2CPacket::toBytes)
        .consumerMainThread(ItemStackSyncS2CPacket::handle)
        .add();
    
  }
  
  public static <M> void sendToServer(M message) {
    channel.sendToServer(message);
  }
  
  public static <M> void sendToPlayer(M message, ServerPlayer player) {
    channel.send(PacketDistributor.PLAYER.with(() -> player), message);
  }
  
  public static <M> void sendToClients(M message) {
    channel.send(PacketDistributor.ALL.noArg(), message);
  }
  
}
