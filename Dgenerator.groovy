
@Grab('de.sven-jacobs:loremipsum:1.0')
import de.svenjacobs.loremipsum.LoremIpsum
import java.security.MessageDigest

import groovy.transform.CompileStatic

@CompileStatic
trait Dgenerator {

    private LoremIpsum    loremIpsum
    private StringBuilder generatedField
    private Random        randomNumberGenerator
    final Date MIN_DATE = Date.parse('yyyy-MM-dd', '1971-01-01')
    final Date MAX_DATE = Date.parse('yyyy-MM-dd', '2525-01-01')

    final int MIN_CHARACTER_RANGE = 32
    final int MAX_CHARACHER_RANGE = 126

    final int MIN_SYMBOL_RANGE = 32
    final int MAX_SYMBOL_RANGE = 47

    final int MIN_UPPER_CHAR_RANGE = 65
    final int MAX_UPPER_CHAR_RANGE = 90
    final int MIN_LOWER_CHAR_RANGE = 97
    final int MAX_LOWER_CHAR_RANGE = 122

    final List<String> UPPER_VOWELS = ['A', 'E', 'I', 'O', 'U']
    final List<String> LOWER_VOWELS = ['a', 'e', 'i', 'o', 'u']
    List<String> VOWELS       = UPPER_VOWELS + LOWER_VOWELS

    final List<String> UPPER_CONSONANTS = [
            'B',
            'C',
            'D',
            'F',
            'G',
            'H',
            'J',
            'K',
            'L',
            'M',
            'N',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'V',
            'W',
            'X',
            'Y',
            'Z'
    ]

    final List<String> LOWER_CONSONANTS = [
            'b',
            'c',
            'd',
            'f',
            'g',
            'h',
            'j',
            'k',
            'l',
            'm',
            'n',
            'p',
            'q',
            'r',
            's',
            't',
            'w',
            'x',
            'y',
            'z'
    ]

    List<String> CONSONANTS = UPPER_CONSONANTS + LOWER_CONSONANTS

    final int MIN_PHONE_NUMBER = 0
    final int MAX_PHONE_NUMBER = 9

    
    /**
     * Helper method that sets up the seed and the Random object
     * @param seed optional seed can be null
     */
    private setUpRandom(Long seed) {
        if (seed == null) {
            seed = System.currentTimeMillis() + Runtime.runtime.freeMemory()
        }
        if (randomNumberGenerator == null) {
            randomNumberGenerator = new Random(seed)
        }
    }

    /**
     * Generates a random int
     * @param seed optional seed value could be null
     * @param min min value that the int generated can be
     * @param max max value that the int generated can be
     * @return the generate int
     */
    int generateRandomInt(int min, int max, Long seed = null) {
        setUpRandom(seed)
        return min + ((max - min) * randomNumberGenerator.nextDouble()) as int
    }

    /**
     * Generates a random double
     * @param seed optional seed value could be null
     * @param min min value that the double generated can be
     * @param max max value that the double generated can be
     * @return the generate double
     */
    double generateRandomDouble(Double min, Double max, Long seed = null) {
        setUpRandom(seed)
        return Math.min(max, randomNumberGenerator.nextDouble() * max + min)
    }

    /**
     * Generates a character base on a random int between min and max
     * http://www.ssec.wisc.edu/~tomw/java/unicode.html
     * @param seed optional seed value could be null
     * @param min the min number the character can be
     * @param max the max number the character can be
     * @return returns the generated character
     */
    char generateCharacter(int min, int max, Long seed = null) {
        return (char) generateRandomInt(min, max, seed)
    }

    /**
     * Generate a random array of bytes
     * @param seed optional seed value could be null
     * @param numberOfBytes the length of the array of bytes to be generated
     * @return the array of generated bytes
     */
    byte[] generateByte(int numberOfBytes, Long seed = null) {
        setUpRandom(seed)
        byte[] bytes = new byte[numberOfBytes]
        randomNumberGenerator.nextBytes(bytes)
        return bytes
    }

    

    /**
     * This method parses the encoded generated command the list below represents
     * the commands currently supported:
     * |L| 	An upper case Letter.
     * |V|	An upper case Vowel.
     * |l| 	A lower case letter.
     * |v|	A lower case vowel.
     * |Q|	A letter (upper or lower).
     * |A| 	A vowel (upper or lower).
     * |C| 	An upper case Consonant.
     * |#|  A long
     * |%|  A Double
     * |c| 	A lower case consonant.
     * |%| 	Any symbol
     * |B|	A consonant (upper or lower)
     * |Ampersand| 
     * |@|  
     * |t| 				time
     * |d,start,end,format| date
     * |p| 	phone number
     * |w,#,delimiter| 	word, number of words separated by delimiter
     * |b,#|bytes number of bytes
     * |s,text| 			sha1 of text
     * |m,text| md5 of text
     * |P,#,delimiter|		paragraph, number of paragraphs separated by delimiter
     *
     * TODO finish/cleanup documentation
     */
    

    String 'L'(){
        return generateCharacter(MIN_UPPER_CHAR_RANGE, MAX_UPPER_CHAR_RANGE)
    }
    
    String 'l'(){
        return generateCharacter(MIN_UPPER_CHAR_RANGE, MAX_UPPER_CHAR_RANGE)
    }
    
    String 'Q'(){
        return generateCharacter(MIN_UPPER_CHAR_RANGE, MAX_UPPER_CHAR_RANGE)
    }
    
    String 'C'(){
        return UPPER_CONSONANTS[generateRandomInt(0, UPPER_CONSONANTS.size())]
    }
    
    String 'c'(){
        return LOWER_CONSONANTS[generateRandomInt(0, LOWER_CONSONANTS.size())];
    }
    
    String 'B'(){
        return CONSONANTS[generateRandomInt(0, CONSONANTS.size())]
    }
    
    String 'V'(){
        return UPPER_VOWELS[generateRandomInt(0, UPPER_VOWELS.size())]
    }
    

    String 'v'(){
        def num = generateRandomInt(0, LOWER_VOWELS.size()-1)
        return LOWER_VOWELS[num]
    }
    
    String 'A'(){
        return VOWELS[generateRandomInt(0, VOWELS.size())]
    }
    
    String 'p'(){
        //I could do something where I put mins on the exchange If I wanted or add an optional country code in the future.
        return "(${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}) " +
                "${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)} -" +
                "${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)}${generateRandomInt(MIN_PHONE_NUMBER, MAX_PHONE_NUMBER)} "
        
    }
    
    String 'd'(Range<Date> range = MIN_DATE..MAX_DATE, String format = 'MM/dd/YYYY HH:mm:ss z'){
        (range.from + new Random().nextInt(range.to - range.from + 1)).format(format)
    }
    
    String 't'(Range<Date> range = MIN_DATE..MAX_DATE,String format = 'HH:mm:ss z'){
        (range.from + new Random().nextInt(range.to - range.from + 1)).format(format)
    }
    
    byte[] 'b'(int numberOfBytes = 1){
        generateByte(numberOfBytes)
    }
    
    String 'I'(int min = Integer.MIN_VALUE, int max = Integer.MAX_VALUE){
        return generateRandomInt(min, max)
    }

    String 'D'(Double min = Double.MIN_VALUE, Double max = Double.MAX_VALUE){
        return generateRandomDouble(min, max)
    }
    
    String 'X'(){
        return generateCharacter(MIN_UPPER_CHAR_RANGE, MAX_UPPER_CHAR_RANGE)
    }
    
    String 'x'(){
        return generateCharacter( MIN_SYMBOL_RANGE, MAX_SYMBOL_RANGE)
    }
    
    String 'w'(int numberOfWords = 1, String delimiter = ' '){
        if(!loremIpsum){
            loremIpsum = new LoremIpsum()
        }
        return loremIpsum.getWords(numberOfWords).replace(" ", delimiter)
    }
    
    String 's'(String message = 'sample'){
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1")
        messageDigest.update(message.getBytes());
        return new BigInteger(1, messageDigest.digest()).toString(16).padLeft(40, '0')
    }
    
    String 'm'(String message = 'sample'){
        MessageDigest messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update(message.getBytes());
        return new BigInteger(1, messageDigest.digest()).toString(16).padLeft(32, '0')
    }
    
    String 'P'(int numberOfParagraphs = 1, String delimiter = " "){
        if(!loremIpsum){
            loremIpsum = new LoremIpsum()
        }
        loremIpsum.getParagraphs(numberOfParagraphs).replace(" ", delimiter)
    }         
}