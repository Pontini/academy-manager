package com.systems.pontini.routes

import com.systems.pontini.features.report.PdfGenerator
import io.ktor.http.*
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.reportRoutes() {

    routing {
        route("/report") {
            get  ("/download") {
                val pdfBytes = PdfGenerator.generatePdf()
                call.response.header(HttpHeaders.ContentDisposition, "attachment; filename=\"hello_world.pdf\"")
                call.respondBytes(pdfBytes, ContentType.Application.Pdf)
            }
        }
    }
}