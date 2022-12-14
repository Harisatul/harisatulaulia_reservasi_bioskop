package org.binar.orderschedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.binar.orderschedule.Service.OrderService;
import org.binar.orderschedule.Service.SeatService;
import org.binar.orderschedule.exception.ExceptionResponse;
import org.binar.orderschedule.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@RestController
@RequestMapping("cinema/api/v1/orders")
public class OrderController {

    private SeatService seatService;
    private OrderService orderService;

    public OrderController(SeatService seatService, OrderService orderService) {
        this.seatService = seatService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/generatePdf", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            tags = {"Order"},
            operationId = "generateInvoice",
            summary = "generate invoice in pdf format. ",
            description = "to generate invoice",
            parameters = {
                    @Parameter(name = "orderId", description = "this is is Premiered condition. required to generate pdf invoice based on orderId",
                            example = "3",schema = @Schema(type = "Long"), in = ParameterIn.PATH)},
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<String> getInvoicePrint(HttpServletResponse response,
                                                       @RequestParam(name = "orderId") Long orderId) throws IOException, JRException {

        JasperPrint jasperPrint = orderService.generateInvoice(orderId);
        response.setContentType("application/x-download");
        response.addHeader("Content-disposition", "attachment; filename=invoice_report.pdf");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint,out);
//        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "success");
        return new ResponseEntity<>("successfully generated", HttpStatus.OK);
    }

    @GetMapping("/seat")
    @Operation(
            tags = {"Order"},
            operationId = "getSeatAvailable",
            summary = "get all seat available based on schedule",
            description = "to fetch all seat available",
            parameters = {
                    @Parameter(name = "scheduleId", description = "this is schedule id. required to fetch seat availability based on certain schedule",
                            example = "3",schema = @Schema(type = "Long"))},
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<ApiResponse> checkSeatAvailability(@RequestParam(value = "scheduleId") Long schedule){
        ApiResponse allseatAvailable = seatService.getAllseatAvailable(schedule, true);
        return new ResponseEntity<>(allseatAvailable, HttpStatus.OK);
    }

}
