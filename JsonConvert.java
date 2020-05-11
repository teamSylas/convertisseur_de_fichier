import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class JsonConvert {

    // Methode pour ecriture du ficher JSON
    public static void jSONConverter( String cheminFichierEntrer, String FichierSortir ) {

        // ajout de l'estamention au chemin de sortir
        String cheminFichierSortir = FichierSortir + ".json";

        // les attributs à insérer dans le JSON
        String numReference = "";
        String size = "";
        String price = "";
        String color = "";
        try {
            // fichier en lecture normale
            InputStream ips = new FileInputStream( cheminFichierEntrer );
            InputStreamReader ipsr = new InputStreamReader( ips );
            BufferedReader flux = new BufferedReader( ipsr );

            // fichier en lecture pour detecter les erreurs
            InputStream ips1 = new FileInputStream( cheminFichierEntrer );
            InputStreamReader ipsr1 = new InputStreamReader( ips1 );
            BufferedReader flux1 = new BufferedReader( ipsr1 );

            // fichier en écriture
            FileWriter fw = new FileWriter( cheminFichierSortir );
            BufferedWriter bw = new BufferedWriter( fw );
            PrintWriter fichierSortie = new PrintWriter( bw );
            String ligne;

            String chemin = new File( cheminFichierEntrer ).getName();
            // ecriture du richer JSON
            fichierSortie.print( "{" );
            fichierSortie.println( " " );
            fichierSortie.println( "  \"inputFile\" :" + "\"" + chemin + "\"," );
            fichierSortie.println( " " );
            fichierSortie.println( "\"references\" : [ {" );

            // on lit chaque ligne du fichier texte une par une
            while ( ( ligne = flux.readLine() ) != null ) {

                // On redefinit chaque linge du ficher txt en recuperant chaque
                // element avec comme separateur le ";" dans un tableau
                String str = ligne;
                String[] myStrings = str.split( ";" );

                // On recuper chaque mots dans une varable
                numReference = myStrings[0];
                color = myStrings[1];
                price = myStrings[2];
                size = myStrings[3];

                // ecriture du corps du ficher JSON
                if ( ( numReference.trim().length() == 10 && isInteger( numReference ) )
                        && ( color.equals( "R" ) || color.equals( "G" ) || color.equals( "B" ) )
                        && ( isDouble( price ) && ( isInteger( size ) ) ) ) {
                    fichierSortie.print( "  \"numReference \" : \"" );
                    fichierSortie.print( numReference );
                    fichierSortie.println( "\", " );
                    fichierSortie.print( "  \"size\" : " );
                    fichierSortie.print( size );
                    fichierSortie.println( ", " );
                    fichierSortie.print( "  \"price\" : " );
                    fichierSortie.print( price );
                    fichierSortie.println( ", " );

                    fichierSortie.print( "  \"color\" : \"" );
                    fichierSortie.print( color );
                    fichierSortie.println( "\" " );
                    fichierSortie.println( "}, {" );
                }

            }
            fichierSortie.println( "} ]," );

            fichierSortie.println( "\"errors \" : [{" );
            int compteur = 0;

            // on lit chaque ligne du fichier texte une par une pour indentifer
            // les erreurs
            while ( ( ligne = flux1.readLine() ) != null ) {

                // variable de message d'erreur
                List<String> message = new ArrayList<String>();

                // On redefinit chaque linge du ficher txt en recuperant chaque
                // element avec comme separateur le ";" dans un tableau
                String str = ligne;
                String[] myStrings = str.split( ";" );

                // On recuper chaque mots dans une varable
                numReference = myStrings[0];
                color = myStrings[1];
                price = myStrings[2];
                size = myStrings[3];

                if ( !( numReference.trim().length() == 10 ) && !( isInteger( numReference ) ) ) {
                    message.add( "Incorrect value for numReference" );
                }
                if ( !( color.equals( "R" ) || color.equals( "G" ) || color.equals( "B" ) ) ) {
                    message.add( "Incorrect value for color" );
                }
                if ( !( isDouble( price ) ) ) {
                    message.add( "Incorrect value for price" );
                }
                if ( !( isInteger( size ) ) ) {
                    message.add( "Incorrect value for price" );
                }
                compteur++;

                if ( !( numReference.trim().length() == 10 && isInteger( numReference ) )
                        || !( ( color.equals( "R" ) || color.equals( "G" ) || color.equals( "B" ) ) )
                        || !( ( isDouble( price ) ) || !( ( isInteger( size ) ) ) ) ) {
                    fichierSortie.print( "  \"line\" : " );
                    fichierSortie.print( compteur );
                    fichierSortie.println( ", " );
                    fichierSortie.print( "  \"message\" : \"" );
                    for ( String m : message ) {
                        fichierSortie.print( m );
                    }
                    fichierSortie.println( "\"," );
                    fichierSortie.print( "  \"value\" : \"" );
                    fichierSortie.print( ligne );
                    fichierSortie.println( "\"" );
                }
            }
            fichierSortie.println( "}]" );
            fichierSortie.println( "}" );
            // On ferme les flux

            flux.close();
            fichierSortie.close();
        } catch ( IOException e ) {
            e.printStackTrace();
            // ou
            System.out.println( "La convertion n'a pas marcher" );
        }

    }

    // Methode pour verifier un entier
    private static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }

    // Methode pour verifier un double
    private static boolean isDouble( String str ) {
        try {
            Double.parseDouble( str );
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }

}
