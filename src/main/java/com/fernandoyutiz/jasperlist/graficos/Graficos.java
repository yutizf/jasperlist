package com.fernandoyutiz.jasperlist.graficos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Graficos {
    public static BufferedImage generaSello(String[] textLines){
        // Las líneas de texto a dibujar
        //String[] textLines = {
        //        "Primera línea de texto",
        //        "Segunda línea de texto",
        //        "Tercera línea de texto",
        //};
        try {
            int width = 500;
            int height = 400;

            // Crear una imagen en blanco con transparencia
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            // Activar anti-aliasing para un mejor renderizado del texto
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Establecer el fondo transparente
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
            g2d.fillRect(0, 0, width, height);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

            // Configurar la fuente y el color del texto
            g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
            g2d.setColor(Color.BLACK);



            // Obtener las métricas de la fuente para centrar el texto
            FontMetrics metrics = g2d.getFontMetrics();
            int lineHeight = metrics.getHeight();

            // Calcular la altura total del texto
            int textHeight = lineHeight * textLines.length;

            // Configurar la posición del texto
            int x = (width - metrics.stringWidth(textLines[0])) / 2;
            int y = (height - textHeight) / 2 + metrics.getAscent();

            // Calcular el ancho máximo del texto
            int maxWidth = 0;
            for (String line : textLines) {
                int lineWidth = metrics.stringWidth(line);
                if (lineWidth > maxWidth) {
                    maxWidth = lineWidth;
                }
            }

            // Establecer la rotación
            AffineTransform originalTransform = g2d.getTransform();
            AffineTransform rotatedTransform = new AffineTransform();
            rotatedTransform.rotate(Math.toRadians(-45), width / 2, height / 2);
            g2d.setTransform(rotatedTransform);

            // Dibujar el rectángulo redondeado
            int arcWidth = 20;
            int arcHeight = 20;
            int rectX = x - 10;
            int rectY = y - metrics.getAscent() - 10;
            int rectWidth = maxWidth + 20;
            int rectHeight = textHeight + 20;
            //g2d.setColor(new Color(255,255,255,255));
            //g2d.fillRoundRect(rectX, rectY, rectWidth, rectHeight, arcWidth, arcHeight);

            // Dibujar el borde del rectángulo redondeado
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(rectX, rectY, rectWidth, rectHeight, arcWidth, arcHeight);

            // Dibujar cada línea de texto
            g2d.setColor(Color.BLACK);
            // Dibujar texto centrado dentro del rectángulo
            int totalTextHeight = textLines.length * lineHeight;
            for (int i = 0; i < textLines.length; i++) {
                int yy = rectY + ((rectHeight - totalTextHeight) / 2) + (i * lineHeight) + g2d.getFontMetrics().getAscent();
                drawCenteredText(g2d, textLines[i], rectWidth, rectX, yy);
            }

            // Restaurar la transformación original
            g2d.setTransform(originalTransform);

            // Liberar los recursos gráficos
            g2d.dispose();

            // Guardar la imagen en un archivo, evitando sobrescribir un archivo existente
            File outputFile = new File("rotatedTextWithRoundedRectangle.png");
            ImageIO.write(image, "png", outputFile);
            return image;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void drawCenteredText(Graphics2D g2d, String text, int rectWidth, int rectX, int y) {
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        int x = rectX + (rectWidth - metrics.stringWidth(text)) / 2;
        g2d.drawString(text, x, y);
    }
}
