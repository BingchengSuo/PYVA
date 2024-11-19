/*
 * j.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Nov 18 2024, 19:28 by COMSOL 6.2.0.290. */
public class j {

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
         .set("size", new String[]{"substrate_size", "substrate_size", "1645.0[nm]"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-822.5[nm]"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "1500.0[nm]", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "10.5[nm]", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "4.0[nm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "10.5[nm]", 3);
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

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").set("filename", "dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").label("mesa_etch");
    model.component("comp1").geom("geom1").feature("wp1").label("mesa_etch_wp");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("imp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext1").label("mesa_etch_Etch");
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "134.5[nm]", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("scale", 0.31025641025641026, 0, 1);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("ext1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature("dif1").label("mesa_etch");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "-134.5[nm]");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").set("filename", "dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("imp2");
    model.component("comp1").geom("geom1").feature("wp2").label("semi_dot_wp");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "workplane");
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "4[nm]", 0);
    model.component("comp1").geom("geom1").feature("ext2").label("semi_dot_ext");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "35[nm]");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("imp3", "Import");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").set("filename", "dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp3").label("QPC_wp");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("imp3");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("imp3");
    model.component("comp1").geom("geom1").feature("wp3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp3").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp3").set("color", "18");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "-99.5[nm]");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("imp4", "Import");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").set("filename", "dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp4").label("Plunger_wp");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("imp4");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("imp4");
    model.component("comp1").geom("geom1").feature("wp4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp4").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp4").set("color", "18");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("DomainTerminalsel");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("ext2");
    model.component("comp1").geom("geom1").run("sel5");

    model.component("comp1").physics("es").create("DomainTerminal5", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("DomainTerminal5").selection().named("geom1_sel5_dom");

    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("Groundsel");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("wp3", "wp4");
    model.component("comp1").geom("geom1").run("sel6");

    model.component("comp1").physics("es").create("gnd6", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd6").selection().named("geom1_sel6_bnd");

    model.label("test1.mph");

    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickz", "500[nm]");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp1")
         .set("filename", "/Users/bing/Documents/GitHub/PYVA/dot_3layers.GDS");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp1").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp1").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp1").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp1").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp1").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("imp1");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "1000[nm]", 0);
    model.component("comp1").geom("geom1").run("ext3");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("ext3");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif2");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("dif2");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "-50[nm]");
    model.component("comp1").geom("geom1").run("mov1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("dif1", "mov1");
    model.component("comp1").geom("geom1").feature("int1").set("keep", true);
    model.component("comp1").geom("geom1").run("int1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").set("displz", "50[nm]");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").run("mov2");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").feature().remove("mov2");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("mov2").set("displz", "50[nm]");
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("mov2");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("mov1");
    model.component("comp1").geom("geom1").feature("dif3").set("keepadd", true);
    model.component("comp1").geom("geom1").feature().remove("dif3");
    model.component("comp1").geom("geom1").create("int2", "Intersection");
    model.component("comp1").geom("geom1").feature("int2").selection("input").set("mov1", "mov2");
    model.component("comp1").geom("geom1").run("int2");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").feature("int1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").run("int2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").view("view1").set("renderwireframe", true);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
