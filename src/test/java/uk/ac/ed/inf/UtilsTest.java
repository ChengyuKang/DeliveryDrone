package uk.ac.ed.inf;

import org.junit.Test;
import uk.ac.ed.inf.beans.LongLat;
import uk.ac.ed.inf.beans.OrderRecord;
import uk.ac.ed.inf.controllers.RouteCalculator;
import uk.ac.ed.inf.parsers.Menus;
import uk.ac.ed.inf.parsers.NoFlyZone;
import uk.ac.ed.inf.parsers.Words;
import uk.ac.ed.inf.utils.DatabaseConnector;
import uk.ac.ed.inf.utils.DatabaseReader;
import uk.ac.ed.inf.utils.ServerConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilsTest {

    private static final String IP = "localhost";
    private static final String webport = "9898";
    private static final String dbport = "1527";
    private final LongLat gs1 = new LongLat(-3.189412,55.943441);
    private final LongLat gs2 = new LongLat( -3.187498,55.943452);
    private final LongLat noflypoint = new LongLat(-3.190597,55.945200);
    @Test
    public void dbConnectorTest(){
        DatabaseConnector dbConnector = new DatabaseConnector(IP, dbport);
        dbConnector.getStatement();
    }

    @Test
    public void dbReaderOrderTest(){
        //create database and server connector
        DatabaseConnector databaseConnector = new DatabaseConnector(IP,dbport);
        //create database reader
        DatabaseReader reader = new DatabaseReader(databaseConnector);
        //get order record resultSet from database
        //and transfer resultSet to arraylist
        ResultSet rsOrder = reader.readOrder(2022+"-"+12+"-"+20);

        ArrayList<OrderRecord> orderRecordList = reader.getOrderRecordList(rsOrder);
        //get orderDetails resultSet from database
        //and transfer resultSet to HashMap
        ResultSet rsOrderDetails = reader.readOrderDetails();
        HashMap<String,ArrayList<String>> orderDetails = reader.getOrderDetails(rsOrderDetails);
        // relate items with orders which have the same order number
        for (OrderRecord orderRecord : orderRecordList) {
            ArrayList<String> item = orderDetails.get(orderRecord.getOrderNo());
            orderRecord.setItem(item);
        }

    }

    @Test
    public void serverNoflyzoneTest(){
        ServerConnector serverConnector = new ServerConnector(IP,webport);
        Words words = new Words(serverConnector);
        NoFlyZone noFlyZone = new NoFlyZone(serverConnector);
        assertEquals(true,gs1.isInNoFlyZone(noflypoint,noFlyZone.getNoFlyZone()));
    }

    @Test
    public void dbReaderMenuTest(){

    }
    @Test
    public void testMenusOne() {
        ServerConnector server = new ServerConnector(IP, webport);
        // The webserver must be running on port 9898 to run this test.
        Menus menus = new Menus(server);
        int totalCost = menus.getDeliveryCost(

        new ArrayList<>(Arrays.asList("Ham and mozzarella Italian roll"))
        );
        // Don't forget the standard delivery charge of 50p
        assertEquals(230 + 50, totalCost);
    }

    @Test
    public void testMenusTwo() {
        ServerConnector server = new ServerConnector(IP, webport);
        // The webserver must be running on port 9898 to run this test.
        Menus menus = new Menus(server);
        int totalCost = menus.getDeliveryCost(
                new ArrayList<>(Arrays.asList("Ham and mozzarella Italian roll",
                "Salami and Swiss Italian roll"))
        );
        // Don't forget the standard delivery charge of 50p
        assertEquals(230 + 230 + 50, totalCost);
    }

    @Test
    public void testMenusThree() {
        ServerConnector server = new ServerConnector(IP, webport);
        // The webserver must be running on port 9898 to run this test.
        Menus menus = new Menus(server);
        int totalCost = menus.getDeliveryCost(
                new ArrayList<>(Arrays.asList("Ham and mozzarella Italian roll",
                        "Salami and Swiss Italian roll",
                        "Flaming tiger latte"))

        );
        // Don't forget the standard delivery charge of 50p
        assertEquals(230 + 230 + 460 + 50, totalCost);
    }



}
