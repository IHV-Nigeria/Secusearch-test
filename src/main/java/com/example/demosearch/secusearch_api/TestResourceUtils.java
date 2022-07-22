package com.example.demosearch.secusearch_api;

import com.example.demosearch.model.BiometricInfo;
import com.example.demosearch.repositories.BiometricInfoRepository;
import com.example.demosearch.repositories.ContainerRepository;
import com.secugen.secusearch.api.SSIdTemplatePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@Service
public class TestResourceUtils {
    private static final String templateDirName = "/min_data";
    private static final String ansiTemplateFilename = "/test_data/ansi378_2views.bin";
    private static final String isoTemplateFilename = "/test_data/iso19794_1view.bin";
    public static final int ANSI_TEMPLATE_VIEW_COUNT = 2;
    public static final int ISO_TEMPLATE_VIEW_COUNT = 1;
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private BiometricInfoRepository biometricInfoRepository;

    /**
     * Reads all the fingerprint minutiae templates in 'min_data'.
     * Each template data is in the SecuGen 400-byte format and
     * each template ID is extracted from the first part of its file name.
     *
     * @return an arrays of SSIdTemplatePair
     */
    public SSIdTemplatePair[] readSecuGenTemplates() {
        List<BiometricInfo> biometricInfoList = biometricInfoRepository.findAll();
        SSIdTemplatePair[] templates = new SSIdTemplatePair[biometricInfoList.size()];
        for (int i = 0; i < biometricInfoList.size(); i++) {
            System.out.println(biometricInfoList.get(i));
            int id = Math.toIntExact(biometricInfoList.get(i).getBiometricInfo_Id());
            byte[] sgTemplate = Base64.getDecoder().decode(biometricInfoList.get(i).getNewTemplate());
            System.out.println(sgTemplate.length);
            templates[i] = new SSIdTemplatePair(id, sgTemplate);
        }
        return templates;
//        List<Container> containers = containerRepository.findAll();
//        List<Container> validContainers = new ArrayList<>();
//        for (Container container: containers) {
//            if (container.getMessageData().getPatientBiometrics() != null && container.getMessageData().getPatientBiometrics().size() > 0) {
//                validContainers.add(container);
//            }
//        }
//        SSIdTemplatePair[] templates = new SSIdTemplatePair[validContainers.size()];
//
//        for (int i = 0; i < templates.length; i++) {
//            List<PatientBiometricType> biometricTypes = validContainers.get(i).getMessageData().getPatientBiometrics();
//            for (PatientBiometricType biometricType : biometricTypes) {
//                int id = validContainers.get(i).getMessageData().getDemographics().getPatientId();
//                byte[] sgTemplate = Base64.getDecoder().decode(biometricType.getTemplate());
//                int errorCode;
//                System.out.println(sgTemplate.length);
////                long numberOfViews;
////                sgTemplate = secuSearch.extractTemplate(sgTemplate, SSTemplateType.ANSI378, 1);
////                System.out.println(sgTemplate.length);
//////                byte[] sgTemplate = biometricType.getTemplate().getBytes(StandardCharsets.ISO_8859_1);
////                System.out.println(sgTemplate.length);
//                templates[i] = new SSIdTemplatePair(id, sgTemplate);
//            }
//
//        }
//
//        return templates;
    }

    /**
     * Reads a ANSI 378 template in 'test_data'.
     *
     * @return a byte array containing a ANSI 378 template
     * @throws IOException
     */
    public static byte[] readAnsiTemplate() throws IOException {
        return readBinaryResourceFile(ansiTemplateFilename);
    }

    /**
     * Reads a ISO 19794 template in 'test_data'.
     *
     * @return a byte array containing a ISO 19794 template
     * @throws IOException
     */
    public static byte[] readIsoTemplate() throws IOException {
        return readBinaryResourceFile(isoTemplateFilename);
    }

    /**
     * Reads a resource file.
     *
     * @param resourceFilename a resource file name to read
     * @return a byte array containing the binary data of the {@code resourceFilename}
     * @throws IOException if it failed
     */
    private static byte[] readBinaryResourceFile(String resourceFilename) throws IOException {
        InputStream in = TestResourceUtils.class.getResourceAsStream(resourceFilename);
        if (in == null) {
            throw new IOException("cannot open a file: " + resourceFilename);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = in.read(buffer);
        while (len > 0) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
        in.close();
        out.close();
        return out.toByteArray();
    }

}
