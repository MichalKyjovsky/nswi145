package cz.cuni.mff.kyjovsm.ocr;

import javax.jws.WebService;

@WebService(endpointInterface = "service.OCR")
public class OCRImpl implements OCR
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Override
    public void foo() {

    }
}
