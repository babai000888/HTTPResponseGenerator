package ru.babai.http.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// files from https://www.online-convert.com/file-type

@RestController
@RequestMapping("/httpResponse")
public class MessageController {

    @GetMapping("/getFile")
    @Operation(summary = "Download a file, get MIME, get HTTP status")
    public ResponseEntity<StreamingResponseBody> getPDF(
            @Parameter(name = "url", description = "To get a file - select the URL", schema = @Schema(type = "java.net.URL",
                    allowableValues = {
                            "https://example-files.online-convert.com/document/pdf/example.pdf",
                            "https://example-files.online-convert.com/raster%20image/jpeg/example.jpeg",
                            "https://example-files.online-convert.com/document/xml/example.xml",
                            "https://example-files.online-convert.com/audio/mp3/example.mp3"
                    }))
            @RequestParam(required = false) URL url,

            @Parameter(name = "contentType", description = "selesct MIME type (the Media type of the body)", required = false, schema = @Schema(type = "org.springframework.http.MediaType",
                    allowableValues = {
                            "multipart/form-data",
                            "application/octet-stream",
                            "application/pdf"
                    }))
            @RequestParam(required = false) String contentType,

            @Parameter(name = "httpStatusCode", description = "select HTTP status code)", required = false, schema = @Schema(type = "org.springframework.http.HttpStatusCode",
                    allowableValues = {
                            "200",
                            "204",
                            "302",
                            "400",
                            "401",
                            "404",
                            "408",
                            "500",
                            "502"
                    }))
            @RequestParam(required = false) String httpStatusCode

    ) throws IOException {

        url = url != null ? url : new URL("https://example-files.online-convert.com/document/pdf/example.pdf");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        StreamingResponseBody stream = outputStream -> outputStream.write(connection.getInputStream().readAllBytes());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.valueOf(contentType));
        responseHeaders.setContentDisposition(ContentDisposition.attachment().filename(new File(url.getFile()).getName()).build());
        return new ResponseEntity<>(stream, responseHeaders, HttpStatusCode.valueOf(Integer.parseInt(httpStatusCode)));
    }
}
