package com.kib.order.route;

import com.kib.order.dto.AvgCSV;
import com.kib.order.dto.OrderCSV;
import com.kib.order.dto.TopCSV;
import com.kib.order.processor.OrderBean;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.model.dataformat.BindyType;

public class OrderCSVRouteBuilder extends EndpointRouteBuilder {

    @Override
    public void configure() throws Exception {
        from(file("{{folderPath}}").fileName("{{fileName}}").noop(true))
                .multicast().to("direct:avg", "direct:top");

        from("direct:avg")
                .routeId("Average-Route")
                .unmarshal().bindy(BindyType.Csv, OrderCSV.class)
                .bean(OrderBean.class, "calculateAvg")
                .marshal().bindy(BindyType.Csv, AvgCSV.class)
        .to(file("{{folderPath}}").fileName("0-{{fileName}}"));

        from("direct:top")
                .routeId("Top-Route")
                .unmarshal().bindy(BindyType.Csv, OrderCSV.class)
                .bean(OrderBean.class, "calculateTop")
                .marshal().bindy(BindyType.Csv, TopCSV.class)
        .to(file("{{folderPath}}").fileName("1-{{fileName}}"));
    }
}
