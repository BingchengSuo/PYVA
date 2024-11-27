/*
 * j.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Nov 26 2024, 20:34 by COMSOL 6.2.0.290. */
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

    model.param().set("substrate_size", "10000.0[nm]");

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
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").set("filename", "double_dot.gds");
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
    model.component("comp1").geom("geom1").feature("ext1").setIndex("scale", 0.30848329048843187, 0, 1);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "-0.0[nm]");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "2000[nm]", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("arr1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "-134.5[nm]");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").set("filename", "double_dot.gds");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp2").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("imp2");
    model.component("comp1").geom("geom1").feature("wp2").label("dot_wp");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "workplane");
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "4[nm]", 0);
    model.component("comp1").geom("geom1").feature("ext2").label("dot_ext");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("mov2").set("disply", "-0.0[nm]");
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("mov2");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"0", "2000[nm]", "0"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "800[nm]");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("imp3", "Import");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").set("filename", "double_dot.gds");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("imp3").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("imp3");
    model.component("comp1").geom("geom1").feature("wp3").label("metal_wp");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "1000[nm]", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("mov3", "Move");
    model.component("comp1").geom("geom1").feature("mov3").selection("input").set("ext3");
    model.component("comp1").geom("geom1").feature("mov3").set("disply", "-0.0[nm]");
    model.component("comp1").geom("geom1").run("mov3");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("mov3");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("dif1");
    model.component("comp1").geom("geom1").feature("dif3").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("mov300", "Move");
    model.component("comp1").geom("geom1").feature("mov300").selection("input").set("dif3");
    model.component("comp1").geom("geom1").feature("mov300").set("displz", "-100[nm]");
    model.component("comp1").geom("geom1").run("mov300");
    model.component("comp1").geom("geom1").create("int3", "Intersection");
    model.component("comp1").geom("geom1").feature("int3").selection("input").set("dif1", "mov300");
    model.component("comp1").geom("geom1").feature("int3").set("keep", true);
    model.component("comp1").geom("geom1").run("int3");
    model.component("comp1").geom("geom1").create("mov310", "Move");
    model.component("comp1").geom("geom1").feature("mov310").set("displz", "100[nm]");
    model.component("comp1").geom("geom1").feature("mov310").selection("input").set("int3");
    model.component("comp1").geom("geom1").run("mov310");
    model.component("comp1").geom("geom1").create("int300", "Intersection");
    model.component("comp1").geom("geom1").feature("int300").selection("input").set("mov310", "mov300");
    model.component("comp1").geom("geom1").run("int300");
    model.component("comp1").geom("geom1").feature("int3").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("int300").set("intbnd", false);
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("int300");
    model.component("comp1").geom("geom1").feature("arr3").set("fullsize", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").feature("arr3").set("displ", new String[]{"0", "2000[nm]", "0"});
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "800[nm]");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("imp4", "Import");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").set("filename", "double_dot.gds");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("imp4").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("imp4");
    model.component("comp1").geom("geom1").feature("wp4").label("Al2O3_wp");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext4").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "1000[nm]", 0);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("dif4", "Difference");
    model.component("comp1").geom("geom1").feature("dif4").selection("input").set("ext4");
    model.component("comp1").geom("geom1").feature("dif4").selection("input2").set("dif1", "arr3(1,1,1)");
    model.component("comp1").geom("geom1").feature("dif4").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif4");
    model.component("comp1").geom("geom1").create("mov4", "Move");
    model.component("comp1").geom("geom1").feature("mov4").selection("input").set("dif4");
    model.component("comp1").geom("geom1").feature("mov4").set("displz", "-35[nm]");
    model.component("comp1").geom("geom1").run("mov4");
    model.component("comp1").geom("geom1").create("int4", "Intersection");
    model.component("comp1").geom("geom1").feature("int4").selection("input").set("dif1", "mov4");
    model.component("comp1").geom("geom1").feature("int4").set("keep", true);
    model.component("comp1").geom("geom1").run("int4");
    model.component("comp1").geom("geom1").create("mov400", "Move");
    model.component("comp1").geom("geom1").feature("mov400").set("displz", "35[nm]");
    model.component("comp1").geom("geom1").feature("mov400").selection("input").set("int4");
    model.component("comp1").geom("geom1").run("mov400");
    model.component("comp1").geom("geom1").create("int400", "Intersection");
    model.component("comp1").geom("geom1").feature("int400").selection("input").set("mov4", "mov400");
    model.component("comp1").geom("geom1").run("int400");
    model.component("comp1").geom("geom1").feature("int4").set("intbnd", false);
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickz", "800[nm]");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("imp5", "Import");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").set("filename", "double_dot.gds");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("imp5").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("imp5");
    model.component("comp1").geom("geom1").feature("wp5").label("metal_Al2O3_wp");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext5").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "1000[nm]", 0);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("mov5", "Move");
    model.component("comp1").geom("geom1").feature("mov5").selection("input").set("ext5");
    model.component("comp1").geom("geom1").feature("mov5").set("disply", "-0.0[nm]");
    model.component("comp1").geom("geom1").run("mov5");
    model.component("comp1").geom("geom1").create("dif5", "Difference");
    model.component("comp1").geom("geom1").feature("dif5").selection("input").set("mov5");
    model.component("comp1").geom("geom1").feature("dif5").selection("input2").set("arr3(1,1,1)");
    model.component("comp1").geom("geom1").feature("dif5").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif5");
    model.component("comp1").geom("geom1").create("mov500", "Move");
    model.component("comp1").geom("geom1").feature("mov500").selection("input").set("dif5");
    model.component("comp1").geom("geom1").feature("mov500").set("displz", "-35[nm]");
    model.component("comp1").geom("geom1").run("mov500");
    model.component("comp1").geom("geom1").create("int5", "Intersection");
    model.component("comp1").geom("geom1").feature("int5").selection("input").set("arr3(1,1,1)", "mov500");
    model.component("comp1").geom("geom1").feature("int5").set("keep", true);
    model.component("comp1").geom("geom1").run("int5");
    model.component("comp1").geom("geom1").create("mov510", "Move");
    model.component("comp1").geom("geom1").feature("mov510").set("displz", "35[nm]");
    model.component("comp1").geom("geom1").feature("mov510").selection("input").set("int5");
    model.component("comp1").geom("geom1").run("mov510");
    model.component("comp1").geom("geom1").create("int500", "Intersection");
    model.component("comp1").geom("geom1").feature("int500").selection("input").set("mov510", "mov500");
    model.component("comp1").geom("geom1").run("int500");
    model.component("comp1").geom("geom1").feature("int5").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("int500").set("intbnd", false);
    model.component("comp1").geom("geom1").create("arr5", "Array");
    model.component("comp1").geom("geom1").feature("arr5").selection("input").set("int500");
    model.component("comp1").geom("geom1").feature("arr5").set("fullsize", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").feature("arr5").set("displ", new String[]{"0", "2000[nm]", "0"});
    model.component("comp1").geom("geom1").run("arr5");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("quickz", "35[nm]");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("imp6", "Import");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").set("filename", "double_dot.gds");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("imp6").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp6").label("QPC_wp");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("imp6");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("imp6");
    model.component("comp1").geom("geom1").feature("wp6").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp6").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp6").set("color", "18");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("quickz", "-99.5[nm]");
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("imp7", "Import");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").set("filename", "double_dot.gds");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("imp7").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").feature("wp7").label("Plunger_wp");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("imp7");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("imp7");
    model.component("comp1").geom("geom1").feature("wp7").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp7").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp7").set("color", "18");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("Al2O3sel");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("int400", "arr5(1,1,1)");
    model.component("comp1").geom("geom1").run("sel8");

    model.component("comp1").material("Al2O3").selection().named("geom1_sel8_dom");

    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("DomainTerminalsel9");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("arr2(1,1,1)", "arr3(1,1,1)");
    model.component("comp1").geom("geom1").run("sel9");

    model.component("comp1").physics("es").create("DomainTerminal9", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("DomainTerminal9").selection().named("geom1_sel9_dom");

    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("Groundsel10");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("wp6", "wp7");
    model.component("comp1").geom("geom1").run("sel10");

    model.component("comp1").physics("es").create("gnd10", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd10").selection().named("geom1_sel10_bnd");

    model.label("double_dot.mph");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("renderwireframe", true);
    model.component("comp1").view("view1").set("transparency", true);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stssw");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stssw");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature("p1").set("preusesol", "no");
    model.sol("sol1").feature("s1").feature("p1").set("pcontinuationmode", "no");
    model.sol("sol1").feature("s1").feature("p1").set("control", "stssw");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "cg");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "amg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("coarseningmethod", "classic");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Electric Potential (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("defaultPlotID", "InterfaceComponents/PlotDefaults/icom2/pdef1/pcond2/pcond1/pcond1/pg1");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("expr", "V/es.VexcTerm");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("expr", "V/es.VexcTerm");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Electric Field Norm (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("defaultPlotID", "InterfaceComponents/PlotDefaults/icom3/pdef1/pcond2/pcond1/pg1");
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "es.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1").label(" (es)");
    model.result().evaluationGroup("eg1").label("Lumped Parameters (dset1, es)");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "es.Cinv", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Inverse Maxwell capacitance", 0);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "es.Cinv", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Maxwell capacitance", 1);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "es.Cinv", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Mutual capacitance", 2);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").autoMeshSize(4);

    model.result("pg1").run();

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
