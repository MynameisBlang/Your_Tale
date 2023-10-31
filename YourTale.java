// Your Tale made by: Tristan Ing Angel Cruz and David Wu
// Class CS2011-09 FINAL PROJECT!


import java.util.*;
import java.util.Random;

public class YourTale {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    if(args.length != 0 && args[0].equals("-help"))
    {
      help();
      System.out.println("Press enter to continue");
      in.nextLine();
    }
    // HP. DEF, ATK, WIFE, SON, DAUGHTER, GRANDPA, SALT, LIGHT, WATER, GOLD, ACID
    int[] masterValues = {50, 10, 10, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    // INTRO
    int gameStart = setDressing();
    // NAME CREATION
    String[] name = menu();
    pause(1250);
    // FIRST REAL CHOICE
    int vacation = choiceOfVacation(name);
    // SPLIT
    if (vacation == 1) {
      fairyLand(masterValues, name);
    }

  }

  public static void help()
  {
    System.out.println("Choices: You have between 1-4 choices to make and each one of them will lead to a death of your family member,");
    System.out.println("         lost or gain of stat points, and potential information and useful key items");
    System.out.println();
    System.out.println("Battle: You have the option of either battling or talking to the enemy.");
    System.out.println("        If you choose to talk 3 times and survive it will end the battle with your win.");
    System.out.println("        Attacking and talking leads to a dodge mini game which revolves into a simple number guesser.");
    System.out.println("        Defending will not allow you to dodge but it will lead you to take less damage for 1 turn.");
    System.out.println();
  }

  public static void clear_console()
  {
    for (int i = 0; i < 26; i++)
      {
        System.out.println();
      }
  }
  
  public static void battle(int[] masterValues, int[] enemyValues, int index) {
    Scanner input = new Scanner(System.in);
    int[] player_stats = new int[3];
      for (int i = 0; i < player_stats.length; i++)
        {
          player_stats[i] = masterValues[i];
        }
    
    char choice;
    int talk_count = 0;
    char dodge;
    int enemy_current_HP = enemyValues[0];
    while (true) {
      enemyHP(enemy_current_HP, index);
      System.out.println();
      playerHUD(player_stats);
      System.out.println();
      if (talk_count == 3)
      {
        System.out.println("Why won't you fight me....");
        pause(1000);
        System.out.println("I have lost my will to fight...");
        pause(1000);
        System.out.println("Bye....");
        pause(1000);
        break;
        
      }
      System.out.println(" ____________________________________ ");
      System.out.println("|                                    |");
      System.out.println("|   1. Battle             2. Talk    |");
      System.out.println("|____________________________________|");
      System.out.println();

      do 
      {
        System.out.print("Please enter 1 for battle and 2 to talk: ");
        choice = input.next().charAt(0);
        if (choice != '1' && choice != '2')
        {
          System.out.println("Not a valid input");
        }
      } while (choice != '1' && choice != '2');
      
      if (choice == '1') {
        System.out.println(" ____________________________________ ");
        System.out.println("|                                    |");
        System.out.println("|   1. Attack             2. Defend  |");
        System.out.println("|____________________________________|");
        System.out.println();
        do 
        {
          System.out.print("Please enter 1 to attack and 2 to defend: ");
          choice = input.next().charAt(0);
          if (choice != '1' && choice != '2')
          {
            System.out.println("Not a valid input");
          }
        } while (choice != '1' && choice != '2');

        if (choice == '1') {
          enemy_current_HP = damageCalculator(player_stats[2], enemyValues[1], enemy_current_HP);
          System.out.println("You have dealt damage to the enemy");
          explosion();
          clear_console();
          enemyHP(enemy_current_HP, index);
          if (enemy_current_HP <= 0)
          {
            System.out.println("You have successfully killed the enemy!");
            pause(4000);
            clear_console();
            break;
          }
          System.out.println("The enemy is preparing to attack please choose a spot to dodge");
          System.out.println("\t ______   ______   ______ ");
          System.out.println("\t|      | |      | |      |");
          System.out.println("\t|  01  | |  02  | |  03  |");
          System.out.println("\t|______| |______| |______|");
          System.out.println();
          do
          {
          System.out.print("Enter 1 to dodge left, Enter 2 to dodge in the middle, Enter 3 to dodge to the right: ");
          dodge = input.next().charAt(0);
            if (dodge != '1' && dodge != '2' & dodge != '3')
            {
              System.out.println("Not valid option");
            }
          } while (dodge != '1' && dodge != '2' & dodge != '3');
            
          if (hitRegister(dodge)) {
            player_stats[0] = damageCalculator(enemyValues[2], player_stats[1], player_stats[0]);
            explosion();
            clear_console();
            enemyHP(enemy_current_HP, index);
            playerHUD(player_stats);
            pause(1000);
            System.out.println("You been hit!");
            pause(1000);
            System.out.println("damage has been done to you");
            pause(1000);
            if (player_stats[0] <= 0)
            {
              printDeathMessage();
              pause(3000);
              System.exit(0);
            }
            clear_console();
          } 
          else {
            System.out.println("Successful Dodge!");
            pause(1000);
            clear_console();
          }
        } else if (choice == '2') {
          System.out.println("You are in a defensive stand");
          System.out.println("You been hit!");
          System.out.println("damage has been done to you");
          pause(1000);
          player_stats[0] = damageCalculator(enemyValues[2], player_stats[1] + 15, player_stats[0]);
          
          if (player_stats[0] <= 0)
            {
              printDeathMessage();
              pause(4000);
              System.exit(0);
            }
        }
      }

      else if (choice == '2') {
        System.out.println("Enemy: " + getRandomPhrase());
        talk_count++;
        pause(1000);
        System.out.println("The enemy is preparing to attack please choose a spot to dodge");
        pause(1000);
        System.out.println("\t ______   ______   ______ ");
        System.out.println("\t|      | |      | |      |");
        System.out.println("\t|  01  | |  02  | |  03  |");
        System.out.println("\t|______| |______| |______|");
        System.out.println();
        do
          {
          System.out.print("Enter 1 to dodge left, Enter 2 to dodge in the middle, Enter 3 to dodge to the right: ");
          dodge = input.next().charAt(0);
            if (dodge != '1' && dodge != '2' & dodge != '3')
            {
              System.out.println("Not valid option");
            }
          } while (dodge != '1' && dodge != '2' & dodge != '3');
        if (hitRegister(dodge)) {
          player_stats[0] = damageCalculator(enemyValues[2], player_stats[1], player_stats[0]);
            explosion();
            clear_console();
            enemyHP(enemy_current_HP, index);
            playerHUD(player_stats);
            pause(1000);
            System.out.println("You been hit!");
            pause(1000);
            System.out.println("damage has been done to you");
            pause(1000);
            if (player_stats[0] <= 0)
            {
              printDeathMessage();
              pause(3000);
              System.exit(0);
            }
            clear_console();
          } 
        else {
           System.out.println("Successful Dodge!");
           pause(1000);
           clear_console();
          }
      } 

    }
  }

  public static boolean hitRegister(char moved) {
    char[] place = { randomNumberGenerator(), randomNumberGenerator() };
    while (place[0] == place[1]) {
      place[1] = randomNumberGenerator();
    }
    boolean hit = false;
    if (place[0] == moved || place[1] == moved) {
      hit = true;
    }
    return hit;
  }

  public static int damageCalculator(int atk, int def, int hp) {
    int damage = hp - (atk - (atk * (def / 100)));
    return damage;
  }

  public static char randomNumberGenerator() {
    Random rand = new Random();
    int number = rand.nextInt(3) + 1;
    char value = Character.forDigit(number, 10);
    return value;
  }

  public static void zeroStringOmeter(int number) {
    if (number <= 0) {
      System.out.print("|       00       |  ");
    } 
    else if (number > 0 && number < 10) {
      System.out.print("|       0" + number + "       |  ");
    } else {
      System.out.print("|       " + number + "       |  ");
    }
  }

  public static void familyNamingConventions(String name) {
    if (name.length() == 4) {
      System.out.print("|---" + name + "---|   ");
    }
    if (name.length() == 3) {
      System.out.print("|---_" + name + "---|   ");

    }
    if (name.length() == 2) {
      System.out.print("|---__" + name + "---|   ");

    }
    if (name.length() == 1) {
      System.out.print("|---___" + name + "---|   ");

    }
  }

  public static void familyMeter(int life) {
    if (life == 0) {
      System.out.print("|   GONE   |   ");
    } else if (life == 1) {
      System.out.print("|   LIVE   |   ");
    }
  }

  public static void playerHUD(int[] mastVal) {
    String lineBreaker = "----------------------------------------------------------";
    // Stat parameters
    System.out.println(lineBreaker);
    System.out.print("|-----HEALTH-----|  ");
    System.out.print("|-----SHIELD-----|  ");
    System.out.println("|-----ATTACK-----|  ");
    // Stat values
    zeroStringOmeter(mastVal[0]);
    zeroStringOmeter(mastVal[1]);
    zeroStringOmeter(mastVal[2]);
    // Spacing
    System.out.println("\n" + lineBreaker);
  }

  public static void playerNames(int[] mastVal, String[] name) {
    String lineBreaker = "----------------------------------------------------------";
    System.out.println("\n" + lineBreaker);
    // Family names
    familyNamingConventions(name[1]);
    familyNamingConventions(name[2]);
    System.out.print(" ");
    familyNamingConventions(name[3]);
    familyNamingConventions(name[4]);
    // Spacing
    System.out.println();
    // Mortality
    familyMeter(mastVal[3]);
    familyMeter(mastVal[4]);
    System.out.print(" ");
    familyMeter(mastVal[5]);
    familyMeter(mastVal[6]);
    System.out.println("\n" + lineBreaker);

  }
  public static void enemyHP(int HP,int index) {
    characterGraphics(index);
    String lineBreaker = "******************";
    // Stat parameters
    System.out.println(lineBreaker);
    System.out.print("|-----HEALTH-----|");
    System.out.println();
    // Stat values
    zeroStringOmeter(HP);

    // Spacing
    System.out.println("\n" + lineBreaker);
  }
  

  public static void castle() {
    System.out.println("                                       /\\");
    System.out.println("                                      /`:\\");
    System.out.println("                                     /`'`:\\");
    System.out.println("                                    /`'`'`:\\");
    System.out.println("                                   /`'`'`'`:\\");
    System.out.println("                                  /`'`'`'`'`:\\");
    System.out.println("                                   |`'`'`'`:|");
    System.out.println("     _ _  _  _  _                  |] ,-.  :|_  _  _  _");
    System.out.println("    ||| || || || |                 |  |_| ||| || || || |");
    System.out.println("    |`' `' `' `'.|                 | _'=' |`' `' `' `'.|");
    System.out.println("    :          .:;                 |'-'   :          .:;");
    System.out.println("     \\-..____..:/  _  _  _  _  _  _| _  _'-\\-..____..:/");
    System.out.println("      :--------:_,' || || || || || || || `.::--------:");
    System.out.println("      |]     .:|:.  `' `'_`' `' `' `' `'    | '-'  .:|");
    System.out.println("      |  ,-. .[|:._     '-' ____     ___    |   ,-.'-|");
    System.out.println("      |  | | .:|'--'_     ,'____`.  '---'   |   | |.:|");
    System.out.println("      |  |_| .:|:.'--' ()/,| |`|`.\\()   __  |   |_|.:|");
    System.out.println("      |  '=' .:|:.     |::_|_|_|\\|::   '--' |  _'='.:|");
    System.out.println("      | __   .:|:.     ;||-,-,-,-,|;        | '--' .:|");
    System.out.println("      |'--'  .:|:. _  ; ||       |:|        |      .:|");
    System.out.println("      |      .:|:.'-':  ||       |;|     _  |]     _:|");
    System.out.println("      |      '-|:.   ;  ||       :||    '-' |     '--|");
    System.out.println("      |  _   .:|].  ;   ||       ;||]       |   _  .:|");
    System.out.println("      | '-'  .:|:. :   [||      ;|||        |  '-' .:|");
    System.out.println("  ,', ;._____.::-- ;---->'-,--,:-'<'--------;._____.::.`.");
    System.out.println(" ((  (          )_;___,' ,' ,  ; //________(          ) ))");
    System.out.println("  `. _`--------' : -,' ' , ' '; //-       _ `--------' ,'");
    System.out.println("       __  .--'  ;,' ,'  ,  ': //    -.._    __  _.-  -");
    System.out.println("   `-   --    _ ;',' ,'  ,' ,;/_  -.       ---    _,");
    System.out.println("       _,.   /-:,_,_,_,_,_,_(/:-\\   ,     ,.    _");
    System.out.println("     -'   `-'--'-'-'-'-'-'-'-''--'-' `-'`'  `'`' `-SSt-\n");
  }

  public static void crossroads() {
    System.out.println(";`             ;;                                               ");
    System.out.println(" ;;  -''-.   ;;                 -;'  -.                         ");
    System.out.println("   ''     ``                      `.   `.                       ");
    System.out.println("                                    ;    `                      ");
    System.out.println("           `;                  -          ;         -.        ;`");
    System.out.println("             `-  `.         .'`  .-'             .--`  ;     ;  ");
    System.out.println("              ;    `-.   ;    `-'             .;`     ;       `.");
    System.out.println("              .        ``                                       ");
    System.out.println("               `            .--------.             .'           ");
    System.out.println("             ...        .--'``````````'--.        ;.            ");
    System.out.println("            `      . .-' .``          ``. '-. .      `.         ");
    System.out.println("          ;-.;  .  .' .`                  `. '.  .    ;         ");
    System.out.println("              .' .' .`                      `. '. '.    .       ");
    System.out.println("_____/'.-..___________________________ mvn,, ___________________");
    System.out.println("                             )\\     nMmIEFooPTn                 ");
    System.out.println("                            ( (    Li1iiJl1ItTIjp               ");
    System.out.println("                             ) \\  i i_BP_LWmKK`  J  `           ");
    System.out.println("`                .          /  (   i1 LL 1I`L            .      ");
    System.out.println("             ..             \\   \\  i   X  Y o1                  ");
    System.out.println("                        .    )   )  `   l   p      ..           ");
    System.out.println(";                           /   (,      l(@) l                ..");
    System.out.println("  q      ` .  '            (     \\.     i    p   R          .;  ");
    System.out.println("   \\  t            ;        )     \\`   j,.. ,.q,/Pqoj          `");
    System.out.println("    \\/            `       ./       \\`;     `'     `          .. ");
    System.out.println("  '-     \\;            -'.'    ;    \\ `                     `. `");
    System.out.println(".--.`.; ,-.. ,.-, ;' `.-'       `    `.'.   .--.''-._        .; ");
    System.out.println("    `............---''     ;_.         )   (  '=    /         `-");
    System.out.println(" ~                                    /     `------'     .      ");
    System.out.println("                 ~                  ,'  \\|//            `'      ");
    System.out.println("                ~           ~       ; `. ''                   ..");
    System.out.println("                                     `.  )     \\'       .--'''");
  }

  public static void tree() {
    System.out.println("            .        +          .      .          .");
    System.out.println("     .            _        .                    .");
    System.out.println("  ,              /;-._,-.____        ,-----.__");
    System.out.println(" ((        .    (_:#::_.:::. `-._   /:, /-._, `._,");
    System.out.println("  `                 \\   _|`'=:_::.`.);  \\ __/ /");
    System.out.println("                      ,    `./  \\:. `.   )==-'  .");
    System.out.println("    .      ., ,-=-.  ,\\, +#./`   \\:.  / /           .");
    System.out.println(".           \\/:/`-' , ,\\ '` ` `   ): , /_  -o");
    System.out.println("       .    /:+- - + +- : :- + + -:'  /(o-) \\)     .");
    System.out.println("  .      ,=':  \\    ` `/` ' , , ,:' `'--'.--'---._/`7");
    System.out.println("   `.   (    \\: \\,-._` ` + '\\, ,'   _,--._,---':.__/");
    System.out.println("              \\:  `  X` _| _,\\/'   .-'");
    System.out.println(".               ':._:`\\____  /:'  /      .           .");
    System.out.println("                    \\::.  :\\/:'  /              +");
    System.out.println("   .                 `.:.  /:'  }      .");
    System.out.println("           .           ):_(:;   \\           .");
    System.out.println("                      /:. _/ ,  |");
    System.out.println("                   . (|::.     ,`                  .");
    System.out.println("     .                |::.    {\\");
    System.out.println("                      |::.\\  \\ `.");
    System.out.println("                      |:::(\\    |");
    System.out.println("              O       |:::/{ }  |                  (o");
    System.out.println("               )  ___/#\\::`/ (O '==._____   O, (O  /`");
    System.out.println("          ~~~w/w~'~~,\\` `:/,-(~`'~~~~~~~~'~o~\\~/~w|/~");
    System.out.println("dew   ~~~~~~~~~~~~~~~~~~~~~~~\\W~~~~~~~~~~~~\\|/~~");
  }

  public static void plant() {
    System.out.println("              ,");
    System.out.println("        }`-.   ,          ,");
    System.out.println("        \\ \\ '-' \\      .-'{");
    System.out.println("        _} .  | ,`\\   /  ' ;    .-;\\");
    System.out.println("       {    \\ |    | / `/  '-.,/ ; |");
    System.out.println("       { -- -.  '  '`-, .--._.' ;  \\__");
    System.out.println("        \\     \\ | '  /  |`.    ;    _,`\\");
    System.out.println("         '. '-     ' `_- '.`;  ; ,-`_.-'");
    System.out.println("     ,--.  \\    `   /` '--'  `;.` (`  _");
    System.out.println("  .--.\\  '._) '-. \\ \\ `-.    ;     `-';|");
    System.out.println("  '. -. '         __ '.  ;  ;     _,-' /");
    System.out.println("   { __'.\\  ' '-,/; `-'   ';`.- `   .-'");
    System.out.println("    '-.  `-._'  | `;     ;`'   .-'`");
    System.out.println("      <_ -'   ` .\\  `;  ;     (_.'`\\");
    System.out.println("      _.;-'``''-._'. `:;  ___, _.-' |");
    System.out.println("  .-'\'. '.` \\ \\_,_`\\ ;##`   `';  _.'");
    System.out.println(" /_'._\\ \\  \\__;#####./###.      \\`");
    System.out.println(" \\.' .'`/''`// (#######)###::.. _.'");
    System.out.println("  '.' .'  ; , |:.  `|()##`'''`");
    System.out.println("jgs `'-../__/_\\::   /O()()o");
    System.out.println("             ()'._.'`()()'");
  }

  public static void characterGraphics(int index) {

    String[][] charIndex = new String[17][15];

    String[] bear = { "      (()__(()", "     /       \\ ", "    ( /    \\  \\", "     \\ o o    /",
        "     (_()_)__/ \\ ", "    / _,==.____ \\", "   (   |--|      )", "   /\\_.|__|'-.__/\\_",
        "  / (        /     \\ ", "  \\  \\      (      /", "   )  '._____)    /    ", "(((____.--(((____/   ", "" };
    String[] fairy = { "    ,@@@@", "__ _ ),\\(\\   _,::;", ".)\\)\\_(((\\),:::::;", " `\\`._,)))))::::::`,",
        "   `.__/(((:::::::'", "      \\  (`:::::::.", "       ) .. `:::::;", "      /   ))  `::'", "      |  //",
        "      | //", "      \\ \\", "       `.\\", "         \\((", "          ` ` hjw", "" };
    String[] death = { "            ___", "           /   \\", "       /\\ | . . \\", "     ////\\|     ||",
        "   ////   \\ ___//\\", "  ///      \\      \\", " ///       |\\      |", "//         | \\  \\   \\",
        "/          |  \\  \\   \\", "           |   \\ /   /", "           |    \\/  /", "           |     \\/|",
        "           |      \\|", "           |       \\", "           |        |", "           |_________\\",
        "     from Dustin Slater", "" };
    String[] croc = { "                _ ___                /^^\\ /^\\  /^^\\_",
        "    _          _@)@) \\            ,,/ '` ~ `'~~ ', `\\.",
        "  _/o\\_ _ _ _/~`.`...'~\\        ./~~..,'`','',.,' '  ~:",
        " / `,'.~,~.~  .   , . , ~|,   ,/ .,' , ,. .. ,,.   `,  ~\\_",
        "( ' _' _ '_` _  '  .    , `\\_/ .' ..' '  `  `   `..  `,   \\_",
        " ~V~ V~ V~ V~ ~\\ `   ' .  '    , ' .,.,''`.,.''`.,.``. ',   \\_",
        "  _/\\ /\\ /\\ /\\_/, . ' ,   `_/~\\_ .' .,. ,, , _/~\\_ `. `. '.,  \\_",
        " < ~ ~ '~`'~'`, .,  .   `_: ::: \\_ '      `_/ ::: \\_ `.,' . ',  \\_",
        "  \\ ' `_  '`_    _    ',/ _::_::_ \\ _    _/ _::_::_ \\   `.,'.,`., \\-,-,-,_,_,",
        "   `'~~ `'~~ `'~~ `'~~  \\(_)(_)(_)/  `~~' \\(_)(_)(_)/ ~'`\\_.._,._,'_;_;_;_;_;", "" };
    String[] gramps = { "        .-''''.", "       /       \\", "   __ /   .-.  .\\", "  /  `\\  /   \\/  \\",
        "  |  _ \\/   .==.==.", "  | (   \\  /____\\__\\", "   \\ \\      (_()(_()", "    \\ \\            '---._",
        "     \\                   \\_", "  /\\ |`       (__)________/", " /  \\|     /\\___/", "|    \\     \\||VV",
        "|     \\     \\|''',", "|      \\     ______)", "\\       \\  /`", "jgs      \\(", "" };
    String[] you = { "    _.._", " ,''    '\\", "/ _      |", "[' ',  _ ;", "| (2'  '`\\", "`:`\\,   _'", " |[``-_`~\\",
        " \\|\\ _/''", "  '--'' itz", "" };
    String[] spouse = { "        w*W*W*W*w", "         \\'.'.'/", "          //` \\", "         (/a a\\)",
        "         (\\_-_/)", "        .-~'='~-.", "       /`~`'Y'`~`\\", "      / /(_ * _)\\ \\",
        "     / /  )   (  \\ \\", "     \\ \\_/ \\_//\\_/ /", "      \\/_) '*' (_\\/", "        |       |",
        "        |       |", "        |       |", "        |       |", "        |       |", "        |       |",
        "        |       |", "jgs     |       |", "        w*W*W*W*w", "" };
    String[] child = { "     __/\\__", ". _  \\''//", "-( )-/_||_\\", " .'. \\_()_/", "  |   | . \\", "  |mrf| .  \\",
        " .'. ,\\_____'." };
    String[] child2 = { "     ,-.,~~.", "   ,'///||\\`.", "  ///(((||)))\\.", " (((  ,   ,  )))", " _)))   -    |(_",
        "._//\\   -   /\\_.\\`-'_/`-._.-'\\-`-'", "  ' \\/=._.=\\/ hjw" };
    String[] troll = { "                            ,-.", "       ___,---.__          /'|`\\          __,---,___",
        "    ,-'    \\`    `-.____,-'  |  `-.____,-'    //    `-.",
        "  ,'        |           ~'\\     /`~           |        `.",
        " /      ___//              `. ,'          ,  , \\___      \\",
        "|    ,-'   `-.__   _         |        ,    __,-'   `-.    |",
        "|   /          \\/\\_  `   .    |    ,      _/\\          \\   |",
        "\\  |           \\ \\`-.___ \\   |   / ___,-'/ /           |  /",
        " \\  \\           | `._   `\\  |  //'   _,' |           /  /",
        "  `-.\\         /'  _ `---'' , . ``---' _  `\\         /,-'",
        "     ``       /     \\    ,='/ \\`=.    /     \\       ''", "             |__   /|\\_,--.,-.--,--._/|\\   __|",
        "             /  `./  \\`\\ |  |  | /,//' \\,'  \\", "eViL        /   /     ||--+--|--+-/-|     \\   \\",
        "           |   |     /'\\_\\_\\ | /_/_/`\\     |   |", "            \\   \\__, \\_     `~'     _/ .__/   /",
        "             `-._,-'   `-._______,-'   `-._,-'" };
    String[] wizard = { "      -. -. `.  / .-' _.'  _", "     .--`. `. `| / __.-- _' `",
        "    '.-.  \\  \\ |  /   _.' `_", "    .-. \\  `  || |  .' _.-' `.", "  .' _ \\ '  -    -'  - ` _.-.",
        "   .' `. %%%%%   | %%%%% _.-.`-", " .' .-. ><(@)> ) ( <(@)>< .-.`.", "   (('`(   -   | |   -   )''))",
        "  / \\#)\\    (.(_).)    /(#//\\", " ' / ) ((  /   | |   \\  )) (`.`.", " .'  (.) \\ .md88o88bm. / (.) \\)",
        "   / /| / \\ `Y88888Y' / \\ | \\ \\", " .' / O  / `.   -   .' \\  O \\ \\", "  / /(O)/ /| `.___.' | \\(O) \\",
        "   / / / / |  |   |  |\\  \\  \\ \\", "   / / // /|  |   |  |  \\  \\ \\  VK",
        " _.--/--/'( ) ) ( ) ) )`\\-\\-\\-._", "( ( ( ) ( ) ) ( ) ) ( ) ) ) ( ) )" };
    String[] leprechaun = { "            d8888o8888b", "            88888888888", "           d88888888888b",
        "   `-..____HHHHHHHHHHHHH____.,-'", "           /___, : .___\\", "          _) >=-( )-=< (_",
        "         ( (    / \\    ) )", "          \\_\\  ((_))  /_/", "            |)/  :  \\(|",
        "            |(,-----.)|", "            \\   ''`   /", "            |`---'---'|", "   Stef00   |   `-'   |" };
    String[] fairyQueen = {"   //.---.    .-'.","( (-/==^==.  /    ) ))","  /|))è é()./   .'"," ('-((\\_/( ))..' /","  \\ '-;_.-. ) ))","   '-(_ _)_\\ ) )).'","    / ) (/_ ) \\","(( ( /\\_/\\,/|  ) ))","    /  .  '.'.' ","   (  .\\  . '.___.","    \\_| \\  '.___/","     \\'._;.___) ","      \\_|-.\\ |mrf","       '--,-\\'.","          |/ \\ )","        ._/   \\|_","               \\ )","                \\|","               ._)"};
    String[] fairyEnforcer = {"   /\\  ,","  {Oo\\{o\\    .=.","  {o: \\:.\\  /   \\"," {O:'  \\:.-'_.-\\_)____","  {o:.  /`~('-./-----.\\","   }o: // /|         `/\\","  {O:'// /-'         /\\/\\","  }o-/( <___    \\'/ /\\/\\/\\"," /o./  ;--._)====* -\\/\\/\\/"," `'`\\  \\        /.\\  `''''`","     \\  \\","      \\  \\","jgs   /`\\ )","      |/| |","    _//  \\|","   | /   ||","   |/   / |","        `\\|","          '"};
    String[] cannibal1 = {"                 /\\","                 ||","   ____ (((+))) _||_","  /.--.\\  .-.  /.||.\\"," /.,   \\(0.0)// || \\","/;`';/\\ \\|m|//  ||  ;\\","|:   \\ \\__`:`____||__:|","|:    \\__ \\T/ (@~)(~@)|","|:    _/|     |\\_\\/  :|","|:   /  |     |  \\   :|","|'  /   |     |   \\  '|"," \\_/    |     |    \\_/","        |     |","        |_____|","    jgs |_____|"};
    String[] cannibal2 = {"              __.......__","            .-:::::::::::::-.","          .:::''':::::::''':::.","        .:::'     `:::'     `:::. ","   .'\\  ::'   ^^^  `:'  ^^^   '::  /`.","  :   \\ ::   _.__       __._   :: /   ;"," :     \\`: .' ___\\     /___ `. :'/     ; ",":       /\\   (_|_)\\   /(_|_)   /\\       ;",":      / .\\   __.' ) ( `.__   /. \\      ;",":      \\ (        {   }        ) /      ;" ," :      `-(     .  ^'^  .     )-'      ;","  `.       \\  .'<`-._.-'>'.  /       .'","    `.      \\    \\;`.';/    /      .'"," jgs  `._    `-._       _.-'    _.'","       .'`-.__ .'`-._.-'`. __.-'`.","     .'       `.         .'       `.","   .'           `-.   .-'           `."};
    String[] dragon = {"              ,,))))))));,","           __)))))))))))))),","\\|/       -\\(((((''''((((((((.","-*-==//////((''  .     `)))))),","/|\\      ))| o    ;-.    '(((((                                  ,(,","         ( `|    /  )    ;))))'                               ,_))^;(~","            |   |   |   ,))((((_     _____------~~~-.        %,;(;(>';'~","            o_);   ;    )))(((` ~---~  `::           \\      %%~~)(v;(`('~","                  ;    ''''````         `:       `:::|\\,__,%%    );`'; ~","                 |   _                )     /      `:|`----'     `-'","           ______/\\/~    |                 /        /","         /~;;.____/;;'  /          ___--,-(   `;;;/","        / //  _;______;'------~~~~~    /;;/\\    /","       //  | |                        / ;   \\;;,\\","      (<_  | ;                      /',/-----'  _>","       \\_| ||_                     //~;~~~~~~~~~","           `\\_|                   (,~~  -Tua Xiong","                                   \\~\\","                                    ~~"};
    
    charIndex[0] = bear;
    charIndex[1] = fairy;
    charIndex[2] = death;
    charIndex[3] = croc;
    charIndex[4] = gramps;
    charIndex[5] = you;
    charIndex[6] = spouse;
    charIndex[7] = child;
    charIndex[8] = child2;
    charIndex[9] = troll;
    charIndex[10] = wizard;
    charIndex[11] = leprechaun;
    charIndex[12] = fairyQueen;
    charIndex[13] = fairyEnforcer;
    charIndex[14] = cannibal1;
    charIndex[15] = cannibal2;
    charIndex[16] = dragon;

    for (int x = 0; x < charIndex[index].length; x++) {
      System.out.println(charIndex[index][x]);
    }
  }

  public static String[][] letters() {
    String[][] array = new String[27][8];

    // Alphabet
    String[] a = { " _______ ", "(  ___  )", "| (   ) |", "| (___) |", "|  ___  |", "| (   ) |", "| )   ( |",
        "|/     \\|" };
    String[] b = { " ______  ", "(  ___ \\ ", "| (   ) )", "| (__/ / ", "|  __ (  ", "| (  \\ \\ ", "| )___) )",
        "|/ \\___/ " };
    String[] c = { " _______ ", "(  ____ \\", "| (    \\/", "| |      ", "| |      ", "| |      ", "| (____/\\",
        "(_______/" };
    String[] d = { " ______  ", "(  __  \\ ", "| (  \\  )", "| |   ) |", "| |   | |", "| |   ) |", "| (__/  )",
        "(______/ " };
    String[] e = { " _______ ", "(  ____ \\", "| (    \\/", "| (__    ", "|  __)   ", "| (      ", "| (____/\\",
        "(_______/" };
    String[] f = { " _______ ", "(  ____ \\", "| (    \\/", "| (__    ", "|  __)   ", "| (      ", "| )      ",
        "|/       " };
    String[] g = { " _______ ", "(  ____ \\", "| (    \\/", "| |      ", "| | ____ ", "| | \\_  )", "| (___) |",
        "(_______)" };
    String[] h = { "         ", "|\\     /|", "| )   ( |", "| (___) |", "|  ___  |", "| (   ) |", "| )   ( |",
        "|/     \\|" };
    String[] i = { "_________", "\\__   __/", "   ) (   ", "   | |   ", "   | |   ", "   | |   ", "___) (___",
        "\\_______/" };
    String[] j = { "_________", "\\__    _/", "   )  (  ", "   |  |  ", "   |  |  ", "   |  |  ", "|\\_)  )  ",
        "(____/   " };
    String[] k = { " _       ", "| \\    /\\", "|  \\  / /", "|  (_/ / ", "|   _ (  ", "|  ( \\ \\ ", "|  /  \\ \\",
        "|_/    \\/" };
    String[] l = { " _       ", "( \\      ", "| (      ", "| |      ", "| |      ", "| |      ", "| (____/\\",
        "(_______/" };
    String[] m = { " _______ ", "(       )", "| () () |", "| || || |", "| |(_)| |", "| |   | |", "| )   ( |",
        "|/     \\|" };
    String[] n = { " _       ", "( (    /|", "|  \\  ( |", "|   \\ | |", "| (\\ \\) |", "| | \\   |", "| )  \\  |",
        "|/    )_)" };
    String[] o = { " _______ ", "(  ___  )", "| (   ) |", "| |   | |", "| |   | |", "| |   | |", "| (___) |",
        "(_______)" };
    String[] p = { " _______ ", "(  ____ )", "| (    )|", "| (____)|", "|  _____)", "| (      ", "| )      ",
        "|/       " };
    String[] q = { " _______ ", "(  ___  )", "| (   ) |", "| |   | |", "| |   | |", "| | /\\| |", "| (_\\ \\ |",
        "(____\\/_)" };
    String[] r = { " _______ ", "(  ____ )", "| (    )|", "| (____)|", "|     __)", "| (\\ (   ", "| ) \\ \\__",
        "|/   \\__/" };
    String[] s = { " _______ ", "(  ____ \\", "| (    \\/", "| (_____ ", "(_____  )", "      ) |", "/\\____) |",
        "\\_______)" };
    String[] t = { "_________", "\\__   __/", "   ) (   ", "   | |   ", "   | |   ", "   | |   ", "   | |   ",
        "   )_(   " };
    String[] u = { "         ", "|\\     /|", "| )   ( |", "| |   | |", "| |   | |", "| |   | |", "| (___) |",
        "(_______)" };
    String[] v = { "         ", "|\\     /|", "| )   ( |", "| |   | |", "( (   ) )", " \\ \\_/ / ", "  \\   /  ",
        "   \\_/   " };
    String[] w = { "         ", "|\\     /|", "| )   ( |", "| | _ | |", "| |( )| |", "| || || |", "| () () |",
        "(_______)" };
    String[] x = { "         ", "|\\     /|", "( \\   / )", " \\ (_) / ", "  ) _ (  ", " / ( ) \\ ", "( /   \\ )",
        "|/     \\|" };
    String[] y = { "         ", "|\\     /|", "( \\   / )", " \\ (_) / ", "  \\   /  ", "   ) (   ", "   | |   ",
        "   \\_/   " };
    String[] z = { " _______  ", "/ ___   ) ", "\\/   )  | ", "    /   ) ", "   /   /  ", "  /   /   ", " /   (_/\\ ",
        "(_______/ " };
    String[] apostrophe = { " _ ", "( )", "|/ ", "   ", "   ", "   ", "   ", "   ", "   " };

    array[0] = a;
    array[1] = b;
    array[2] = c;
    array[3] = d;
    array[4] = e;
    array[5] = f;
    array[6] = g;
    array[7] = h;
    array[8] = i;
    array[9] = j;
    array[10] = k;
    array[11] = l;
    array[12] = m;
    array[13] = n;
    array[14] = o;
    array[15] = p;
    array[16] = q;
    array[17] = r;
    array[18] = s;
    array[19] = t;
    array[20] = u;
    array[21] = v;
    array[22] = w;
    array[23] = x;
    array[24] = y;
    array[25] = z;
    array[26] = apostrophe;

    return array;
  }
  
  public static String nameCheck(int character) {
    Scanner input = new Scanner(System.in);
    String[] family = { "Player", "Wife", "Son", "Daughter", "Grandpa" };

    boolean progress = false;
    String name = "";
    while (!(progress)) {
      int badCounter = 0;
      System.out.print("Enter " + family[character] + " Name (4 CHARACTERS MAX): ");
      name = input.nextLine() + "";

      if (name.length() > 4) {
        System.out.println("ERROR: MUST BE 4 CHARACTERS LONG OR LESS.");
        progress = false;
      }
      for (int vv = 0; vv < name.length(); vv++) {
        if (name.charAt(vv) >= '1' && name.charAt(vv) <= '9') {
          badCounter += 1;
        }
        if (!((name.charAt(vv) >= 'a' && name.charAt(vv) <= 'z')
            || (name.charAt(vv) >= 'A' && name.charAt(vv) <= 'Z'))) {
          badCounter += 1;
        }
      }
      if (badCounter > 0) {
        System.out.println("ERROR: CANNOT USE NUMBERS OR SPECIAL CHARACTERS.");
        progress = false;
      } else {
        progress = true;
      }
    }
    return name;
  }

  public static void bigText(String[][] array, String name, int apostrophe) {
    int[] numerals = new int[4];
    for (int yy = 0; yy < name.length(); yy++) {
      if (name.charAt(yy) >= 'a' && name.charAt(yy) <= 'z') {
        numerals[yy] = name.charAt(yy) - 'a';
      } else if (name.charAt(yy) >= 'A' && name.charAt(yy) <= 'Z') {
        numerals[yy] = name.charAt(yy) - 'A';
      }
    }
    for (int gg = 0; gg < 8; gg++) {
      System.out.print(array[numerals[0]][gg] + "  ");
      if (gg < 8) {
        if (name.length() == 1) {
        }
        if (name.length() == 2) {
          System.out.print(array[numerals[1]][gg] + "  ");
        }
        if (name.length() == 3) {
          System.out.print(array[numerals[1]][gg] + "  ");
          System.out.print(array[numerals[2]][gg] + "  ");
        }
        if (name.length() == 4) {
          System.out.print(array[numerals[1]][gg] + "  ");
          System.out.print(array[numerals[2]][gg] + "  ");
          System.out.print(array[numerals[3]][gg] + "  ");
        }
        if (apostrophe == 1) {
          System.out.print(array[26][gg] + "  ");
          System.out.print(array[18][gg] + "  ");
        }
      }
      System.out.println();
    }
  }

  public static String[] menu() {
    String lineBreaker = "-----------------------------------------------------------";
    String[][] array = letters();
    String[] familyValues = new String[5];
    // YOU
    String name = nameCheck(0);
    characterGraphics(5);
    bigText(array, name, 0);
    System.out.println();
    // WIFE
    String spouse = nameCheck(1);
    characterGraphics(6);
    bigText(array, spouse, 0);
    System.out.println();
    // SON
    String son = nameCheck(2);
    characterGraphics(7);
    bigText(array, son, 0);
    System.out.println();
    // DAUGHTER
    String daughter = nameCheck(3);
    characterGraphics(8);
    bigText(array, daughter, 0);
    System.out.println();
    // GRAMPS
    String grandpa = nameCheck(4);
    characterGraphics(4);
    bigText(array, grandpa, 0);
    System.out.println();

    familyValues[0] = name;
    familyValues[1] = spouse;
    familyValues[2] = son;
    familyValues[3] = daughter;
    familyValues[4] = grandpa;
    pause(1000);
    clear_console();
    System.out.println();
    System.out.println();
    System.out.println(lineBreaker);
    bigText(array, name, 1);

    for (int bb = 0; bb < 8; bb++) {
      System.out.print("    " + array[19][bb]);
      if (bb < 8) {
        System.out.print("    " + array[0][bb]);
        System.out.print("    " + array[11][bb]);
        System.out.print("    " + array[4][bb]);
      }
      System.out.println();
    }
    System.out.println(lineBreaker);

    System.out.println();
    return familyValues;
  }

  public static int choiceOfVacation(String[] name) {
    crossroads();
    pause(750);
    int destination;
    Scanner input = new Scanner(System.in);
    Boolean switch1 = false;
    System.out.print("Daughter " + name[3]
        + " excitedly suggests everyone travel to Freakin' Fantastical Fairyland, a place brimming with insufferable moth creatures and oversaturated colors. \n'Pleeeeaaaaassssseee?' She whines. \n\nGrandpa "
        + name[4] + " despises the happiness, it reminds him of death.\n\nSon " + name[2]
        + " yearns to play in the forest, and see the dragons.\n\nWife " + name[1]
        + " doesn't care.\n\n1. Travel to Fairyland? ");
    String choice = input.nextLine() + "";
    while (!(switch1)) {
      if (choice.equals("1")) {
        switch1 = true;
      } else {
        System.out.print("Value must be between (1-1): ");
        choice = input.nextLine() + "";
      }
    }
    destination = Integer.parseInt(choice);
    return destination;
  }

  public static int setDressing() {
    castle();
    pause(750);
    int gameStart = 0;
    Scanner input = new Scanner(System.in);
    Boolean switch1 = false;
    System.out.print(
        "Your kingdom, a once vast civilization of wealth and prosperity, now in ruin. \nThe sheer devastation and horror, brought upon by: \n\n1. A ruthless campaign, a coup, led by your selfish, baboon bastard of a man you once considered to be your brother. \n\n2. A vicious plague, spurred from a curse, orchestrated through diabolical witchcraft via a coven of nasty women. \n\n3. A fault in the Earth, which swallowed the land into the abyss. \n\n4. God himself, his hand stretched out from the heavens and smashed everything into pulp, how could he? \n\n(Enter choice 1 - 4) ");
    String choice = input.nextLine() + "";
    while (!(switch1)) {
      if ((choice.equals("1")) || (choice.equals("2")) || (choice.equals("3")) || (choice.equals("4"))) {
        switch1 = true;
      } else {
        System.out.print("Value must be between (1-4): ");
        choice = input.nextLine() + "";
      }
    }
    System.out.println(
        "Your friends, your caretakers, your secret lovers, your private army, all have perished. \nBut at least your lovely family is still here...\n...\nBut it's time to say goodbye to the ashes of the past, and pave a new kingdom, set forth, through native, untouched lands. \nReady for your influence. \nYou pack up your belongings and gather your family, and so, start your journey, towards greener pastures.\n");

    gameStart = Integer.parseInt(choice);
    return gameStart;
  }

  public static void explosion() {
    String start = "         ,\n      \\  :  /\n   `. __/ \\__ .'\n   _ _\\     /_ _\n      /_   _\\\n    .'  \\ /  `.\n      /  :  \\    hjw\n         '\n";

    String end = "         /\\\n   .--._/  \\_.--.\n    `)        (`\n _.-'          '-._\n'-.              .-'\n    `)          ('\n    /.-'-.  .-'-.\\\n    `     \\/ jgs\n";

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 23; j++) {
        System.out.println();
      }
      System.out.println(start);
      for (int j = 0; j < 23; j++) {
        System.out.println();
      }
      pause(150);
      System.out.println(end);
      for (int k = 0; k < 23; k++) {
        System.out.println();
      }
      pause(150);
    }
  }
  // method given by professor to pause the console before it continues
  public static void pause(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
  }
  // phrases
  // call the phrase:
  
  public static String getRandomPhrase() {
    String[] phrases = {
      "Trust me, you don't want to do this!",
      "Let's fight",
      "You are no match for me",
      "You should leave while you still can",
      "Stop!" };
    Random random = new Random();
    int index = random.nextInt(phrases.length);
    return phrases[index];
  }


  public static int[] fairyLand(int[] mastVals, String[] names) {
    tree();
    pause(750);
    Scanner input = new Scanner(System.in);
    System.out.print(
    "The fairies are a delicate race, yet surprisingly, still exist. "+
    "\nTheir kingdom is situated in the Enchanted Forest. "+
    "\nThe brochure indicates that held by the fairies is an ancient artifact, with the abilty to grant wishes. "+
    "\nThis is how you shall rebuild your kingdom, with your loving family by your side.\n");
    pause(3500);
    mastVals = fryEv1(mastVals, names);
    mastVals = fryEv2(mastVals, names);
    mastVals = fryEv3(mastVals, names);
    pause(750);
    System.out.println("As you travel along the path, a fairy guard attacks!");
    pause(1750);
    int[] fairy = {30,7,7};
    battle(mastVals, fairy, 13);
    mastVals = fryEv4(mastVals, names);
    mastVals = fryEv5(mastVals, names);
    pause(750);
    System.out.println("As you travel along the path, a wild cannibal attacks!");
    pause(1750);
    int[] cannibal = {40,9,12};
    battle(mastVals, cannibal, 14);
    mastVals = fryEv6(mastVals, names);
    mastVals = fryEv7(mastVals, names);
    return mastVals;
  }

  public static int[] fryEv1(int[] mastVals, String[] names) {
    characterGraphics(1);
    pause(750);
    Scanner input = new Scanner(System.in);
    int[] fairy = {30,7,7};
       System.out.print("You begin to follow the path and then suddenly..." +
    "\nYour hear the soft sound of a footstep, chittering about behind you. It's a fairy!" +
    "\nIncredible! It dances and prances around your family." +
    "\n\"My name is Lilywink, and I have a secret to tell you, come little one. Hear my spoils\":"+
    // choices
    "\n\n1. Allow daughter " + names[3] + " to listen to the fairy." +
    "\n\n2. Smash the fairy between your hands."+
    "\n\n3. Allow wife " +  names[1] + " to address the situation."+
    "\n\n4. Disperse a dash of salt onto the helpless fairy.");   

    playerNames(mastVals, names);
    
    // event 1 decision
    Boolean switch1 = false;
      while (!(switch1)) {
        System.out.print("\nEnter your choice (1-4):");
        char event1Choice = input.next().charAt(0);
        input.nextLine(); // clear scanner
        if (event1Choice == '1') {
          System.out.println("\nThe fairy approaches " + names[3] + ", stabbing her needle like feet into the lobes of her ear." +
              "\n" + names[3] + "'s smile drops. She now looks distressed." +
              "\nThe fairy lets out a life ending screech into " + names[3] + "'s ear! Bursting her eardrums!" +
              "\n" + names[3] + " turns towards you and collapses to the ground where she begins to bleed out." +
              "\nThe fairy giggles."); 
              mastVals[5] = 0;
              pause(750);
              battle(mastVals, fairy, 1);
              System.out.println("A terrible tragedy. ");
          switch1 = true;
        } 
        else if (event1Choice == '2') {
          mastVals[2] += 1;
          System.out.println("\nShe doesn't expect it." +
              "\nA misshapen rainbow stains your hands as you separate them between the smushed fairy." +
              "\nYour attack stat has been increased to: " + mastVals[2]);
          switch1 = true;
        } 
        else if (event1Choice == '3') {
          mastVals[0] -= 15;
          System.out.println("\n " + names[1] + " politely informs the fairy that " + names[3] + " is not allowed to talk to strangers."+
              "\nThe fairy found this very disrespectful that you decided not trust her."+
              "\nShe sprays a mysterious powder all over you and flutters away."+
              "\nIt stings to the touch of your skin."+
              "\nYour health stat has been decreased to: " + mastVals[0]);
          switch1 = true;
        } 
        else if (event1Choice == '4') {
          System.out.println("\nThe microscopic rocks pummel the tiny fairy." +
              "\nOverwhelming her as she crashes into the ground. Dehydrated." +
              "\nEveryone steps over her.");
          mastVals[7] = 1;
          switch1 = true;
        } 
        else 
          System.out.print("Invalid input: (1-4): ");
        }
    
    System.out.println("You press on.");
    pause(2000);
    return mastVals;
  }

  public static int[] fryEv2(int[] mastVals, String[] names) {
    plant();
    pause(750);
    Scanner input = new Scanner(System.in);
    System.out.print(
        "You venture deeper into the forest, thickets of brush shroud the sun, and a guiding light shines bright down the path you walk down." +
        "\nIt's a glowing plant."+
        "\n\"Take it!\""+
        "\nInstructs grandpa " + names[4] +
        "\n\"Fairies hate the light!\""+
        "\n\n1. Investigate the plant itself."+
        "\n\n2. Rest by the light."+
        "\n\n3. Ignore the plant and continue moving."+
        "\n\n4. Ask grandpa " + names[4] + " to pluck the plant, he's gravitating to its warmth anyway.");

    playerNames(mastVals, names);
    // enter your choice
    Boolean switch1 = false;
    while(!(switch1)) {
      System.out.print("\nEnter your choice (1-4):");
      char event2Choice = input.next().charAt(0);
      input.nextLine(); // clear scanner
      // choice A
      if (event2Choice == '1') {
        mastVals[0] -= 5;
        mastVals[1] += 2;
        mastVals[2] -= 1;
        System.out.println("\nYou bend down to inspect glowing plant." +
            "\nA wire hangs down the base of the stem, attached to a strange box contraption."+
            "\nYou carefully detach the glowing face flower from the wire." +
            "\nThe bulb burns your hand."+ 
            "\nYour health stat has been decreased to: " + mastVals[0] +
            "\nYour defense stat has been increased to: " + mastVals[1] +
            "\nYour attack stat has been decreased to: " + mastVals[2] +
            "\nThe light comforts you.");
            mastVals[8] = 1;
            switch1 = true;
    }
    // choice B
      else if (event2Choice == '2') {
        System.out.println("\nThe spot cozy enough to comfortably sit by the plant." +
            "\nAfter a few hours, the plant begins to emit a strange ticking noise." +
            "\nsitting closest to the plant, grandpa " + names[4] + " begins to shuffle through its leaves." +
            "\n\"BOOM!\" The plant blows up in granpa " + names[4] + "'s hands, dismembering him into a thousand little pieces!");
        mastVals[6] = 0;
        System.out.println("\nYou stare blankly at the scene in front of you.");
        switch1 = true;
    }
    // choice C
      else if (event2Choice == '3') {
        System.out.println("\nIt's not an artifact. It's not important." +
            "\nYou continue moving in the surrounding darkness.");
        switch1 = true;
    }
    // choice D
      else if (event2Choice == '4') {
        System.out.println("\nYou ask grandpa to to pick up the plant." +
            "\nAs he holds the glowing plant it begins to glow brighter and suddenly makes a rapid ticking noise." +
            "\n\"BOOM!\" The plant blows up in granpa " + names[4] + "'s hands, dismembering him into a thousand little pieces!");
        mastVals[6] = 0;
        System.out.println("\nYou stare blankly at the scene in front of you.");
        switch1 = true;
    } else
      System.out.print("Invalid input: (1-4): ");
    }

    System.out.println("You press on.");
    pause(2000);
    return mastVals;
  }
    
  public static int[] fryEv3(int[] mastVals, String[] names) {
    characterGraphics(10);
    pause(750);
    Scanner input = new Scanner(System.in);
    System.out.print("Eventually, the forest lights up again, you feel the sun beat down on your face."+
    "\nTurning to get a head count of the family, you see a disgusting, wrinkly man covered in liverspots."+
    "\nThat's not grandpa...The old man lunges towards you and grabs your face."+
    "\n\"I am the Berlin the Great, a Wizard!\" he proclaims."+
    "\n\"You have to know something, these fairies...they're evil. Monsters even. I possess great knowledge you must hear, so hear me!\":"+
    "\n\n 1. Smile awkwardly and nod."+
    "\n\n 2. Gently shove him into the jagged brush below.");

    playerNames(mastVals, names);

    Boolean switch1 = false;
    while(!(switch1)) {
      // enter your choice
      System.out.print("\nEnter your choice (1-2):");
      char event3Choice = input.next().charAt(0);
      input.nextLine(); // clear
        if (event3Choice =='1') {
          System.out.println("\nHis eyes light up, he takes a deep breath and begins to recount his important information."+
                             "\n\"You know. The fairies are an evil bunch that cannot be trusted, their one and only weakness is water\""+
                             "\nHe hands you a water bottle."+
                             "\nYou absolutely go along with this knowlegde, waiting for him to let you go."+
                             "\nKeeping it in mind incase you encounter more of those mischievous fairies."+
                             "\nAs a reward for listening to him when no one else would, he bleses you with good luck."); 
        mastVals[0]+=5;
        mastVals[1]+=1;
        mastVals[2]+=1;
        System.out.println("\nYour health stat has been increased to: " + mastVals[0] +
                           "\nYour defense stat has been increased to: " + mastVals[1] +
                           "\nYour attack stat has been dincreased to: " + mastVals[2]);
        System.out.println("\nAs you are leaving the wizard yells out, \"Stick to the path label on your map as it's the safest route!\"");
          mastVals[9] = 1;
          switch1 = true;
          pause(3000);
        }
        else if (event3Choice =='2') {
          System.out.println("\nHe softly crashes into the thick thorns. Withering away."+
                             "\nYour stats remain the same and you move along missing out on significant information that may affect a later decision.");
          switch1 = true;
      } else System.out.println("Invalid input: (1-2): ");
    }
    
    System.out.println("\nYou press on.");
    pause(2000);
    return mastVals;
  }

  public static int[] fryEv4(int[] mastVals, String[] names) {
    characterGraphics(9);
    pause(750);
    int[] troll = {50,10,1};
    Scanner input = new Scanner(System.in);
    System.out.print("Coming upon a ravine, leads to the only bridge you can cross to the other side."+
                     "\nA dozen bodies lay smushed together."+
                     "\nTheir colorful clothes melted into the skin."+
                     "\nA troll sits cross-legged in-between the gore."+
                     "\n\"YOU WANT CROSS? PLAY TIC-TAC-TOE.\" It calmly screams:"+
                     "\n\n1. Oblige the beast?"+
                     "\n\n2. Let son "+ names[2] + " take a crack at the game instead. He's jumping in excitement anyway.");
    
    playerNames(mastVals, names);

    Boolean switch1 = false;
    while(!(switch1)) {
      // enter your choice
      System.out.print("\nEnter your choice (1-2): ");
      int event4Choice = input.next().charAt(0);
      input.nextLine(); // clear scanner

        if (event4Choice == '1') {
          System.out.println("\nIt begins childishly drawing patterns onto the ground."+
                            "You charge at the beast while it is distracted.");
          battle(mastVals, troll, 9);
          switch1 = true;

        } else if (event4Choice == '2') {
    
          System.out.println("\n" + names[2] + " hops next to the beast to join in." +
              "\nYou walk past the two as they play.");
          mastVals[4] = 0;
          switch1 = true;
        } else
          System.out.print("Invalid input (1-2): ");
    }
    
    System.out.println("\nYou press on.");
    pause(2000);
    return mastVals;
  }

  public static int[] fryEv5(int[] mastVals, String[] names) {
      characterGraphics(11);
      pause(750);
      Scanner input = new Scanner(System.in);
      System.out.print("The air reeks of fairy dust."+
                       "\nYou can almost feel their kingdom in your palms."+
                       "\nThe thought is interrupted however by the anguished screams of a Leprechaun."+
                       "\nA pitiful creature indeed."+
                       "\nHe's trying to squirm his body out of quicksand:"+
                       "\nThe leprechaun yells out, \"Please help me, I dont want to die like this! I can make it worth your while!\"" +
                       "\n\n1. Inquire the Leprechaun about his predicament."+
                       "\n\n2. Extend your arm in his direction."+
                       "\n\n3. Avert your eyes and continue on the path."+
                       "\n\n4. Chuck a rock at his face.");
    playerNames(mastVals, names);
        
    Boolean switch1 = false;
    while(!(switch1)) {
        System.out.print("\nEnter your choice (1-4):");
        char event5Choice = input.next().charAt(0);
        input.nextLine(); //clear scanner 

        if (event5Choice == '1') {
            mastVals[0] += 5;
            System.out.println("\nHe glares at you. That was silly." +
            "\nYou decide to help the leprechaun out of the quicksand, just out of embarassment earlier." +
            "\nYou reach down and grab the leprechaun's puny arm." +
            "\n\"Thank you. I am eternally in your debt. Take this...\"" + 
            "\nWith the same arm you are pulling him by, the leprechaun gives you a piece of gold!" + 
            "\n\"Beware of the fairys, they are preparing for a battle when you arrive\""+
            "\nduly noted, deep in thought, you continue to pull on the leprechaun's arm as hard as you can." +
            "\nHe is freed!" + 
            "\nOr his arm at least, which was torn neatly off the base of the leprechaun's shoulder socket." + 
            "\nThe leprechaun stares in horror at his dismembered arm, before fainting." +
            "\nHe succumbs to his fate and sinks further into the quicksand."+
            "\nYou stare dumbfounded. Then take a bite out of the gold piece he gave you."+
            "\nIt's real alright."+
            "\nYour health stat has been increased to: " + mastVals[0]);
          
            mastVals[10] = 1;
            switch1 = true;
        }
        else if (event5Choice == '2') {
            System.out.println("\nYou reach down and grab the leprechaun's puny arm, and pull as hard as you can." +
            "\nHe is freed!" + 
            "\nOr his arm at least, which was torn neatly off the base of the leprechaun's shoulder socket." + 
            "\nThe leprechaun stares in horror at his dismembered arm, before fainting." +
            "\nHe succumbs to his fate and sinks further into the quicksand.");
            switch1 = true;
        }
          else if (event5Choice == '3') {
            mastVals[1] -= 1;
            System.out.println("\nYou choose to ignore him, instead advancing towards towards the fairy kingdom." +
            "\nAs you walk away the leprechaun keeps sinking, dying a slow and painful death." + 
            "\nJust before he is completely submerged he shoots dangerous magic at you!" +
            "\nYou and your family will continue to advance on the path towards the castle"+
            "\nYour defense stat has been decreased to: " + mastVals[1]);
            switch1 = true;

        }
        else if (event5Choice == '4'){
            System.out.println("\nYou taunt the leprechaun while he's sinking." +
            "\nPicking up a smooth stone and hurling it right at him.." +
            "\nThe stone goes clean through his eye socket leaving a large hole in his head.");
          switch1 = true;
        }
        else 
            System.out.print("Invalid input (1-4): ");
    }
        
      System.out.println("\nYou press on.");
      pause(2000);
      return mastVals;
  }
  public static int[] fryEv6(int[] mastVals, String[] names) {
     characterGraphics(3);
     pause(750);
    int[] dragon = {45,15,30};
     Scanner input = new Scanner(System.in);
     System.out.print("The fairy kingdom is just in your peripheral, astoundingly large."+ 
                      "\nSo close yet so far. \nBefore the kingdom rests a Dragon, no bigger than a castle, situated atop a rock."+
                      "\nAnd before the dragon, a reflective, emerald lake of acid:"+
                      "\n\n1. Perform a triple somersault off the edge of the lake, dropkick the dragon in the face, and gently land on the solid rock across the lake."+
                      "\n\n2. Make a casual remark about the Dragon's laziness while using dracist slurs in its proximity."+
                      "\n\n3. Instruct wife " + names[1] + " to imitate a Dragon's mating call."+
                      "\n\n4. Instruct wife " + names[1] + " to collect some of the acid. Just to make sure it's acid.");
    
     playerNames(mastVals, names);

     Boolean switch1 = false;
     while(!(switch1)) {
        System.out.print("\nEnter your choice (1-4):");
        char event5Choice = input.next().charAt(0);
        input.nextLine(); //clear scanner 

        if (event5Choice == '1'){

            System.out.println("You close your eyes. Pure silence."+
                              "\nYou smell the air. It burns your nostrils."+
                              "This is it. This is your moment."+
                              "You open your eyes."+
                              "The dragon is sitting right in front of you, staring.");
          battle(mastVals, dragon, 3);
          System.out.println("You snap the dragon's neck with quick precision and accuracy, with one kick to the head."+
                              "\nThen, clutching your family, you leap off into the air as you attempt to perform the somersault."+
                              "You feel yourself gliding in the air, spinning, like a ballerina."+
                              "You feel the ground under you."+
                              "SNAP! SNAP! Those were your knees making that sound."+
                              "They're bent the wrong way."+
                              "But that's okay, you can just wish them back to normal once you find the artifact."+
                              "Using only your hands and upperbody strength, you continue towards the fairyland kingdom.");
            switch1 = true;
        }
        else if (event5Choice == '2'){
            mastVals[2] -= 1;
            System.out.println("\nAs your words echo across the lake, the dragon's eyes begin to tear up." +
            "\nVisibly hurt, it leaves the lake looking for somewhere else to rest." + 
            "\nAs it moves through the acid, displacement ripples open up parts of the lake, making it possible to cross." + 
            "\nYou feel bad about the dragon, but know you can't do anything else about it." +
            "\nYour attack stat has been decreased to: " + mastVals[0]);
            switch1 = true;

        }
        else if (event5Choice == '3'){
          System.out.println("\nHer eyes narrow at you. Insulted." +
          "\n\"I am so done with you.\"" +
          "\nwife " + names[1] +" leaves you."+
          "\nYou decide to perform the mating call instead."+
          "\nIt works splendiculantly. The dragon swings by, scoops up you and any remaining family, and takes you to the other side.");
          mastVals[3] = 0;
          switch1 = true;
        }
        else if (event5Choice == '4'){
                System.out.println("\nWife " + names[1] + "obliges cautiously." + 
                "\nAs she is filling the bucket with acid a strong gust of wind blows past you." + 
                "\nSuddenly " + names[1] + " falls into the pit of acid." + 
                "\nShe instantly disintegrates..." +
                "\n..." + 
                "\n..." + 
                "\n..." + 
                "\nYou snap back to consciousness." + 
                "\n" + names[1]  + " is staring back at you with a bucket of acid. Confused." +
                "\nThe dragon remains rested at the rock." +
                "\nWhat a tease." +
                "\nFor the next 97 hours, you and " + names[1] + " pail out buckets of acid until a sizable chunk of the lake is dry."+
                "\nAllowing you to safely walk up to the dragon and attack it.");
                mastVals[11] = 1;
          switch1 = true;
        }
        else 
            System.out.print("Invalid input (1-4): ");
    }
        
      System.out.println("\nYou press on.");
       pause(2000);
     return mastVals;
     }
  public static int[] fryEv7(int[] mastVals, String[] names) {
    // HP. DEF, ATK, WIFE, SON, DAUGHTER, GRANDPA, SALT, LIGHT, WATER, GOLD, ACID
    // int[] masterValues = { 50, 5, 5, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    characterGraphics(12);
    pause(750);
    int[] fairyQueen = {60,20,20};
    Scanner input = new Scanner(System.in);
    System.out.println("\nYou advance on the path you are about to reach the castle gates" + 
         "\nCongratulations you have finally reach the castle" +
         "\nYou make your way through the castle and finally reach the throne room" +
         "\nThe throne room is where the artifact is being guarded by a group of armed fairries" +
         "\nSitting in throne is the very own Queen Satia" +
         "\nShe is furious with all the havoc you have caused throughout her kingdom" +
         "\nShe rises from the throne and speaks out, \"You have caused enough trouble, this is the end of the road for you and your revolting family!\"");

         System.out.println("\nThe Queen challenges you to a battle" +
             "\nYou have no other option but to kill or be killed...");
    
         System.out.println("\n1. You choose to fight the fairy Queen." +
                            "\n2. You try to reason with the Queen.");
    
         playerNames(mastVals, names);
    
         Boolean switch1 = false;
         while(!(switch1)) {
    
           System.out.print("\nEnter your final choice:");
           char event10choice = input.next().charAt(0);
           input.nextLine(); 
      
            if (event10choice == '1') {
              System.out.println("\nYou choose to fight Queen Satia" +
                 "\nYou prepare for an intense battle, only one will come out alive!"+
                 "\nThinking resourcefully, you throw all of your items at the queen just to see if she does indeed have a weakness.");
              Boolean killQueen = false;
                if (mastVals[7] == 1) {
                  System.out.println("\nRemembering your assault on Lilywink," +
                     "\nYou sprinkle a dash of salt into Queen Satia's face!" +
                     "\nShe looks unimpressed and unamused.");
                }
                if (mastVals[8] == 1) {
                  System.out.println("\nHeeding grandpa " + names[4] + "'s' advice," +
                     "\nYou shine the glowing plant at Queen Satia's face!" +
                     "\nShe winces for a second before looking absolutely irritated.");
                }                
                if (mastVals[10] == 1) {
                  System.out.println("\nYou're getting tense, perhaps gold is the answer!" +
                     "\nYou attempt to bribe Queen Satia with the fairyland kingdom, for one single gold nugget." +
                     "\nShe smacks the gold nugget out of your hand.");
                }
                if (mastVals[9] == 1) {
                  System.out.println("\nA long sigh." +
                     "\nMaybe the acid will kill her at least..." +
                     "\nAs you raise the acid bucket into the air at Queen Satia..." +
                     "\n...you realize that there's a giant hole at the bottom of the bucket." +
                     "\nQueen Satia laughs at the failed attempt.");
                }
                if (mastVals[11] == 1) {
                  System.out.println("\nYou suddenly remember that Berlin informed you about the fairries one and only weakness..." +
                     "\nWithout hesitation you reach for the bottle of water" +
                     "\nYou throw the bottle of water at the Queen who is standing fiercely" +
                     "\nThe Queen has no time to react as the water is approching her" +
                     "\nThe water hits the Queen and she instantly evaporates into thin air");
                      killQueen = true;
                }
              
                if (killQueen) {
                      System.out.println("\nYou have successfully beat the queen in her own castle" +
                     "\nThe rest of the farries all flee the castle as they watch their Queen fall" +
                     "\nYou notice the artifact you have been searching for across the room" +
                     "\nYou approach the artifact and grab it, theres only last thing to do" +
                     "\nYou look at your family one last time and close your eyes" +
                     "\nYou make the wish and appear in the throne room once again, however this time you are the king" + 
                     "\nAlong side you is your family, and your guards happen to be your very own army from your past kingdom");
          
                     System.out.println("\nCongratulations " + names[1] + " you have successfully survived Fairyland");
                     //game over
                     System.exit(0);
                }
              else {
                pause(750);
                battle(mastVals, fairyQueen, 12);
                    System.out.println("\nYou have successfully beat the queen in her own castle" +
                     "\nThe rest of the farries all flee the castle as they watch their Queen fall" + 
                     "\nYou notice the artifact you have been searching for across the room" +
                     "\nYou approach the artifact and grab it, theres only last thing to do" +
                     "\nYou look at your family one last time and close your eyes" +
                     "\nYou make the wish and appear in the throne room once again, however this time you are the king" + 
                     "\nAlong side you is your family, and your guards happen to be your very own army from your past kingdom");
          
                     System.out.println("\nCongratulations " + names[1] + " you have successfully survived Fairyland");
                     //game over
                     System.exit(0);
              }       
          }
             
      
             else if (event10choice == '2') {
               System.out.println("\nYou choose to try and reason with the Queen" +
                 "\nAs you speak to her of your familys past life and the journey you have face to reach the castle" +
                 "\nThe Queen seems unamused" +
                 "\nYou suddenly mention the enchanted artifact and she becomes enraged" + 
                 "\nThe queen suddenly throws her sharp staff in your direction" +
                 "\nYou have no time to react..." +
                 "\nYou look down and you notice that you have been impaled by her sharp staff." + 
                 "\nYou begin to bleed out slowly, you know for certain these are your final moments");
      
                 mastVals[0] = 0;
          
                 System.out.println("\nYou have died\nGame Over");
                 //game over
                 System.exit(0);
             }
             else {
               System.out.print("Invalid input (1-2): ");
             }
         }
    pause(2000);
    return mastVals;
  }
  public static void printDeathMessage() {
        System.out.println("\nYou have been defeated in battle, as you lay on the ground bleeding out you look at your family one last time."+
            "\nThey are mourning you as you take your last breath."+
            "\nYour Opponent begins to approch them, as you close your eyes you shed a single tear."+
            "\nYou hear the screams coming from your family as they are being torn to pieces."+
            "\nYou have failed to keep your family safe."+
            "\nGAME OVER!");
  }
}