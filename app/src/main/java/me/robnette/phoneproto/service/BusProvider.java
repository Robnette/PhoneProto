package me.robnette.phoneproto.service;

import com.squareup.otto.Bus;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public BusProvider(){}
}
