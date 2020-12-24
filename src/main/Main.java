package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        int exercise = 0;
        int part = 0;
        boolean testing = false;
        if(args.length >3 || args.length<2) {
            usage();
        }else {
            try {
                exercise = Integer.parseInt(args[0]);
                part = Integer.parseInt(args[1]);
                if(args.length==3) {
                    testing = Boolean.parseBoolean(args[2]);
                }
                if(exercise>25 || exercise<1 || part < 1 || part > 2){
                    usage();
                }
            } catch (Exception e) {
                e.printStackTrace();
                usage();
            }
        }
        doExecrise(exercise,part,testing);
    }

    private static void usage(){
        System.out.println("useage: exercise part testing");
        System.out.println("useage:  [0-25]  [1,2]  [1,0]?");
        System.exit(-1);
    }

    private static void doExecrise(int exercise, int part,boolean test){
        String in = "";
        try {
            in = readFile((test ? "test" : "input" + exercise) + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(exercise){
            case(1):
                NotQuiteLisp notQuiteLisp = new NotQuiteLisp(in);
                System.out.println((part==1?notQuiteLisp.solve1():notQuiteLisp.solve2()));
                break;
            case(2):
                NoMath noMath = new NoMath(in);
                System.out.println(part==1?noMath.solve1():noMath.solve2());
                break;
            case(3):
                HousesInVacuum housesInVacuum = new HousesInVacuum(in);
                System.out.println(part==1?housesInVacuum.solve1():housesInVacuum.solve2());
                break;
            case(4):
                IdealStockingStuffer idealStockingStuffer = new IdealStockingStuffer(in);
                System.out.println(part==1?idealStockingStuffer.solve1():idealStockingStuffer.solve2());
                break;
            case(5):
                InternElves internElves = new InternElves(in);
                System.out.println(part==1?internElves.solve1():internElves.solve2());
                break;
            case(6):
                FireHazard fireHazard = new FireHazard(in);
                System.out.println(part==1?fireHazard.solve1():fireHazard.solve2());
                break;
            case(7):
                AssemblyRequired assemblyRequired = new AssemblyRequired(in);
                System.out.println(part==1?assemblyRequired.solve1():assemblyRequired.solve2());
                break;
            case(8):
                Matchsticks matchsticks = new Matchsticks(in);
                System.out.println(part==1?matchsticks.solve1():matchsticks.solve2());
                break;
            case(9):
                SingleNight singleNight = new SingleNight(in);
                System.out.println(part==1?singleNight.solve1():singleNight.solve2());
                break;
            case(10):
                LookAndSay lookAndSay = new LookAndSay(in);
                System.out.println(part==1?lookAndSay.solve1():lookAndSay.solve2());
                break;
            case(11):
                CorporatePolicy corporatePolicy = new CorporatePolicy(in);
                System.out.println(part==1?corporatePolicy.solve1():corporatePolicy.solve2(corporatePolicy.solve1()));
                break;
            case(12):
                JSAbacus jsAbacus = new JSAbacus(in);
                System.out.println(part==1?jsAbacus.solve1():jsAbacus.solve2());
                break;
            case(13):
                DinnerTable dinnerTable = new DinnerTable(in);
                System.out.println(part==1?dinnerTable.solve1():dinnerTable.solve2());
                break;
            case(14):
                ReindeerOlympics reindeerOlympics = new ReindeerOlympics(in);
                System.out.println(part==1?reindeerOlympics.solve1():reindeerOlympics.solve2());
                break;
            case(15):
                //TODO made it in excel
                System.out.println("linear approxiation in java!?!");
                break;
            case(16):
                MFCSAM mfcsam = new MFCSAM(in);
                System.out.println(part==1?mfcsam.solve1():mfcsam.solve2());
                break;
            case(17):
                TooMuch tooMuch = new TooMuch(in);
                System.out.println(part==1?tooMuch.solve1():tooMuch.solve2());
                break;
            case(18):
                YardGIF yardGIF = new YardGIF(in);
                System.out.println(part==1?yardGIF.solve1():yardGIF.solve2());
                break;
            case(19):
                MedicineRudolph medicineRudolph = new MedicineRudolph(in);
                System.out.println(part==1?medicineRudolph.solve1():medicineRudolph.solve2());
                break;
            case(20):
                InfiniteHouses infinteHouses = new InfiniteHouses(in);
                System.out.println(part==1?infinteHouses.solve1():infinteHouses.solve2());
                break;
            case(21):
                RPGSimulator rpgSimulator = new RPGSimulator(in);
                System.out.println(part==1?rpgSimulator.solve1():rpgSimulator.solve2());
                break;
            case(22): //TODO
                WizardSimulator wizardSimulator = new WizardSimulator(in);
                System.out.println(part==1?wizardSimulator.solve1():wizardSimulator.solve2());
                break;
            case(23):
                TuringLock turingLock = new TuringLock(in);
                System.out.println(part==1?turingLock.solve1():turingLock.solve2());
                break;
            case(24):
                HangsInBalance hangsInBalance = new HangsInBalance(in);
                System.out.println(part==1?hangsInBalance.solve1():hangsInBalance.solve2());
                break;
            case(25):
                LetItSnow letItSnow = new LetItSnow(in);
                System.out.println(part==1?letItSnow.solve1():letItSnow.solve2());
                break;
            default:
        }
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(
                Objects.requireNonNull(Main.class.getClassLoader().getResource(fileName)).getFile()
        );
        return Files.readString(file.toPath());
    }


}
