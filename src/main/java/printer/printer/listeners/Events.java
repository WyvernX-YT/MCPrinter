package printer.printer.listeners;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.particles.Particles;
import net.minecraft.network.protocol.game.PacketPlayOutBlockAction;
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.TileEntity;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import printer.printer.Printer;
import printer.printer.items.ItemManager;
import printer.printer.util.Color;
import printer.printer.util.Config;
import printer.printer.util.GUI;

import javax.annotation.Nullable;
import java.util.*;

public class Events implements Listener {
    static ArrayList <Location> blocks = new ArrayList <>();
    Location loc;

    static ArrayList<UUID> cooldowns = new ArrayList <>();

    public static ArrayList <Material> bannedBlocks = new ArrayList <Material>();
    public static ArrayList <Material> nonClearables = new ArrayList<Material>();
    static {
        bannedBlocks.add(Material.CHEST);
        nonClearables.add(Material.BEDROCK);
        nonClearables.add(Material.END_PORTAL_FRAME);
        nonClearables.add(Material.NETHER_PORTAL);
        nonClearables.add(Material.END_PORTAL);
        nonClearables.add(Material.DRAGON_EGG);

        blocks.add(new Location(Bukkit.getWorld("World4"), -29,63,-201));
        blocks.add(new Location(Bukkit.getWorld("World4"), -30,63,-202));
        blocks.add(new Location(Bukkit.getWorld("World4"), -30,63,-201));
        blocks.add(new Location(Bukkit.getWorld("World4"), -29,63,-202));

    }


    @EventHandler
    void onBlockPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        ItemStack i = event.getItemInHand();
        Block b = event.getBlock();
        if (i.equals(ItemManager.pocketPrinter)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack i = event.getItem();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (i != null) {

                if (i.equals(ItemManager.printerWand)) {

                    if (!Config.getSection("permissions").getConfigurationSection("playerUseWand").getBoolean("enabled")) {
                        if (p.hasPermission(Config.getSection("permissions").getConfigurationSection("playerUseWand").getString("permission"))) {
                            if (block != null) {
                                if (block.getType().equals(Material.CHEST)) {
                                    Chest chest = (Chest)block.getState();
                                    if (chest.getCustomName() != null && chest.getCustomName().equals(ItemManager.printerChest.getItemMeta().getDisplayName())) {
                                        p.sendMessage(Color.colorize("&cAttempting to print now"));
                                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
                                        event.setCancelled(true);
                                        if (chest.getInventory() instanceof DoubleChestInventory) {
                                            DoubleChest dchest = (DoubleChest)chest.getInventory().getHolder();
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    print(chest, dchest.getInventory(), p, false, null, null);
                                                }
                                            }.runTaskLater(Printer.instance, 40);

                                        }
                                        else {
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    print(chest, chest.getInventory(), p, false, null, null);
                                                }
                                            }.runTaskLater(Printer.instance, 40);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (block != null) {
                            if (block.getType().equals(Material.CHEST)) {
                                Chest chest = (Chest)block.getState();
                                if (chest.getCustomName() != null && chest.getCustomName().equals(ItemManager.printerChest.getItemMeta().getDisplayName())) {
                                    p.sendMessage(Color.colorize("&cAttempting to print now"));
                                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
                                    event.setCancelled(true);
                                    if (chest.getInventory() instanceof DoubleChestInventory) {
                                        DoubleChest dchest = (DoubleChest)chest.getInventory().getHolder();
                                        new BukkitRunnable() {

                                            @Override
                                            public void run() {
                                                print(chest, dchest.getInventory(), p, false, null, null);
                                            }
                                        }.runTaskLater(Printer.instance, 40);
                                    }
                                    else {
                                        new BukkitRunnable() {

                                            @Override
                                            public void run() {
                                                print(chest, chest.getInventory(), p, false, null, null);
                                            }
                                        }.runTaskLater(Printer.instance, 40);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            if (i != null) {
                if(i.equals(ItemManager.teleporter)) {
                    for(Player pl : Bukkit.getOnlinePlayers()) {

                        if (!cooldowns.contains(p.getUniqueId())) {
                            Teleport(p, 15);
                            pl.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2, 1);
                            pl.spawnParticle(Particle.EXPLOSION_LARGE, p.getLocation(), 0, 0, 0, 0, 0.001f);
                            for(Entity e : p.getNearbyEntities(6, 6, 6)) {
                                if(!(e instanceof Player)) {
                                    if (!e.isDead()) {
                                        if (e instanceof LivingEntity) {
                                            LivingEntity entity = (LivingEntity) e;
                                            entity.damage(10000
                                            );
                                        }
                                    }
                                }
                            }
                            cooldowns.add(p.getUniqueId());
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    cooldowns.remove(p.getUniqueId());
                                }
                            }.runTaskLater(Printer.instance, 2);
                        }
                    }
                }
                if (i.equals(ItemManager.pocketPrinter)) {
                    if (event.getClickedBlock() != null) {
                        loc = event.getClickedBlock().getLocation();
                    }
                    else {
                        loc = null;
                    }
                    GUI ui = new GUI(Color.colorize(ItemManager.pocketPrinter.getItemMeta().getDisplayName()), 54) {
                        @Override
                        public Inventory getInventory() {
                            return super.getInventory();
                        }


                        @Override
                        public String getTitle() {
                            return super.getTitle();
                        }
                    };
                    p.openInventory(ui.getInventory());
                }
            }
        }
    }

    @EventHandler
    public void onEntityTakeDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                p.getWorld().strikeLightning(e.getEntity().getLocation());

            }
        }
    }

    @EventHandler
    void onInventoryClose(InventoryCloseEvent e) {
        Inventory inv = e.getInventory();
        Player p = (Player)e.getPlayer();

        if (e.getView().getTitle().equals(Color.colorize(ItemManager.pocketPrinter.getItemMeta().getDisplayName()))) {
            if (loc == null) {
                loc = p.getTargetBlock(null, 10).getLocation();
            }
            Material prevBlockType = loc.clone().getBlock().getType();
            if (!bannedBlocks.contains(loc.getBlock().getType())) {
                loc.getBlock().setType(Material.CHEST);
                Chest ch = (Chest)loc.getBlock().getState();
                ch.setCustomName(Color.colorize(ItemManager.pocketPrinter.getItemMeta().getDisplayName()));
                blocks.add(loc);

                print(ch, inv, p, true, loc, prevBlockType);
            }
            else {
                p.sendMessage(Color.colorize("&c&lCannot replace unbreakable blocks/banned blocks"));
            }

        }
    }

    @EventHandler
    void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.CHEST)) {
            Chest chest = (Chest)event.getBlock().getState();
            if (blocks.contains(event.getBlock().getLocation())) {
                event.setCancelled(true);
            }
            if (chest.getCustomName() != null) {
                if (chest.getCustomName().equals(ItemManager.printerChest.getItemMeta().getDisplayName())) {
                    event.setDropItems(false);
                    if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                        event.getPlayer().getInventory().addItem(ItemManager.printerChest);
                    }
                }
            }
        }
    }

    private void print(Chest ch, Inventory toPrint, Player p, boolean isPocket, @Nullable Location loc, @Nullable Material prevBlockType) {
        final int[] i = {0};
        Iterator <ItemStack> it = toPrint.iterator();
        Collection <Entity> players = Bukkit.getWorld(p.getWorld().getUID()).getNearbyEntities(p.getLocation(), 10, 10, 10);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (it.hasNext()) {
                    if (isPocket) {
                        if (loc != null) {
                            //nms later
                        }
                    }
                    ItemStack stack = it.next();
                    if (stack != null) if (stack.getAmount() <= 1) {
                        if (!stack.getType().isBlock()) {
                            p.sendMessage(Color.colorize("&cOne of your items where not a block so we gave it to you"));
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 0.1f);
                            if (p.getInventory().firstEmpty() == -1) {
                                p.getWorld().dropItem(p.getLocation(), stack);
                            }
                            else {
                                p.getInventory().addItem(stack);
                            }
                            stack.setType(Material.AIR);
                        }
                    }
                    else {
                        ItemStack stack2 = stack.clone();
                        stack2.setAmount(stack.getAmount() - 1);
                        p.sendMessage(Color.colorize("&cYou had a stack of blocks more than 1 we gave you back the rest"));
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 0.1f);
                        if (p.getInventory().firstEmpty() == -1) {
                            p.getWorld().dropItem(p.getLocation(), stack2);
                        }
                        else {
                            p.getInventory().addItem(stack2);
                        }
                        stack.setAmount(1);
                    }
                    if (ch.getInventory() instanceof DoubleChestInventory) {
                        Block b = ch.getBlock();
                        int x = i[0] % 9;
                        int y = (toPrint.getSize() / 9) - (i[0] / 9) - 1;
                        b = b.getLocation().add(-4, 0, 0).getBlock();
                        if (stack != null) {
                            if(!nonClearables.contains(b.getType())) {
                                b.getLocation().add(x, y + 2, 0).getBlock().setType(stack.getType());
                            }
                            for (Entity e: players) {
                                if (e instanceof Player) {
                                    ((Player)e).playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 2);
                                    ((Player)e).spawnParticle(Particle.FLAME, ch.getLocation().add(0.5, y + 2.5, 0.5), 0);
                                }
                            }

                            toPrint.setItem(i[0], new ItemStack(Material.AIR));
                        }
                        else {
                        }

                    }
                    else {
                        Block b = ch.getBlock();
                        int x = i[0] % 9;
                        int y = (toPrint.getSize() / 9) - (i[0] / 9) - 1;
                        b = b.getLocation().add(-4, 0, 0).getBlock();
                        if (stack != null) {
                            if(!nonClearables.contains(b.getType())) {
                                b.getLocation().add(x, y + 2, 0).getBlock().setType(stack.getType());
                            }


                            for (Entity e: players) {
                                if (e instanceof Player) {
                                    ((Player)e).playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 2);
                                    ((Player)e).spawnParticle(Particle.FLAME, ch.getLocation().add(0.5, y + 2.5, 0.5), 0);
                                }
                            }
                            toPrint.setItem(i[0], new ItemStack(Material.AIR));
                        }
                        else {
                        }

                    }

                    i[0]++;
                }
                else {
                    p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 2, 1);
                    p.sendMessage(Color.colorize(Color.colorize("&aCOMPLETE!")));
                    if (isPocket) {

                        if (prevBlockType != null) {
                            Material newBlockType = null;
                            if (prevBlockType.equals(Material.CHEST)) {
                                newBlockType = Material.AIR;
                            }
                            if (newBlockType != null) {
                                ch.getBlock().setType(newBlockType);
                            }
                            else {
                                ch.getBlock().setType(prevBlockType);
                            }
                        }
                        blocks.remove(loc);
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer(Printer.instance, 0, 1L);
    }
    public static void Teleport(Player player, int distance)
    {
        try
        {
            int f_ = distance;
            for(int range = 1; range < distance; range++) {
                Location location = player.getTargetBlock(null, range).getLocation();
                if (location.getBlock().getType() != Material.AIR && location.getBlock().getType() != Material.VOID_AIR && location.getBlock().getType() != Material.CAVE_AIR)
                {
                    f_ = range;
                    break;
                }
            }
            Location location = player.getTargetBlock(null, f_ - 1).getLocation();
            location.setYaw(player.getLocation().getYaw());
            location.setPitch(player.getLocation().getPitch());
            location.add(0.5, 0, 0.5);
            if (f_ != distance)
            {
                player.sendMessage(ChatColor.RED + "There are blocks in the way!");
            }
            if (f_ > 1) {
                player.teleport(location);
            }
            else player.teleport(player.getLocation());
        }
        catch (IllegalStateException ex) {} // suppress bullshit errors thrown by Player#getTargetBlock
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 3f, 1f);

    }


    @EventHandler
    void onBlockRedstoneEvent(BlockRedstoneEvent e) {
        if(e.getBlock().getState() instanceof Chest) {
            Chest ch = (Chest) e.getBlock().getState();
            if(ch.getCustomName() != null && ch.getCustomName().equals(ItemManager.printerChest.getItemMeta().getDisplayName())) {
                ArrayDeque<Entity> entities = new ArrayDeque<Entity>(e.getBlock().getWorld().getNearbyEntities(e.getBlock().getLocation(), 100, 100, 100));

                while(!(entities.getFirst() instanceof Player)) {
                    entities.removeFirst();
                }
                Player p = (Player)entities.getFirst();
                print(ch, ch.getInventory(), p, false, null, null);
            }
        }
    }
}
