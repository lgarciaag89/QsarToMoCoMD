/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd;

import com.tomocomd.qsartomocomd.gui.GuiApp;
import com.tomocomd.qsartomocomdlib.configuration.ProjectConf;
import com.tomocomd.qsartomocomdlib.fuzzylogic.fuzzymeasures.SugenoLambdaMeasure;
import com.tomocomd.qsartomocomdlib.search.geneticalgorithm.AbstractGeneticAlgorithm;
import com.tomocomd.qsartomocomdlib.search.geneticalgorithm.GAFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

/**
 *
 * @author potter
 */
public class Main {

    static {
        try {
            ConfigurationSource source = new ConfigurationSource(new FileInputStream("log4j2.xml"));
            Configurator.initialize(null, source);
        } catch (IOException e) {
            System.err.println("Error while initializing log4j from file: log4j2.xml");
            e.printStackTrace();
        }
    }

    static final Logger LOGGER = (Logger) LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        validateFolders();

        parseOptions(args);
//        tests();
    }

    private static void validateFolders() {
        File f = new File("descriptoresgenerados");

        if (!f.exists() || f.isFile()) {
            f.mkdir();
        }

        f = new File("logs");

        if (!f.exists() || f.isFile()) {
            f.mkdir();
        }
    }

    private static void parseOptions(String[] args) throws Exception {

        Options options = new Options();

        options.addOption("p", "project", true, "path to project file .qproj");
        options.addOption("s", "sdffile", true, "input, path to sdf file");
        options.addOption("c", "csvfile", true, "output, path to csv file");
        options.addOption("t", "target", true, "property target");

        options.addOption("h", "help", false, "Show this help and exit");
        options.addOption("v", "version", false, "show the version and exit");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        HelpFormatter help = new HelpFormatter();
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {

            help.printHelp("cmd", options, true);
            System.err.println(String.format("Problems parsing coomand line\n" + ex.getMessage()));
            System.exit(-1);
        }

        if (cmd.getOptions().length == 0) {
            GuiApp.launch(GuiApp.class);
        } else {
            if (cmd.hasOption("h")) {
                help.printHelp("cmd", options, true);
            } else if (cmd.hasOption("v")) {
                System.out.println("QSARTommocomd 0.1");
            } else if (cmd.hasOption("p")) {

                String path = cmd.getOptionValue("p");
                ProjectConf conf = new ProjectConf();
                try {
                    ObjectInputStream dataread = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(path))));
                    conf = (ProjectConf) dataread.readObject();
                    dataread.close();
                } catch (IOException ex) {
                    LOGGER.error(String.format("Problems loading project file:%s", path), ex);
                    System.err.println(String.format("Problems loading project file:%s\n" + ex.getMessage(), path));
                    System.exit(-1);
                }

                if (cmd.hasOption("s")) {
                    conf.setSdfFile(cmd.getOptionValue("s"));
                }
                if (cmd.hasOption("c")) {
                    conf.setOutFileFile(cmd.getOptionValue("c"));
                }

                if (cmd.hasOption("t")) {
                    conf.setTarget(cmd.getOptionValue("t"));
                }

                try {
                    cli(conf);
                    LOGGER.info("Task completed");
                } catch (Exception ex) {
                    LOGGER.error(String.format("Problems execution conf file:%s", path), ex);
                    System.exit(-1);
                }
            }
            System.exit(0);
        }
    }

    public static void cli(ProjectConf conf) throws Exception {

        long timeinit = System.currentTimeMillis();
        AbstractGeneticAlgorithm ga = GAFactory.getGA(conf);
        ga.compute();
        long time = System.currentTimeMillis() - timeinit;
        LOGGER.info(String.format("Execution completed with execution time=%d", time));
    }

    public static void tests() throws Exception {

        List<Double> values = new LinkedList<>();

        double v = 0.05;
        while (v < 1) {
            values.add(v);
            v += 0.05;
        }

        for(int i1 = 0 ; i1 < values.size() ; i1++){
            for(int i2 = i1+1 ; i2 < values.size() ; i2++){
                for(int i3 = i2+1 ; i3 < values.size() ; i3++){
//                    for(int i4 = i3+1 ; i4 < values.size() ; i4++){
                        double []pesos = new double[]{
                            values.get(i1),values.get(i2),values.get(i3),values.get(i3)};
                        SugenoLambdaMeasure lambda = new SugenoLambdaMeasure();
                        lambda.buildMeasure(pesos);
                        if( lambda.getLambdaValues() > 0.5 && lambda.getLambdaValues() < 1)
                            System.out.println(Arrays.toString(pesos)+" "+ lambda.getLambdaValues());
//                    }
                }
            }
        }
    }

}
