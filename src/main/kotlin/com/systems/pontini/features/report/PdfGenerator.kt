package com.systems.pontini.features.report

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import java.io.ByteArrayOutputStream

object PdfGenerator {
    fun generatePdf(): ByteArray {
        // Cria um novo documento PDF
        val document = PDDocument()

        // Cria uma nova página
        val page = PDPage(PDRectangle.A4)
        document.addPage(page)

        // Inicia o fluxo de conteúdo para a página
        val contentStream = PDPageContentStream(document, page)

        // Define o número de colunas e linhas da tabela
        val numColumns = 3
        val numRows = 3

        // Define as dimensões da tabela
        val tableWidth = 500f
        val tableHeight = 200f
        val cellWidth = tableWidth / numColumns
        val cellHeight = tableHeight / numRows

        // Define a posição da tabela na página
        val startX = 50f
        val startY = 700f

        // Define a fonte do texto
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)

        // Desenha as linhas da tabela
        for (i in 0..numRows) {
            contentStream.drawLine(startX, startY - i * cellHeight, startX + tableWidth, startY - i * cellHeight)
        }
        for (i in 0..numColumns) {
            contentStream.drawLine(startX + i * cellWidth, startY, startX + i * cellWidth, startY - tableHeight)
        }

        // Define o conteúdo das células
        val tableData = arrayOf(
            arrayOf("Item 1", "Description 1", "10.00"),
            arrayOf("Item 2", "Description 2", "20.00"),
            arrayOf("Item 3", "Description 3", "30.00")
        )

        // Escreve o conteúdo nas células
        for (rowIndex in 0 until numRows) {
            for (columnIndex in 0 until numColumns) {
                val cellX = startX + columnIndex * cellWidth
                val cellY = startY - (rowIndex + 1) * cellHeight
                val cellText = tableData[rowIndex][columnIndex]
                contentStream.beginText()
                contentStream.newLineAtOffset(cellX + 5, cellY + 15)
                contentStream.showText(cellText)
                contentStream.endText()
            }
        }
        // Finaliza o fluxo de conteúdo
        contentStream.close()

        val outputStream = ByteArrayOutputStream()
        document.save(outputStream)


        return outputStream.toByteArray()
    }

}