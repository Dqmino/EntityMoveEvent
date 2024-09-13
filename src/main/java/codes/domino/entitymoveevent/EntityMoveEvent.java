package codes.domino.entitymoveevent;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public final class EntityMoveEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    private final Location from;
    private final Location to;

    public EntityMoveEvent(Entity entity, Location from, Location to) {
        super(entity);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    @Override
    public @NotNull EntityType getEntityType() {
        return super.getEntityType();
    }

    @Override
    public @NotNull Entity getEntity() {
        return super.getEntity();
    }
}
