/*
 * test.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Nov 17 2024, 22:38 by COMSOL 6.2.0.290. */
public class test {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("/Users/bing/Documents/GitHub/PYVA");

    model.label("Model");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stssw", "StationarySourceSweep");
    model.study("std1").feature("stssw").set("solnum", "auto");
    model.study("std1").feature("stssw").set("notsolnum", "auto");
    model.study("std1").feature("stssw").set("outputmap", new String[]{});
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").setSolveFor("/physics/es", true);

    model.param().set("substrate_size", "40000.0[nm]");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("substrate");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("size", new String[]{"substrate_size", "substrate_size", "1680.0[nm]"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-840.0[nm]"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "1500.0[nm]", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "10.5[nm]", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "4.0[nm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "10.5[nm]", 3);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "120.0[nm]", 4);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("InAs");
    model.component("comp1").material("mat1").tag("InAs");
    model.component("comp1").material("InAs").propertyGroup("def").set("relpermittivity", new String[]{"15.1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("InGaAs");
    model.component("comp1").material("mat2").tag("InGaAs");
    model.component("comp1").material("InGaAs").propertyGroup("def").set("relpermittivity", new String[]{"14.1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("InAlAs");
    model.component("comp1").material("mat3").tag("InAlAs");
    model.component("comp1").material("InAlAs").propertyGroup("def").set("relpermittivity", new String[]{"13.3"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Al2O3");
    model.component("comp1").material("mat4").tag("Al2O3");
    model.component("comp1").material("Al2O3").propertyGroup("def").set("relpermittivity", new String[]{"4.5"});
    model.component("comp1").material("InAlAs").selection().set(1, 5);
    model.component("comp1").material("InGaAs").selection().set(2, 4);
    model.component("comp1").material("InAs").selection().set(3);
    model.component("comp1").material("Al2O3").selection().set(6);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").label("mesa");
    model.component("comp1").geom("geom1").feature("wp1").label("mesa_wp");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("imp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext1").label("mesa_Etch");
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "169.5[nm]", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("ext1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature("dif1").label("mesa");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").label("metal");
    model.component("comp1").geom("geom1").feature("wp2").label("metal_wp");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("imp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext2").label("metal_Etch");
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "169.5[nm]", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("ext2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").feature("dif2").label("metal");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "-169.5[nm]");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("imp3", "Import");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("imp3");
    model.component("comp1").geom("geom1").feature("wp3").label("island_wp");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "workplane");
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "50[nm]", 0);
    model.component("comp1").geom("geom1").feature("ext3").label("island_ext");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "-169.5[nm]");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("imp4", "Import");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("imp4");
    model.component("comp1").geom("geom1").feature("wp4").label("al2o3_mesa_wp");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("extrudefrom", "workplane");
    model.component("comp1").geom("geom1").feature("ext4").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "35[nm]", 0);
    model.component("comp1").geom("geom1").feature("ext4").label("al2o3_mesa_ext");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickz", "-119.5[nm]");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("imp5", "Import");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("imp5");
    model.component("comp1").geom("geom1").feature("wp5").label("al2o3_island_wp");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("extrudefrom", "workplane");
    model.component("comp1").geom("geom1").feature("ext5").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "35[nm]", 0);
    model.component("comp1").geom("geom1").feature("ext5").label("al2o3_island_ext");
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("quickz", "-169.5[nm]");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("imp6", "Import");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().run("imp6");
    model.component("comp1").geom("geom1").feature("wp6").label("dot_wp");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").set("extrudefrom", "workplane");
    model.component("comp1").geom("geom1").feature("ext6").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "4[nm]", 0);
    model.component("comp1").geom("geom1").feature("ext6").label("dot_ext");
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("quickz", "0[nm]");
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("imp7", "Import");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp7").label("QPC_wp");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("imp7");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("imp7");
    model.component("comp1").geom("geom1").feature("wp7").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp7").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp7").set("color", "18");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp8", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp8").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp8").set("quickz", "-134.5[nm]");
    model.component("comp1").geom("geom1").run("wp8");
    model.component("comp1").geom("geom1").feature("wp8").geom().create("imp8", "Import");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("imp8")
         .set("filename", "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("imp8").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("imp8").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("imp8").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("imp8").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp8").label("Plunger_wp");
    model.component("comp1").geom("geom1").feature("wp8").geom().run("imp8");
    model.component("comp1").geom("geom1").feature("wp8").geom().run("imp8");
    model.component("comp1").geom("geom1").feature("wp8").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp8").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp8").set("color", "18");
    model.component("comp1").geom("geom1").run();

    model.label("test.mph");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").material("Al2O3").selection().set(7, 11, 16, 19, 21);
    model.component("comp1").material("InAlAs").selection().set(2, 6, 10, 13);
    model.component("comp1").material("InGaAs").selection().set(3, 5, 9, 15);
    model.component("comp1").material("Al2O3").selection().set(7, 11, 14, 16, 19, 21);

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
