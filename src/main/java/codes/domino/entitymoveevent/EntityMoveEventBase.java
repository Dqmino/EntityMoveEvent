package codes.domino.entitymoveevent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class EntityMoveEventBase {
    private final static EntityMoveEventBase instance = new EntityMoveEventBase();

    public Set<Entity> getImmutableMarkedEntities() {
        return Collections.unmodifiableSet(markedEntities.keySet());
    }
    public void markEntity(Entity entity) {
        markedEntities.put(entity, entity.getLocation());
    }

    public void unmarkEntity(Entity entity) {
        markedEntities.remove(entity);
    }

    private final Map<Entity, Location> markedEntities = new HashMap<>();

    public static void inject(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Entity entity : instance.markedEntities.keySet()) {
                if (entity.isDead() || !entity.isValid()) {
                    instance.unmarkEntity(entity);
                    continue;
                }
                Location previousLocation = instance.markedEntities.get(entity);
                if (previousLocation == null) {
                    instance.markedEntities.put(entity, entity.getLocation());
                    continue;
                }
                if (entity.getLocation().equals(previousLocation)) {
                    continue;
                }
                EntityMoveEvent entityMoveEvent = new EntityMoveEvent(entity, previousLocation, entity.getLocation());
                Bukkit.getServer().getPluginManager().callEvent(entityMoveEvent);
                if (entityMoveEvent.isCancelled()) {
                    entity.teleport(previousLocation);
                    continue;
                }
                instance.markedEntities.put(entity, entity.getLocation());
            }
        }, 0, 1);
    }
}
