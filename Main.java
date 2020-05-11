
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner clavier = new Scanner( System.in );

    public static void main( String[] args ) throws IOException {
        // definition des attribus
        String input = "";
        String output = "";
        String formatSortir = "";
        List<String> message = new ArrayList<String>();

        // reseption du chemin du fichier texte enter au clavier
        System.out.println( "Donnee le Chemin du fichier texte " );
        input = clavier.nextLine();

        // controle de valisation sur Chemin du fichier texte
        if ( !cheminValide( input ) ) {
            message.add( "Le Chemin du fichier texte n'est pas valide" );
        }

        // reseption du chemin du fichier en sortie enter au clavier
        System.out.println( "Donne le Chemin du fichier en sortie " );
        output = clavier.nextLine();

        // controle de valisation sur Chemin du fichier en sortie
        if ( !cheminValide( input ) ) {
            message.add( "Le Chemin du fichier en sortie n'est pas valide" );
        }

        // reseption du chemin du format de sortie enter au clavier
        System.out.println( "Donnee le Format de sortie (XML ou JSON)de votre ficher" );
        formatSortir = clavier.nextLine();

        // controle de valisation du format de sortie
        if ( !( formatSortir.equals( "JSON" ) || formatSortir.equals( "XML" ) ) ) {
            message.add( "Le format de sortie de votre ficher doit etre soit XML ou JSON " );
        }

        // Ecriture du richer ou affichage des erreur
        if ( message.isEmpty() && ( formatSortir.equals( "XML" ) && ( formatSortir.trim().length() == 3 ) ) ) {
            XmlConvert.XmlConverter( input, output );
            System.out.println( "La transformation du fichier TXT dans un format XML est fait" );
        } else if ( message.isEmpty()
                && ( formatSortir.equals( "JSON" ) && ( formatSortir.trim().length() == 4 ) ) ) {
            JsonConvert.jSONConverter( input, output );
            System.out.println( "La transformation du fichier TXT dans un format JSON est fait" );

        } else {
            for ( String m : message ) {
                System.out.println( m );
            }
        }
    }

    // Methode pour valider les chemin fornier en enter
    public static boolean cheminValide( String filepath ) {
        File file = new File( filepath );
        if ( file.isAbsolute() ) {
            return true;
        } else {
            return false;
        }
    }
}
