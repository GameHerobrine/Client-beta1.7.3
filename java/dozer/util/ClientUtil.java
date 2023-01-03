package dozer.util;

import dozer.Dozer;
import dozer.event.EventBus;

public interface ClientUtil {

    /**
     * @return dozer from dozerhack
     */
    default Dozer getDozer() {
        return Dozer.getSingleton();
    }

    /**
     * @return eventbus from dozerhack
     */
    default EventBus getEventBus() {
        return getDozer().getEventBus();
    }


}