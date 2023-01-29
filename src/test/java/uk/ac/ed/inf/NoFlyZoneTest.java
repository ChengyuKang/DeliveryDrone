package uk.ac.ed.inf;

import org.junit.Test;
import uk.ac.ed.inf.beans.LongLat;
import uk.ac.ed.inf.parsers.NoFlyZone;
import uk.ac.ed.inf.parsers.Words;
import uk.ac.ed.inf.utils.ServerConnector;

import static org.junit.Assert.assertEquals;

public class NoFlyZoneTest {
    private static final String IP = "localhost";
    private static final String webport = "9898";
    private final LongLat appletonTower = new LongLat(-3.186874, 55.944494);
    private final LongLat businessSchool = new LongLat(-3.1873,55.9430);
    private final LongLat greyfriarsKirkyard = new LongLat(-3.1928,55.9469);
    private final LongLat gs1 = new LongLat(-3.189412,55.943441);
    private final LongLat gs2 = new LongLat( -3.187498,55.943452);
    private final LongLat noflypoint = new LongLat(-3.190597,55.945200);



    @Test
    public void OutConfinedShouldReturnFalse1(){
        LongLat longLat = new LongLat(-3.174319, 55.942917);
        assertEquals(false,longLat.isConfined());
    }

    @Test
    public void OutConfinedShouldReturnFalse2(){
        LongLat longLat = new LongLat(-3.164319, 55.941617);
        assertEquals(false,longLat.isConfined());
    }

    @Test
    public void BoundaryConfinedShouldReturnFalse1(){
        LongLat longLat = new LongLat(-3.164319, 55.941617);
        assertEquals(false,longLat.isConfined());
    }

    @Test
    public void BoundaryConfinedShouldReturnFalse2(){
        LongLat longLat = new LongLat(-3.164319, 55.946233);
        assertEquals(false,longLat.isConfined());
    }
    @Test
    public void InConfinedShouldReturnTrue1(){
        LongLat longLat = new LongLat(-3.189919, 55.942637);
        assertEquals(true,longLat.isConfined());
    }

    @Test
    public void serverConnectionShouldReturnTrue1(){
        ServerConnector serverConnector = new ServerConnector(IP,webport);
        Words words = new Words(serverConnector);
        NoFlyZone noFlyZone = new NoFlyZone(serverConnector);
        assertEquals(true,noFlyZone.getNoFlyZone()!=null);

    }

    @Test
    public void ValidRouteShouldReturnFalse1(){
        ServerConnector serverConnector = new ServerConnector(IP,webport);
        Words words = new Words(serverConnector);
        NoFlyZone noFlyZone = new NoFlyZone(serverConnector);
        assertEquals(false,gs1.isInNoFlyZone(gs2,noFlyZone.getNoFlyZone()));

    }

    @Test
    public void ValidRouteShouldReturnFalse2(){
        ServerConnector serverConnector = new ServerConnector(IP,webport);
        Words words = new Words(serverConnector);
        NoFlyZone noFlyZone = new NoFlyZone(serverConnector);
        assertEquals(false,gs1.isInNoFlyZone(appletonTower,noFlyZone.getNoFlyZone()));

    }

    @Test
    public void inValidRouteShouldReturnTrue1(){
        ServerConnector serverConnector = new ServerConnector(IP,webport);
        Words words = new Words(serverConnector);
        NoFlyZone noFlyZone = new NoFlyZone(serverConnector);
        assertEquals(true,gs1.isInNoFlyZone(noflypoint,noFlyZone.getNoFlyZone()));

    }

    @Test
    public void inValidRouteShouldReturnTrue2(){
        ServerConnector serverConnector = new ServerConnector(IP,webport);
        Words words = new Words(serverConnector);
        NoFlyZone noFlyZone = new NoFlyZone(serverConnector);
        assertEquals(true,noflypoint.isInNoFlyZone(appletonTower,noFlyZone.getNoFlyZone()));

    }


}
