package imdutch21.oilcraft.network;

import imdutch21.oilcraft.tileentity.TileEntityOilGenerator;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Bart on 29/03/2016.
 */
public class DirectionButtonPacketProcessor implements IMessage, IMessageHandler<DirectionButtonPacketProcessor, IMessage> {
    private int buttonID;
    private int dimension;
    private BlockPos pos;

    public DirectionButtonPacketProcessor() {

    }

    public DirectionButtonPacketProcessor(int buttonID, BlockPos pos, int dimension) {
        this.buttonID = buttonID;
        this.dimension = dimension;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        PacketBuffer buff = new PacketBuffer(buffer);
        dimension = buff.readInt();
        pos = buff.readBlockPos();
        buttonID = buff.readInt();
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        PacketBuffer buff = new PacketBuffer(buffer);
        buff.writeInt(dimension);
        buff.writeBlockPos(pos);
        buff.writeInt(buttonID);
    }

    @Override
    public IMessage onMessage(DirectionButtonPacketProcessor message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            message.onMessageFromClient();
        }
        return null;
    }

    public void onMessageFromClient() {
        World world = DimensionManager.getWorld(dimension);
        if (world != null && pos != null) {
            TileEntity tileEntity = world.getTileEntity(pos);

            if (tileEntity instanceof TileEntityOilGenerator) {
                ((TileEntityOilGenerator) tileEntity).switchOutput(buttonID);
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
            }
        }
    }
}
