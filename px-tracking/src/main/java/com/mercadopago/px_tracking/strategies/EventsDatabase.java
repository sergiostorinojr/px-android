package com.mercadopago.px_tracking.strategies;

import com.mercadopago.px_tracking.model.EventTrackIntent;

public interface EventsDatabase {

    void addTrack(EventTrackIntent eventTrackIntent);

    Integer batchSize();

    EventTrackIntent retrieveBatch();

    void rollback();
}
