package com.kib.order.processor;

import com.kib.order.dto.AvgCSV;
import com.kib.order.dto.OrderCSV;
import com.kib.order.dto.TopCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Processor bean used to do calculations for OrderCSVRouteBuilder
 */
public class OrderBean {

    /**
     * Takes List of order and calculate average orders per item
     *
     * @param orders
     * @return List of Average orders
     */
    public List<AvgCSV> calculateAvg(List<OrderCSV> orders) {

        double totalOrders = orders.size();
        //Group order/total count
        Map<String, Integer> ordersCount = orders.stream()
                .collect(Collectors.groupingBy(OrderCSV::getName,
                        Collectors.mapping(OrderCSV::getQuantity, Collectors.summingInt(Integer::intValue))));

        List<AvgCSV> avgCSVS = new ArrayList<>();
        for (String key : ordersCount.keySet()) {
            //Convert order/total count map to list of order/avg
            avgCSVS.add(new AvgCSV(key, ordersCount.get(key) / totalOrders));
        }

        return avgCSVS;
    }

    public List<TopCSV> calculateTop(List<OrderCSV> orders) {

        //Group order/total orders
        Map<String, List<OrderCSV>> ordersCount = orders.stream()
                .collect(Collectors.groupingBy(OrderCSV::getName,
                        Collectors.toList()));

        TopCSV topCSV = null;
        List<TopCSV> topCSVs = new ArrayList<>();
        for (String name : ordersCount.keySet()) {
            //Group brand/total orders
            Map<String, List<OrderCSV>> ordersMap = ordersCount
                    .get(name).stream()
                    .collect(Collectors.groupingBy(OrderCSV::getBrand, Collectors.toList()));

            //Convert them to list of top orders
            int brandOrdersCount = 0;
            for (String brand : ordersMap.keySet()) {
                brandOrdersCount = ordersMap.get(brand).size();
                if (topCSV == null) {
                    topCSV = new TopCSV(name, brand);
                    topCSV.setTotalOrders(brandOrdersCount);
                    continue;
                }

                if (brandOrdersCount > topCSV.getTotalOrders()) {
                    topCSV = new TopCSV(name, brand);
                    topCSV.setTotalOrders(brandOrdersCount);
                }
            }
            topCSVs.add(topCSV);
        }

        return topCSVs;
    }

    public static void main(String[] args) {

        List<OrderCSV> orders = new ArrayList<>();
        orders.add(new OrderCSV("ID1", "Minneapolis", "shoes", 2, "Air"));
        orders.add(new OrderCSV("ID2", "Chicago", "shoes", 1, "Air"));
        orders.add(new OrderCSV("ID3", "Central Department Store", "shoes", 5, "BonPied"));
        orders.add(new OrderCSV("ID4", "Quail Hollow", "forks", 3, "Pfitzcraft"));

//        Map<String, Integer> ordersCount = orders.stream()
//                .collect(Collectors.groupingBy(OrderCSV::getName,
//                        Collectors.mapping(OrderCSV::getQuantity, Collectors.summingInt(Integer::intValue))));
//
//        double totalOrders = (double) orders.size();
//        List<AvgCSV> avgCSVS = new ArrayList<>();
//        for (String key : ordersCount.keySet()) {
//            avgCSVS.add(new AvgCSV(key, ordersCount.get(key) / totalOrders));
//        }
//
//        avgCSVS.forEach(System.out::println);

        Map<String, List<OrderCSV>> ordersCount = orders.stream()
                .collect(Collectors.groupingBy(OrderCSV::getName,
                        Collectors.toList()));

        TopCSV topCSV = null;
        List<TopCSV> topCSVs = new ArrayList<>();
        for (String name : ordersCount.keySet()) {
            Map<String, List<OrderCSV>> ordersMap = ordersCount
                    .get(name).stream()
                    .collect(Collectors.groupingBy(OrderCSV::getBrand, Collectors.toList()));

            int brandOrdersCount = 0;
            for (String brand : ordersMap.keySet()) {
                brandOrdersCount = ordersMap.get(brand).size();
                if (topCSV == null) {
                    topCSV = new TopCSV(name, brand);
                    topCSV.setTotalOrders(brandOrdersCount);
                    continue;
                }

                if (brandOrdersCount > topCSV.getTotalOrders()) {
                    topCSV = new TopCSV(name, brand);
                    topCSV.setTotalOrders(brandOrdersCount);
                }
            }
            topCSVs.add(topCSV);
        }

        System.out.println(topCSVs);
    }
}
