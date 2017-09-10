package de.mathan.maven.latex;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.Test;

import java.io.File;

/**
 * Simple test generating an output file for each supported file without bibtex/makeindex.
 */
public class SimpleTest {

    @Test
    public void pdf() throws Exception {
        testSuccess("pdf");
    }

    @Test
    public void ps() throws Exception {
        testSuccess("ps");
    }

    @Test
    public void dvi() throws Exception {
        testSuccess("dvi");
    }

    @Test
    public void invalidOutputFormat() throws Exception {
        File dir = ResourceExtractor.simpleExtractResources(getClass(), "/simple_invalid");
        Verifier verifier = new Verifier(dir.getAbsolutePath());
        try {
            verifier.executeGoal("mathan:latex");
        } catch (VerificationException e) {
            verifier.verifyTextInLog("Invalid outputFormat");
        }
    }

    private void testSuccess(String outputFormat) throws Exception {
        File dir = ResourceExtractor.simpleExtractResources(getClass(), "/simple_" + outputFormat);
        Verifier verifier = new Verifier(dir.getAbsolutePath());
        verifier.executeGoal("mathan:latex");
        verifier.assertFilePresent("target/sample." + outputFormat);
        // as there is no bibtex, makeindex file these steps will be skipped
        verifier.verifyTextInLog("[mathan] execution skipped: bibtex");
        verifier.verifyTextInLog("[mathan] execution skipped: makeindex");
        verifier.verifyTextInLog("[mathan] execution skipped: makeindexnomencl");
    }

}
