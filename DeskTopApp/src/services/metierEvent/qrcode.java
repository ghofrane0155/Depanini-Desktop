/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.metierEvent;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.Format;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noure
 */
public class qrcode {

            private static final String outputQr="C:\\Users\\noure\\OneDrive\\Bureau\\desktopapp2\\src\\GUI\\images\\qr.png";
            public static void generateQRcode(String text, int width, int height, String filePath) throws WriterException {
        QRCodeWriter qc = new QRCodeWriter();
        BitMatrix bm = qc.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path pobj = FileSystems.getDefault().getPath(filePath);
        try {
            MatrixToImageWriter.writeToPath(bm, "PNG", pobj);
        } catch (IOException ex) {
            Logger.getLogger(qrcode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
