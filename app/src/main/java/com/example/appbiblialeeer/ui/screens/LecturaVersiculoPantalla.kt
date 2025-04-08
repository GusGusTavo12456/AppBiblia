package com.example.appbiblialeeer.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LecturaVersiculoPantalla(
    referencia: String,
    onVolver: () -> Unit,
    onCompletarLectura: () -> Unit // Se llama solo cuando se llega al final
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("PlanLectura", Context.MODE_PRIVATE)

    val textosMap = mapOf("Mateo 25:1-30" to """
        Parábola de las diez vírgenes
        1 Entonces el reino de los cielos será semejante a diez vírgenes que tomando sus lámparas, salieron a recibir al esposo. 
        2 Cinco de ellas eran prudentes y cinco insensatas. 
        3 Las insensatas, tomando sus lámparas, no tomaron consigo aceite; 
        4 mas las prudentes tomaron aceite en sus vasijas, juntamente con sus lámparas. 
        5 Y tardándose el esposo, cabecearon todas y se durmieron. 
        6 Y a la medianoche se oyó un clamor: ¡Aquí viene el esposo; salid a recibirle! 
        7 Entonces todas aquellas vírgenes se levantaron, y arreglaron sus lámparas. 
        8 Y las insensatas dijeron a las prudentes: Dadnos de vuestro aceite; porque nuestras lámparas se apagan. 
        9 Mas las prudentes respondieron diciendo: Para que no nos falte a nosotras y a vosotras, id más bien a los que venden, y comprad para vosotras mismas. 
        10 Pero mientras ellas iban a comprar, vino el esposo; y las que estaban preparadas entraron con él a las bodas; y se cerró la puerta. 
        11 Después vinieron también las otras vírgenes, diciendo: ¡Señor, señor, ábrenos! 
        12 Mas él, respondiendo, dijo: De cierto os digo, que no os conozco. 
        13 Velad, pues, porque no sabéis el día ni la hora en que el Hijo del Hombre ha de venir.
        Parábola de los talentos
        14 Porque el reino de los cielos es como un hombre que yéndose lejos, llamó a sus siervos y les entregó sus bienes. 
        15 A uno dio cinco talentos, y a otro dos, y a otro uno, a cada uno conforme a su capacidad; y luego se fue lejos. 
        16 Y el que había recibido cinco talentos fue y negoció con ellos, y ganó otros cinco talentos. 
        17 Asimismo el que había recibido dos, ganó también otros dos. 
        18 Pero el que había recibido uno fue y cavó en la tierra, y escondió el dinero de su señor. 
        19 Después de mucho tiempo vino el señor de aquellos siervos, y arregló cuentas con ellos. 
        20 Y llegando el que había recibido cinco talentos, trajo otros cinco talentos, diciendo: Señor, cinco talentos me entregaste; aquí tienes, he ganado otros cinco talentos sobre ellos. 
        21 Y su señor le dijo: Bien, buen siervo y fiel; sobre poco has sido fiel, sobre mucho te pondré; entra en el gozo de tu señor. 
        22 Llegando también el que había recibido dos talentos, dijo: Señor, dos talentos me entregaste; aquí tienes, he ganado otros dos talentos sobre ellos. 
        23 Su señor le dijo: Bien, buen siervo y fiel; sobre poco has sido fiel, sobre mucho te pondré; entra en el gozo de tu señor. 
        24 Pero llegando también el que había recibido un talento, dijo: Señor, te conocía que eres hombre duro, que siegas donde no sembraste y recoges donde no esparciste; 
        25 por lo cual tuve miedo, y fui y escondí tu talento en la tierra; aquí tienes lo que es tuyo. 
        26 Respondiendo su señor, le dijo: Siervo malo y negligente, sabías que siego donde no sembré, y que recojo donde no esparcí. 
        27 Por tanto, debías haber dado mi dinero a los banqueros, y al venir yo, hubiera recibido lo que es mío con los intereses. 
        28 Quitadle, pues, el talento, y dadlo al que tiene diez talentos. 
        29 Porque al que tiene, le será dado, y tendrá más; y al que no tiene, aun lo que tiene le será quitado. 
        30 Y al siervo inútil echadle en las tinieblas de afuera; allí será el lloro y el crujir de dientes.
""".trimIndent(),
        "Números 30-31" to """
        Números 30
        Ley de los votos
        1 Habló Moisés a los príncipes de las tribus de los hijos de Israel, diciendo: Esto es lo que Jehová ha mandado. 
        2 Cuando alguno hiciere voto a Jehová, o hiciere juramento ligando su alma con obligación, no quebrantará su palabra; hará conforme a todo lo que salió de su boca. 
        3 Mas la mujer, cuando hiciere voto a Jehová, y se ligare con obligación en casa de su padre, en su juventud; 
        4 si su padre oyere su voto, y la obligación con que ligó su alma, y su padre callare a ello, todos los votos de ella serán firmes, y toda obligación con que hubiere ligado su alma, firme será. 
        5 Mas si su padre le vedare el día que oyere todos sus votos y sus obligaciones con que ella hubiere ligado su alma, no serán firmes; y Jehová la perdonará, por cuanto su padre se lo vedó. 
        6 Pero si fuere casada e hiciere votos, o pronunciare de sus labios cosa con que obligue su alma; 
        7 si su marido lo oyere, y cuando lo oyere callare a ello, los votos de ella serán firmes, y la obligación con que ligó su alma, firme será. 
        8 Pero si cuando su marido lo oyó, le vedó, entonces el voto que ella hizo, y lo que pronunció de sus labios con que ligó su alma, será nulo; y Jehová la perdonará. 
        9 Pero todo voto de viuda o repudiada, con que ligare su alma, será firme. 
        10 Y si hubiere hecho voto en casa de su marido, y hubiere ligado su alma con obligación de juramento, 
        11 si su marido oyó, y calló a ello y no le vedó, entonces todos sus votos serán firmes, y toda obligación con que hubiere ligado su alma, firme será. 
        12 Mas si su marido los anuló el día que los oyó, todo lo que salió de sus labios cuanto a sus votos, y cuanto a la obligación de su alma, será nulo; su marido los anuló, y Jehová la perdonará. 
        13 Todo voto, y todo juramento obligándose a afligir el alma, su marido lo confirmará, o su marido lo anulará. 
        14 Pero si su marido callare a ello de día en día, entonces confirmó todos sus votos, y todas las obligaciones que están sobre ella; los confirmó, por cuanto calló a ello el día que lo oyó. 
        15 Mas si los anulare después de haberlos oído, entonces él llevará el pecado de ella.
        16 Estas son las ordenanzas que Jehová mandó a Moisés entre el varón y su mujer, y entre el padre y su hija durante su juventud en casa de su padre.
        Números 31
        Venganza de Israel contra Madián
        1 Jehová habló a Moisés, diciendo: 
        2 Haz la venganza de los hijos de Israel contra los madianitas; después serás recogido a tu pueblo. 
        3 Entonces Moisés habló al pueblo, diciendo: Armaos algunos de vosotros para la guerra, y vayan contra Madián y hagan la venganza de Jehová en Madián. 
        4 Mil de cada tribu de todas las tribus de los hijos de Israel, enviaréis a la guerra. 
        5 Así fueron dados de los millares de Israel, mil por cada tribu, doce mil en pie de guerra. 
        6 Y Moisés los envió a la guerra; mil de cada tribu envió; y Finees hijo del sacerdote Eleazar fue a la guerra con los vasos del santuario, y con las trompetas en su mano para tocar. 
        7 Y pelearon contra Madián, como Jehová lo mandó a Moisés, y mataron a todo varón. 
        8 Mataron también, entre los muertos de ellos, a los reyes de Madián, Evi, Requem, Zur, Hur y Reba, cinco reyes de Madián; también a Balaam hijo de Beor mataron a espada. 
        9 Y los hijos de Israel llevaron cautivas a las mujeres de los madianitas, a sus niños, y todas sus bestias y todos sus ganados; y arrebataron todos sus bienes, 
        10 e incendiaron todas sus ciudades, aldeas y habitaciones. 
        11 Y tomaron todo el despojo, y todo el botín, así de hombres como de bestias. 
        12 Y trajeron a Moisés y al sacerdote Eleazar, y a la congregación de los hijos de Israel, los cautivos y el botín y los despojos al campamento, en los llanos de Moab, que están junto al Jordán frente a Jericó.
        13 Y salieron Moisés y el sacerdote Eleazar, y todos los príncipes de la congregación, a recibirlos fuera del campamento. 
        14 Y se enojó Moisés contra los capitanes del ejército, contra los jefes de millares y de centenas que volvían de la guerra, 
        15 y les dijo Moisés: ¿Por qué habéis dejado con vida a todas las mujeres? 
        16 He aquí, por consejo de Balaam ellas fueron causa de que los hijos de Israel prevaricasen contra Jehová en lo tocante a Baal-peor, por lo que hubo mortandad en la congregación de Jehová. 
        17 Matad, pues, ahora a todos los varones de entre los niños; matad también a toda mujer que haya conocido varón carnalmente. 
        18 Pero a todas las niñas entre las mujeres, que no hayan conocido varón, las dejaréis con vida. 
        19 Y vosotros, cualquiera que haya dado muerte a persona, y cualquiera que haya tocado muerto, permaneced fuera del campamento siete días, y os purificaréis al tercer día y al séptimo, vosotros y vuestros cautivos. 
        20 Asimismo purificaréis todo vestido, y toda prenda de pieles, y toda obra de pelo de cabra, y todo utensilio de madera.
        Repartición del botín
        21 Y el sacerdote Eleazar dijo a los hombres de guerra que venían de la guerra: Esta es la ordenanza de la ley que Jehová ha mandado a Moisés: 
        22 Ciertamente el oro y la plata, el bronce, hierro, estaño y plomo, 
        23 todo lo que resiste el fuego, por fuego lo haréis pasar, y será limpio, bien que en las aguas de purificación habrá de purificarse; y haréis pasar por agua todo lo que no resiste el fuego. 
        24 Además lavaréis vuestros vestidos el séptimo día, y así seréis limpios; y después entraréis en el campamento.
        25 Y Jehová habló a Moisés, diciendo: 
        26 Toma la cuenta del botín que se ha hecho, así de las personas como de las bestias, tú y el sacerdote Eleazar, y los jefes de los padres de la congregación; 
        27 y partirás por mitades el botín entre los que pelearon, los que salieron a la guerra, y toda la congregación. 
        28 Y apartarás para Jehová el tributo de los hombres de guerra que salieron a la guerra; de quinientos, uno, así de las personas como de los bueyes, de los asnos y de las ovejas. 
        29 De la mitad de ellos lo tomarás; y darás al sacerdote Eleazar la ofrenda de Jehová. 
        30 Y de la mitad perteneciente a los hijos de Israel tomarás uno de cada cincuenta de las personas, de los bueyes, de los asnos, de las ovejas y de todo animal, y los darás a los levitas, que tienen la guarda del tabernáculo de Jehová. 
        31 E hicieron Moisés y el sacerdote Eleazar como Jehová mandó a Moisés.
        32 Y fue el botín, el resto del botín que tomaron los hombres de guerra, seiscientas setenta y cinco mil ovejas, 
        33 setenta y dos mil bueyes, 
        34 y sesenta y un mil asnos. 
        35 En cuanto a personas, de mujeres que no habían conocido varón, eran por todas treinta y dos mil. 
        36 Y la mitad, la parte de los que habían salido a la guerra, fue el número de trescientas treinta y siete mil quinientas ovejas; 
        37 y el tributo de las ovejas para Jehová fue seiscientas setenta y cinco. 
        38 De los bueyes, treinta y seis mil; y de ellos el tributo para Jehová, setenta y dos. 
        39 De los asnos, treinta mil quinientos; y de ellos el tributo para Jehová, sesenta y uno. 
        40 Y de las personas, dieciséis mil; y de ellas el tributo para Jehová, treinta y dos personas. 
        41 Y dio Moisés el tributo, para ofrenda elevada a Jehová, al sacerdote Eleazar, como Jehová lo mandó a Moisés.
        42 Y de la mitad para los hijos de Israel, que apartó Moisés de los hombres que habían ido a la guerra 
        43 (la mitad para la congregación fue: de las ovejas, trescientas treinta y siete mil quinientas; 
        44 de los bueyes, treinta y seis mil; 
        45 de los asnos, treinta mil quinientos; 
        46 y de las personas, dieciséis mil); 
        47 de la mitad, pues, para los hijos de Israel, tomó Moisés uno de cada cincuenta, así de las personas como de los animales, y los dio a los levitas, que tenían la guarda del tabernáculo de Jehová, como Jehová lo había mandado a Moisés.
        48 Vinieron a Moisés los jefes de los millares de aquel ejército, los jefes de millares y de centenas, 
        49 y dijeron a Moisés: Tus siervos han tomado razón de los hombres de guerra que están en nuestro poder, y ninguno ha faltado de nosotros. 
        50 Por lo cual hemos ofrecido a Jehová ofrenda, cada uno de lo que ha hallado, alhajas de oro, brazaletes, manillas, anillos, zarcillos y cadenas, para hacer expiación por nuestras almas delante de Jehová. 
        51 Y Moisés y el sacerdote Eleazar recibieron el oro de ellos, alhajas, todas elaboradas. 
        52 Y todo el oro de la ofrenda que ofrecieron a Jehová los jefes de millares y de centenas fue dieciséis mil setecientos cincuenta siclos. 
        53 Los hombres del ejército habían tomado botín cada uno para sí. 
        54 Recibieron, pues, Moisés y el sacerdote Eleazar el oro de los jefes de millares y de centenas, y lo trajeron al tabernáculo de reunión, por memoria de los hijos de Israel delante de Jehová.
""".trimIndent(),
        "Job 1" to """
        Las calamidades de Job
        1 Hubo en tierra de Uz un varón llamado Job; y era este hombre perfecto y recto, temeroso de Dios y apartado del mal. 
        2 Y le nacieron siete hijos y tres hijas. 
        3 Su hacienda era siete mil ovejas, tres mil camellos, quinientas yuntas de bueyes, quinientas asnas, y muchísimos criados; y era aquel varón más grande que todos los orientales. 
        4 E iban sus hijos y hacían banquetes en sus casas, cada uno en su día; y enviaban a llamar a sus tres hermanas para que comiesen y bebiesen con ellos. 
        5 Y acontecía que habiendo pasado en turno los días del convite, Job enviaba y los santificaba, y se levantaba de mañana y ofrecía holocaustos conforme al número de todos ellos. Porque decía Job: Quizá habrán pecado mis hijos, y habrán blasfemado contra Dios en sus corazones. De esta manera hacía todos los días.
        6 Un día vinieron a presentarse delante de Jehová los hijos de Dios, entre los cuales vino también Satanás. 
        7 Y dijo Jehová a Satanás: ¿De dónde vienes? Respondiendo Satanás a Jehová, dijo: De rodear la tierra y de andar por ella. 
        8 Y Jehová dijo a Satanás: ¿No has considerado a mi siervo Job, que no hay otro como él en la tierra, varón perfecto y recto, temeroso de Dios y apartado del mal? 
        9 Respondiendo Satanás a Jehová, dijo: ¿Acaso teme Job a Dios de balde? 
        10 ¿No le has cercado alrededor a él y a su casa y a todo lo que tiene? Al trabajo de sus manos has dado bendición; por tanto, sus bienes han aumentado sobre la tierra. 
        11 Pero extiende ahora tu mano y toca todo lo que tiene, y verás si no blasfema contra ti en tu misma presencia. 
        12 Dijo Jehová a Satanás: He aquí, todo lo que tiene está en tu mano; solamente no pongas tu mano sobre él. Y salió Satanás de delante de Jehová.

        13 Y un día aconteció que sus hijos e hijas comían y bebían vino en casa de su hermano el primogénito, 
        14 y vino un mensajero a Job, y le dijo: Estaban arando los bueyes, y las asnas paciendo cerca de ellos, 
        15 y acometieron los sabeos y los tomaron, y mataron a los criados a filo de espada; solamente escapé yo para darte la noticia. 
        16 Aún estaba este hablando, cuando vino otro que dijo: Fuego de Dios cayó del cielo, que quemó las ovejas y a los pastores, y los consumió; solamente escapé yo para darte la noticia. 
        17 Todavía estaba este hablando, y vino otro que dijo: Los caldeos hicieron tres escuadrones, y arremetieron contra los camellos y se los llevaron, y mataron a los criados a filo de espada; y solamente escapé yo para darte la noticia. 
        18 Entre tanto que este hablaba, vino otro que dijo: Tus hijos y tus hijas estaban comiendo y bebiendo vino en casa de su hermano el primogénito; 
        19 y un gran viento vino del lado del desierto y azotó las cuatro esquinas de la casa, la cual cayó sobre los jóvenes, y murieron; y solamente escapé yo para darte la noticia. 
        20 Entonces Job se levantó, y rasgó su manto, y rasuró su cabeza, y se postró en tierra y adoró, 
        21 y dijo: Desnudo salí del vientre de mi madre, y desnudo volveré allá. Jehová dio, y Jehová quitó; sea el nombre de Jehová bendito. 
        22 En todo esto no pecó Job, ni atribuyó a Dios despropósito alguno.
""".trimIndent(),
        "Mateo 25:31-46" to """
        El juicio de las naciones
        31 Cuando el Hijo del Hombre venga en su gloria, y todos los santos ángeles con él, entonces se sentará en su trono de gloria, 
        32 y serán reunidas delante de él todas las naciones; y apartará los unos de los otros, como aparta el pastor las ovejas de los cabritos. 
        33 Y pondrá las ovejas a su derecha, y los cabritos a su izquierda. 
        34 Entonces el Rey dirá a los de su derecha: Venid, benditos de mi Padre, heredad el reino preparado para vosotros desde la fundación del mundo. 
        35 Porque tuve hambre, y me disteis de comer; tuve sed, y me disteis de beber; fui forastero, y me recogisteis; 
        36 estuve desnudo, y me cubristeis; enfermo, y me visitasteis; en la cárcel, y vinisteis a mí. 
        37 Entonces los justos le responderán diciendo: Señor, ¿cuándo te vimos hambriento, y te sustentamos, o sediento, y te dimos de beber? 
        38 ¿Y cuándo te vimos forastero, y te recogimos, o desnudo, y te cubrimos? 
        39 ¿O cuándo te vimos enfermo, o en la cárcel, y vinimos a ti? 
        40 Y respondiendo el Rey, les dirá: De cierto os digo que en cuanto lo hicisteis a uno de estos mis hermanos más pequeños, a mí lo hicisteis. 
        41 Entonces dirá también a los de la izquierda: Apartaos de mí, malditos, al fuego eterno preparado para el diablo y sus ángeles. 
        42 Porque tuve hambre, y no me disteis de comer; tuve sed, y no me disteis de beber; 
        43 fui forastero, y no me recogisteis; estuve desnudo, y no me cubristeis; enfermo, y en la cárcel, y no me visitasteis. 
        44 Entonces también ellos le responderán diciendo: Señor, ¿cuándo te vimos hambriento, sediento, forastero, desnudo, enfermo, o en la cárcel, y no te servimos? 
        45 Entonces les responderá diciendo: De cierto os digo que en cuanto no lo hicisteis a uno de estos más pequeños, tampoco a mí lo hicisteis. 
        46 E irán estos al castigo eterno, y los justos a la vida eterna.
""".trimIndent(),
        "Números 32-34" to """
        Números 32
        Rubén y Gad se establecen al oriente del Jordán
        1 Los hijos de Rubén y los hijos de Gad tenían una muy inmensa muchedumbre de ganado; y vieron la tierra de Jazer y de Galaad, y les pareció el país lugar de ganado. 
        2 Vinieron, pues, los hijos de Gad y los hijos de Rubén, y hablaron a Moisés y al sacerdote Eleazar, y a los príncipes de la congregación, diciendo: 
        3 Atarot, Dibón, Jazer, Nimra, Hesbón, Eleale, Sebam, Nebo y Beón, 
        4 la tierra que Jehová hirió delante de la congregación de Israel, es tierra de ganado, y tus siervos tienen ganado. 
        5 Por tanto, dijeron, si hallamos gracia en tus ojos, dése esta tierra a tus siervos en heredad, y no nos hagas pasar el Jordán.
        6 Y respondió Moisés a los hijos de Gad y a los hijos de Rubén: ¿Irán vuestros hermanos a la guerra, y vosotros os quedaréis aquí? 
        7 ¿Y por qué desanimáis a los hijos de Israel, para que no pasen a la tierra que les ha dado Jehová? 
        8 Así hicieron vuestros padres, cuando los envié desde Cades-barnea para que viesen la tierra. 
        9 Subieron hasta el torrente de Escol, y después que vieron la tierra, desalentaron a los hijos de Israel para que no viniesen a la tierra que Jehová les había dado. 
        10 Y la ira de Jehová se encendió entonces, y juró diciendo: 
        11 No verán los varones que subieron de Egipto de veinte años arriba, la tierra que prometí con juramento a Abraham, Isaac y Jacob, por cuanto no fueron perfectos en pos de mí; 
        12 excepto Caleb hijo de Jefone cenezeo, y Josué hijo de Nun, que fueron perfectos en pos de Jehová. 
        13 Y la ira de Jehová se encendió contra Israel, y los hizo andar errantes cuarenta años por el desierto, hasta que fue acabada toda aquella generación que había hecho mal delante de Jehová. 
        14 Y he aquí, vosotros habéis sucedido en lugar de vuestros padres, prole de hombres pecadores, para añadir aún a la ira de Jehová contra Israel. 
        15 Si os volviereis de en pos de él, él volverá otra vez a dejaros en el desierto, y destruiréis a todo este pueblo.
        16 Entonces ellos vinieron a Moisés y dijeron: Edificaremos aquí majadas para nuestro ganado, y ciudades para nuestros niños; 
        17 y nosotros nos armaremos, e iremos con diligencia delante de los hijos de Israel, hasta que los metamos en su lugar; y nuestros niños quedarán en ciudades fortificadas a causa de los moradores del país. 
        18 No volveremos a nuestras casas hasta que los hijos de Israel posean cada uno su heredad. 
        19 Porque no tomaremos heredad con ellos al otro lado del Jordán ni adelante, por cuanto tendremos ya nuestra heredad a este otro lado del Jordán al oriente. 
        20 Entonces les respondió Moisés: Si lo hacéis así, si os disponéis para ir delante de Jehová a la guerra, 
        21 y todos vosotros pasáis armados el Jordán delante de Jehová, hasta que haya echado a sus enemigos de delante de sí, 
        22 y sea el país sojuzgado delante de Jehová; luego volveréis, y seréis libres de culpa para con Jehová, y para con Israel; y esta tierra será vuestra en heredad delante de Jehová. 
        23 Mas si así no lo hacéis, he aquí habréis pecado ante Jehová; y sabed que vuestro pecado os alcanzará. 
        24 Edificaos ciudades para vuestros niños, y majadas para vuestras ovejas, y haced lo que ha declarado vuestra boca. 
        25 Y hablaron los hijos de Gad y los hijos de Rubén a Moisés, diciendo: Tus siervos harán como mi señor ha mandado. 
        26 Nuestros niños, nuestras mujeres, nuestros ganados y todas nuestras bestias, estarán ahí en las ciudades de Galaad; 
        27 y tus siervos, armados todos para la guerra, pasarán delante de Jehová a la guerra, de la manera que mi señor dice.
        28 Entonces les encomendó Moisés al sacerdote Eleazar, y a Josué hijo de Nun, y a los príncipes de los padres de las tribus de los hijos de Israel. 
        29 Y les dijo Moisés: Si los hijos de Gad y los hijos de Rubén pasan con vosotros el Jordán, armados todos para la guerra delante de Jehová, luego que el país sea sojuzgado delante de vosotros, les daréis la tierra de Galaad en posesión; 
        30 mas si no pasan armados con vosotros, entonces tendrán posesión entre vosotros, en la tierra de Canaán. 
        31 Y los hijos de Gad y los hijos de Rubén respondieron diciendo: Haremos lo que Jehová ha dicho a tus siervos. 
        32 Nosotros pasaremos armados delante de Jehová a la tierra de Canaán, y la posesión de nuestra heredad será a este lado del Jordán.
        33 Así Moisés dio a los hijos de Gad, a los hijos de Rubén, y a la media tribu de Manasés hijo de José, el reino de Sehón rey amorreo y el reino de Og rey de Basán, la tierra con sus ciudades y sus territorios, las ciudades del país alrededor. 
        34 Y los hijos de Gad edificaron Dibón, Atarot, Aroer, 
        35 Atarot-sofán, Jazer, Jogbeha, 
        36 Bet-nimra y Bet-arán, ciudades fortificadas; hicieron también majadas para ovejas. 
        37 Y los hijos de Rubén edificaron Hesbón, Eleale, Quiriataim, 
        38 Nebo, Baal-meón (mudados los nombres) y Sibma; y pusieron nombres a las ciudades que edificaron. 
        39 Y los hijos de Maquir hijo de Manasés fueron a Galaad, y la tomaron, y echaron al amorreo que estaba en ella. 
        40 Y Moisés dio Galaad a Maquir hijo de Manasés, el cual habitó en ella. 
        41 También Jair hijo de Manasés fue y tomó sus aldeas, y les puso por nombre Havot-jair.[a] 
        42 Asimismo Noba fue y tomó Kenat y sus aldeas, y lo llamó Noba, conforme a su nombre.
        Números 33
        Jornadas de Israel desde Egipto hasta el Jordán
        1 Estas son las jornadas de los hijos de Israel, que salieron de la tierra de Egipto por sus ejércitos, bajo el mando de Moisés y Aarón. 
        2 Moisés escribió sus salidas conforme a sus jornadas por mandato de Jehová. Estas, pues, son sus jornadas con arreglo a sus salidas. 
        3 De Ramesés salieron en el mes primero, a los quince días del mes primero; el segundo día de la pascua salieron los hijos de Israel con mano poderosa, a vista de todos los egipcios, 
        4 mientras enterraban los egipcios a los que Jehová había herido de muerte de entre ellos, a todo primogénito; también había hecho Jehová juicios contra sus dioses.
        5 Salieron, pues, los hijos de Israel de Ramesés, y acamparon en Sucot. 
        6 Salieron de Sucot y acamparon en Etam, que está al confín del desierto. 
        7 Salieron de Etam y volvieron sobre Pi-hahirot, que está delante de Baal-zefón, y acamparon delante de Migdol. 
        8 Salieron de Pi-hahirot y pasaron por en medio del mar al desierto, y anduvieron tres días de camino por el desierto de Etam, y acamparon en Mara. 
        9 Salieron de Mara y vinieron a Elim, donde había doce fuentes de aguas, y setenta palmeras; y acamparon allí. 
        10 Salieron de Elim y acamparon junto al Mar Rojo. 
        11 Salieron del Mar Rojo y acamparon en el desierto de Sin. 
        12 Salieron del desierto de Sin y acamparon en Dofca. 
        13 Salieron de Dofca y acamparon en Alús. 
        14 Salieron de Alús y acamparon en Refidim, donde el pueblo no tuvo aguas para beber. 
        15 Salieron de Refidim y acamparon en el desierto de Sinaí. 
        16 Salieron del desierto de Sinaí y acamparon en Kibrot-hataava. 
        17 Salieron de Kibrot-hataava y acamparon en Hazerot. 
        18 Salieron de Hazerot y acamparon en Ritma. 
        19 Salieron de Ritma y acamparon en Rimón-peres. 
        20 Salieron de Rimón-peres y acamparon en Libna. 
        21 Salieron de Libna y acamparon en Rissa. 
        22 Salieron de Rissa y acamparon en Ceelata. 
        23 Salieron de Ceelata y acamparon en el monte de Sefer. 
        24 Salieron del monte de Sefer y acamparon en Harada. 
        25 Salieron de Harada y acamparon en Macelot. 
        26 Salieron de Macelot y acamparon en Tahat. 
        27 Salieron de Tahat y acamparon en Tara. 
        28 Salieron de Tara y acamparon en Mitca. 
        29 Salieron de Mitca y acamparon en Hasmona. 
        30 Salieron de Hasmona y acamparon en Moserot. 
        31 Salieron de Moserot y acamparon en Bene-jaacán. 
        32 Salieron de Bene-jaacán y acamparon en el monte de Gidgad. 
        33 Salieron del monte de Gidgad y acamparon en Jotbata. 
        34 Salieron de Jotbata y acamparon en Abrona. 
        35 Salieron de Abrona y acamparon en Ezión-geber. 
        36 Salieron de Ezión-geber y acamparon en el desierto de Zin, que es Cades. 
        37 Y salieron de Cades y acamparon en el monte de Hor, en la extremidad del país de Edom.
        38 Y subió el sacerdote Aarón al monte de Hor, conforme al dicho de Jehová, y allí murió a los cuarenta años de la salida de los hijos de Israel de la tierra de Egipto, en el mes quinto, en el primero del mes. 
        39 Era Aarón de edad de ciento veintitrés años, cuando murió en el monte de Hor.
        40 Y el cananeo, rey de Arad, que habitaba en el Neguev en la tierra de Canaán, oyó que habían venido los hijos de Israel.
        41 Y salieron del monte de Hor y acamparon en Zalmona. 
        42 Salieron de Zalmona y acamparon en Punón. 
        43 Salieron de Punón y acamparon en Obot. 
        44 Salieron de Obot y acamparon en Ije-abarim, en la frontera de Moab. 
        45 Salieron de Ije-abarim y acamparon en Dibón-gad. 
        46 Salieron de Dibón-gad y acamparon en Almón-diblataim. 
        47 Salieron de Almón-diblataim y acamparon en los montes de Abarim, delante de Nebo. 
        48 Salieron de los montes de Abarim y acamparon en los campos de Moab, junto al Jordán, frente a Jericó. 
        49 Finalmente acamparon junto al Jordán, desde Bet-jesimot hasta Abel-sitim, en los campos de Moab.
        Límites y repartición de Canaán
        50 Y habló Jehová a Moisés en los campos de Moab junto al Jordán frente a Jericó, diciendo: 
        51 Habla a los hijos de Israel, y diles: Cuando hayáis pasado el Jordán entrando en la tierra de Canaán, 
        52 echaréis de delante de vosotros a todos los moradores del país, y destruiréis todos sus ídolos de piedra, y todas sus imágenes de fundición, y destruiréis todos sus lugares altos; 
        53 y echaréis a los moradores de la tierra, y habitaréis en ella; porque yo os la he dado para que sea vuestra propiedad. 
        54 Y heredaréis la tierra por sorteo por vuestras familias; a los muchos daréis mucho por herencia, y a los pocos daréis menos por herencia; donde le cayere la suerte, allí la tendrá cada uno; por las tribus de vuestros padres heredaréis. 
        55 Y si no echareis a los moradores del país de delante de vosotros, sucederá que los que dejareis de ellos serán por aguijones en vuestros ojos y por espinas en vuestros costados, y os afligirán sobre la tierra en que vosotros habitareis. 
        56 Además, haré a vosotros como yo pensé hacerles a ellos.
        Números 34
        1 Y Jehová habló a Moisés, diciendo: 
        2 Manda a los hijos de Israel y diles: Cuando hayáis entrado en la tierra de Canaán, esto es, la tierra que os ha de caer en herencia, la tierra de Canaán según sus límites, 
        3 tendréis el lado del sur desde el desierto de Zin hasta la frontera de Edom; y será el límite del sur al extremo del Mar Salado hacia el oriente. 
        4 Este límite os irá rodeando desde el sur hasta la subida de Acrabim, y pasará hasta Zin; y se extenderá del sur a Cades-barnea; y continuará a Hasar-adar, y pasará hasta Asmón. 
        5 Rodeará este límite desde Asmón hasta el torrente de Egipto, y sus remates serán al occidente.
        6 Y el límite occidental será el Mar Grande; este límite será el límite occidental.
        7 El límite del norte será este: desde el Mar Grande trazaréis al monte de Hor. 
        8 Del monte de Hor trazaréis a la entrada de Hamat, y seguirá aquel límite hasta Zedad; 
        9 y seguirá este límite hasta Zifrón, y terminará en Hazar-enán; este será el límite del norte.
        10 Por límite al oriente trazaréis desde Hazar-enán hasta Sefam; 
        11 y bajará este límite desde Sefam a Ribla, al oriente de Aín; y descenderá el límite, y llegará a la costa del mar de Cineret, al oriente. 
        12 Después descenderá este límite al Jordán, y terminará en el Mar Salado: esta será vuestra tierra por sus límites alrededor.
        13 Y mandó Moisés a los hijos de Israel, diciendo: Esta es la tierra que se os repartirá en heredades por sorteo, que mandó Jehová que diese a las nueve tribus, y a la media tribu; 
        14 porque la tribu de los hijos de Rubén según las casas de sus padres, y la tribu de los hijos de Gad según las casas de sus padres, y la media tribu de Manasés, han tomado su heredad. 
        15 Dos tribus y media tomaron su heredad a este lado del Jordán frente a Jericó al oriente, al nacimiento del sol.
        16 Y habló Jehová a Moisés, diciendo: 
        17 Estos son los nombres de los varones que os repartirán la tierra: El sacerdote Eleazar, y Josué hijo de Nun. 
        18 Tomaréis también de cada tribu un príncipe, para dar la posesión de la tierra. 
        19 Y estos son los nombres de los varones: De la tribu de Judá, Caleb hijo de Jefone. 
        20 De la tribu de los hijos de Simeón, Semuel hijo de Amiud. 
        21 De la tribu de Benjamín, Elidad hijo de Quislón. 
        22 De la tribu de los hijos de Dan, el príncipe Buqui hijo de Jogli. 
        23 De los hijos de José: de la tribu de los hijos de Manasés, el príncipe Haniel hijo de Efod, 
        24 y de la tribu de los hijos de Efraín, el príncipe Kemuel hijo de Siftán. 
        25 De la tribu de los hijos de Zabulón, el príncipe Elizafán hijo de Parnac. 
        26 De la tribu de los hijos de Isacar, el príncipe Paltiel hijo de Azán. 
        27 De la tribu de los hijos de Aser, el príncipe Ahiud hijo de Selomi. 
        28 Y de la tribu de los hijos de Neftalí, el príncipe Pedael hijo de Amiud. 
        29 A estos mandó Jehová que hiciesen la repartición de las heredades a los hijos de Israel en la tierra de Canaán.
""".trimIndent(),
        "Job 2" to """
        1 Aconteció que otro día vinieron los hijos de Dios para presentarse delante de Jehová, y Satanás vino también entre ellos presentándose delante de Jehová. 
        2 Y dijo Jehová a Satanás: ¿De dónde vienes? Respondió Satanás a Jehová, y dijo: De rodear la tierra, y de andar por ella. 
        3 Y Jehová dijo a Satanás: ¿No has considerado a mi siervo Job, que no hay otro como él en la tierra, varón perfecto y recto, temeroso de Dios y apartado del mal, y que todavía retiene su integridad, aun cuando tú me incitaste contra él para que lo arruinara sin causa? 
        4 Respondiendo Satanás, dijo a Jehová: Piel por piel, todo lo que el hombre tiene dará por su vida. 
        5 Pero extiende ahora tu mano, y toca su hueso y su carne, y verás si no blasfema contra ti en tu misma presencia. 
        6 Y Jehová dijo a Satanás: He aquí, él está en tu mano; mas guarda su vida.
        7 Entonces salió Satanás de la presencia de Jehová, e hirió a Job con una sarna maligna desde la planta del pie hasta la coronilla de la cabeza. 
        8 Y tomaba Job un tiesto para rascarse con él, y estaba sentado en medio de ceniza.
        9 Entonces le dijo su mujer: ¿Aún retienes tu integridad? Maldice a Dios, y muérete. 
        10 Y él le dijo: Como suele hablar cualquiera de las mujeres fatuas, has hablado. ¿Qué? ¿Recibiremos de Dios el bien, y el mal no lo recibiremos? En todo esto no pecó Job con sus labios.
        11 Y tres amigos de Job, Elifaz temanita, Bildad suhita, y Zofar naamatita, luego que oyeron todo este mal que le había sobrevenido, vinieron cada uno de su lugar; porque habían convenido en venir juntos para condolerse de él y para consolarle. 
        12 Los cuales, alzando los ojos desde lejos, no lo conocieron, y lloraron a gritos; y cada uno de ellos rasgó su manto, y los tres esparcieron polvo sobre sus cabezas hacia el cielo. 
        13 Así se sentaron con él en tierra por siete días y siete noches, y ninguno le hablaba palabra, porque veían que su dolor era muy grande.
""".trimIndent(),
        "Mateo 26:1-25" to """
        El complot para prender a Jesús
        1 Cuando hubo acabado Jesús todas estas palabras, dijo a sus discípulos: 
        2 Sabéis que dentro de dos días se celebra la pascua, y el Hijo del Hombre será entregado para ser crucificado.
        3 Entonces los principales sacerdotes, los escribas, y los ancianos del pueblo se reunieron en el patio del sumo sacerdote llamado Caifás, 
        4 y tuvieron consejo para prender con engaño a Jesús, y matarle. 
        5 Pero decían: No durante la fiesta, para que no se haga alboroto en el pueblo.
        Jesús es ungido en Betania
        6 Y estando Jesús en Betania, en casa de Simón el leproso, 
        7 vino a él una mujer, con un vaso de alabastro de perfume de gran precio, y lo derramó sobre la cabeza de él, estando sentado a la mesa. 
        8 Al ver esto, los discípulos se enojaron, diciendo: ¿Para qué este desperdicio? 
        9 Porque esto podía haberse vendido a gran precio, y haberse dado a los pobres. 
        10 Y entendiéndolo Jesús, les dijo: ¿Por qué molestáis a esta mujer? pues ha hecho conmigo una buena obra. 
        11 Porque siempre tendréis pobres con vosotros, pero a mí no siempre me tendréis. 
        12 Porque al derramar este perfume sobre mi cuerpo, lo ha hecho a fin de prepararme para la sepultura. 
        13 De cierto os digo que dondequiera que se predique este evangelio, en todo el mundo, también se contará lo que esta ha hecho, para memoria de ella.
        Judas ofrece entregar a Jesús
        14 Entonces uno de los doce, que se llamaba Judas Iscariote, fue a los principales sacerdotes, 
        15 y les dijo: ¿Qué me queréis dar, y yo os lo entregaré? Y ellos le asignaron treinta piezas de plata. 
        16 Y desde entonces buscaba oportunidad para entregarle.
        Institución de la Cena del Señor
        17 El primer día de la fiesta de los panes sin levadura, vinieron los discípulos a Jesús, diciéndole: ¿Dónde quieres que preparemos para que comas la pascua? 
        18 Y él dijo: Id a la ciudad a cierto hombre, y decidle: El Maestro dice: Mi tiempo está cerca; en tu casa celebraré la pascua con mis discípulos. 
        19 Y los discípulos hicieron como Jesús les mandó, y prepararon la pascua.
        20 Cuando llegó la noche, se sentó a la mesa con los doce. 
        21 Y mientras comían, dijo: De cierto os digo, que uno de vosotros me va a entregar. 
        22 Y entristecidos en gran manera, comenzó cada uno de ellos a decirle: ¿Soy yo, Señor? 
        23 Entonces él respondiendo, dijo: El que mete la mano conmigo en el plato, ese me va a entregar. 
        24 A la verdad el Hijo del Hombre va, según está escrito de él, mas ¡ay de aquel hombre por quien el Hijo del Hombre es entregado! Bueno le fuera a ese hombre no haber nacido. 
        25 Entonces respondiendo Judas, el que le entregaba, dijo: ¿Soy yo, Maestro? Le dijo: Tú lo has dicho.
""".trimIndent(),
        "Números 35-36" to """
        Números 35
        Herencia de los levitas
        1 Habló Jehová a Moisés en los campos de Moab, junto al Jordán frente a Jericó, diciendo: 
        2 Manda a los hijos de Israel que den a los levitas, de la posesión de su heredad, ciudades en que habiten; también daréis a los levitas los ejidos de esas ciudades alrededor de ellas. 
        3 Y tendrán ellos las ciudades para habitar, y los ejidos de ellas serán para sus animales, para sus ganados y para todas sus bestias. 
        4 Y los ejidos de las ciudades que daréis a los levitas serán mil codos alrededor, desde el muro de la ciudad para afuera. 
        5 Luego mediréis fuera de la ciudad al lado del oriente dos mil codos, al lado del sur dos mil codos, al lado del occidente dos mil codos, y al lado del norte dos mil codos, y la ciudad estará en medio; esto tendrán por los ejidos de las ciudades. 
        6 Y de las ciudades que daréis a los levitas, seis ciudades serán de refugio, las cuales daréis para que el homicida se refugie allá; y además de estas daréis cuarenta y dos ciudades. 
        7 Todas las ciudades que daréis a los levitas serán cuarenta y ocho ciudades con sus ejidos. 
        8 Y en cuanto a las ciudades que diereis de la heredad de los hijos de Israel, del que tiene mucho tomaréis mucho, y del que tiene poco tomaréis poco; cada uno dará de sus ciudades a los levitas según la posesión que heredará.
        Ciudades de refugio
        9 Habló Jehová a Moisés, diciendo: 
        10 Habla a los hijos de Israel, y diles: Cuando hayáis pasado al otro lado del Jordán a la tierra de Canaán, 
        11 os señalaréis ciudades, ciudades de refugio tendréis, donde huya el homicida que hiriere a alguno de muerte sin intención. 
        12 Y os serán aquellas ciudades para refugiarse del vengador, y no morirá el homicida hasta que entre en juicio delante de la congregación. 
        13 De las ciudades, pues, que daréis, tendréis seis ciudades de refugio. 
        14 Tres ciudades daréis a este lado del Jordán, y tres ciudades daréis en la tierra de Canaán, las cuales serán ciudades de refugio. 
        15 Estas seis ciudades serán de refugio para los hijos de Israel, y para el extranjero y el que more entre ellos, para que huya allá cualquiera que hiriere de muerte a otro sin intención.
        16 Si con instrumento de hierro lo hiriere y muriere, homicida es; el homicida morirá. 
        17 Y si con piedra en la mano, que pueda dar muerte, lo hiriere y muriere, homicida es; el homicida morirá. 
        18 Y si con instrumento de palo en la mano, que pueda dar muerte, lo hiriere y muriere, homicida es; el homicida morirá. 
        19 El vengador de la sangre, él dará muerte al homicida; cuando lo encontrare, él lo matará. 
        20 Y si por odio lo empujó, o echó sobre él alguna cosa por asechanzas, y muere; 
        21 o por enemistad lo hirió con su mano, y murió, el heridor morirá; es homicida; el vengador de la sangre matará al homicida cuando lo encontrare.
        22 Mas si casualmente lo empujó sin enemistades, o echó sobre él cualquier instrumento sin asechanzas, 
        23 o bien, sin verlo hizo caer sobre él alguna piedra que pudo matarlo, y muriere, y él no era su enemigo, ni procuraba su mal; 
        24 entonces la congregación juzgará entre el que causó la muerte y el vengador de la sangre conforme a estas leyes; 
        25 y la congregación librará al homicida de mano del vengador de la sangre, y la congregación lo hará volver a su ciudad de refugio, en la cual se había refugiado; y morará en ella hasta que muera el sumo sacerdote, el cual fue ungido con el aceite santo. 
        26 Mas si el homicida saliere fuera de los límites de su ciudad de refugio, en la cual se refugió, 
        27 y el vengador de la sangre le hallare fuera del límite de la ciudad de su refugio, y el vengador de la sangre matare al homicida, no se le culpará por ello; 
        28 pues en su ciudad de refugio deberá aquel habitar hasta que muera el sumo sacerdote; y después que haya muerto el sumo sacerdote, el homicida volverá a la tierra de su posesión.
        Ley sobre los testigos y sobre el rescate
        29 Estas cosas os serán por ordenanza de derecho por vuestras edades, en todas vuestras habitaciones. 
        30 Cualquiera que diere muerte a alguno, por dicho de testigos morirá el homicida; mas un solo testigo no hará fe contra una persona para que muera. 
        31 Y no tomaréis precio por la vida del homicida, porque está condenado a muerte; indefectiblemente morirá. 
        32 Ni tampoco tomaréis precio del que huyó a su ciudad de refugio, para que vuelva a vivir en su tierra, hasta que muera el sumo sacerdote. 
        33 Y no contaminaréis la tierra donde estuviereis; porque esta sangre amancillará la tierra, y la tierra no será expiada de la sangre que fue derramada en ella, sino por la sangre del que la derramó. 
        34 No contaminéis, pues, la tierra donde habitáis, en medio de la cual yo habito; porque yo Jehová habito en medio de los hijos de Israel.
        Números 36
        Ley del casamiento de las herederas
        1 Llegaron los príncipes de los padres de la familia de Galaad hijo de Maquir, hijo de Manasés, de las familias de los hijos de José; y hablaron delante de Moisés y de los príncipes, jefes de las casas paternas de los hijos de Israel, 
        2 y dijeron: Jehová mandó a mi señor que por sorteo diese la tierra a los hijos de Israel en posesión; también ha mandado Jehová a mi señor, que dé la posesión de Zelofehad nuestro hermano a sus hijas. 
        3 Y si ellas se casaren con algunos de los hijos de las otras tribus de los hijos de Israel, la herencia de ellas será así quitada de la herencia de nuestros padres, y será añadida a la herencia de la tribu a que se unan; y será quitada de la porción de nuestra heredad. 
        4 Y cuando viniere el jubileo de los hijos de Israel, la heredad de ellas será añadida a la heredad de la tribu de sus maridos; así la heredad de ellas será quitada de la heredad de la tribu de nuestros padres.
        5 Entonces Moisés mandó a los hijos de Israel por mandato de Jehová, diciendo: La tribu de los hijos de José habla rectamente. 
        6 Esto es lo que ha mandado Jehová acerca de las hijas de Zelofehad, diciendo: Cásense como a ellas les plazca, pero en la familia de la tribu de su padre se casarán, 
        7 para que la heredad de los hijos de Israel no sea traspasada de tribu en tribu; porque cada uno de los hijos de Israel estará ligado a la heredad de la tribu de sus padres. 
        8 Y cualquiera hija que tenga heredad en las tribus de los hijos de Israel, con alguno de la familia de la tribu de su padre se casará, para que los hijos de Israel posean cada uno la heredad de sus padres, 
        9 y no ande la heredad rodando de una tribu a otra, sino que cada una de las tribus de los hijos de Israel estará ligada a su heredad.
        10 Como Jehová mandó a Moisés, así hicieron las hijas de Zelofehad. 
        11 Y así Maala, Tirsa, Hogla, Milca y Noa, hijas de Zelofehad, se casaron con hijos de sus tíos paternos. 
        12 Se casaron en la familia de los hijos de Manasés, hijo de José; y la heredad de ellas quedó en la tribu de la familia de su padre.
        13 Estos son los mandamientos y los estatutos que mandó Jehová por medio de Moisés a los hijos de Israel en los campos de Moab, junto al Jordán, frente a Jericó.
""".trimIndent(),
        "Job 3" to """
        Job maldice el día en que nació
        3 Después de esto abrió Job su boca, y maldijo su día. 2 Y exclamó Job, y dijo:
        3 Perezca el día en que yo nací,
        Y la noche en que se dijo: Varón es concebido.
        4 Sea aquel día sombrío,
        Y no cuide de él Dios desde arriba,
        Ni claridad sobre él resplandezca.
        5 Aféenlo tinieblas y sombra de muerte;
        Repose sobre él nublado
        Que lo haga horrible como día caliginoso.
        6 Ocupe aquella noche la oscuridad;
        No sea contada entre los días del año,
        Ni venga en el número de los meses.
        7 ¡Oh, que fuera aquella noche solitaria,
        Que no viniera canción alguna en ella!
        8 Maldíganla los que maldicen el día,
        Los que se aprestan para despertar a Leviatán.
        9 Oscurézcanse las estrellas de su alba;
        Espere la luz, y no venga,
        Ni vea los párpados de la mañana;
        10 Por cuanto no cerró las puertas del vientre donde yo estaba,
        Ni escondió de mis ojos la miseria.
        11 ¿Por qué no morí yo en la matriz,
        O expiré al salir del vientre?
        12 ¿Por qué me recibieron las rodillas?
        ¿Y a qué los pechos para que mamase?
        13 Pues ahora estaría yo muerto, y reposaría;
        Dormiría, y entonces tendría descanso,
        14 Con los reyes y con los consejeros de la tierra,
        Que reedifican para sí ruinas;
        15 O con los príncipes que poseían el oro,
        Que llenaban de plata sus casas.
        16 ¿Por qué no fui escondido como abortivo,
        Como los pequeñitos que nunca vieron la luz?
        17 Allí los impíos dejan de perturbar,
        Y allí descansan los de agotadas fuerzas.
        18 Allí también reposan los cautivos;
        No oyen la voz del capataz.
        19 Allí están el chico y el grande,
        Y el siervo libre de su señor.
        20 ¿Por qué se da luz al trabajado,
        Y vida a los de ánimo amargado,
        21 Que esperan la muerte, y ella no llega,
        Aunque la buscan más que tesoros;
        22 Que se alegran sobremanera,
        Y se gozan cuando hallan el sepulcro?
        23 ¿Por qué se da vida al hombre que no sabe por dónde ha de ir,
        Y a quien Dios ha encerrado?
        24 Pues antes que mi pan viene mi suspiro,
        Y mis gemidos corren como aguas.
        25 Porque el temor que me espantaba me ha venido,
        Y me ha acontecido lo que yo temía.
        26 No he tenido paz, no me aseguré, ni estuve reposado;
        No obstante, me vino turbación.
""".trimIndent(),
        "Mateo 26:26-46" to """
        26 Y mientras comían, tomó Jesús el pan, y bendijo, y lo partió, y dio a sus discípulos, y dijo: Tomad, comed; esto es mi cuerpo. 
        27 Y tomando la copa, y habiendo dado gracias, les dio, diciendo: Bebed de ella todos; 
        28 porque esto es mi sangre del nuevo pacto, que por muchos es derramada para remisión de los pecados. 
        29 Y os digo que desde ahora no beberé más de este fruto de la vid, hasta aquel día en que lo beba nuevo con vosotros en el reino de mi Padre.
        Jesús anuncia la negación de Pedro
        30 Y cuando hubieron cantado el himno, salieron al monte de los Olivos. 
        31 Entonces Jesús les dijo: Todos vosotros os escandalizaréis de mí esta noche; porque escrito está: Heriré al pastor, y las ovejas del rebaño serán dispersadas. 
        32 Pero después que haya resucitado, iré delante de vosotros a Galilea. 
        33 Respondiendo Pedro, le dijo: Aunque todos se escandalicen de ti, yo nunca me escandalizaré. 
        34 Jesús le dijo: De cierto te digo que esta noche, antes que el gallo cante, me negarás tres veces. 
        35 Pedro le dijo: Aunque me sea necesario morir contigo, no te negaré. Y todos los discípulos dijeron lo mismo.
        Jesús ora en Getsemaní
        36 Entonces llegó Jesús con ellos a un lugar que se llama Getsemaní, y dijo a sus discípulos: Sentaos aquí, entre tanto que voy allí y oro. 
        37 Y tomando a Pedro, y a los dos hijos de Zebedeo, comenzó a entristecerse y a angustiarse en gran manera. 
        38 Entonces Jesús les dijo: Mi alma está muy triste, hasta la muerte; quedaos aquí, y velad conmigo. 
        39 Yendo un poco adelante, se postró sobre su rostro, orando y diciendo: Padre mío, si es posible, pase de mí esta copa; pero no sea como yo quiero, sino como tú. 
        40 Vino luego a sus discípulos, y los halló durmiendo, y dijo a Pedro: ¿Así que no habéis podido velar conmigo una hora? 
        41 Velad y orad, para que no entréis en tentación; el espíritu a la verdad está dispuesto, pero la carne es débil. 
        42 Otra vez fue, y oró por segunda vez, diciendo: Padre mío, si no puede pasar de mí esta copa sin que yo la beba, hágase tu voluntad. 
        43 Vino otra vez y los halló durmiendo, porque los ojos de ellos estaban cargados de sueño. 
        44 Y dejándolos, se fue de nuevo, y oró por tercera vez, diciendo las mismas palabras. 
        45 Entonces vino a sus discípulos y les dijo: Dormid ya, y descansad. He aquí ha llegado la hora, y el Hijo del Hombre es entregado en manos de pecadores. 
        46 Levantaos, vamos; ved, se acerca el que me entrega.
""".trimIndent(),
        "Mateo 26:26-46" to """
        26 Y mientras comían, tomó Jesús el pan, y bendijo, y lo partió, y dio a sus discípulos, y dijo: Tomad, comed; esto es mi cuerpo. 
        27 Y tomando la copa, y habiendo dado gracias, les dio, diciendo: Bebed de ella todos; 
        28 porque esto es mi sangre del nuevo pacto, que por muchos es derramada para remisión de los pecados. 
        29 Y os digo que desde ahora no beberé más de este fruto de la vid, hasta aquel día en que lo beba nuevo con vosotros en el reino de mi Padre.
        Jesús anuncia la negación de Pedro
        30 Y cuando hubieron cantado el himno, salieron al monte de los Olivos. 
        31 Entonces Jesús les dijo: Todos vosotros os escandalizaréis de mí esta noche; porque escrito está: Heriré al pastor, y las ovejas del rebaño serán dispersadas. 
        32 Pero después que haya resucitado, iré delante de vosotros a Galilea. 
        33 Respondiendo Pedro, le dijo: Aunque todos se escandalicen de ti, yo nunca me escandalizaré. 
        34 Jesús le dijo: De cierto te digo que esta noche, antes que el gallo cante, me negarás tres veces. 
        35 Pedro le dijo: Aunque me sea necesario morir contigo, no te negaré. Y todos los discípulos dijeron lo mismo.
        Jesús ora en Getsemaní
        36 Entonces llegó Jesús con ellos a un lugar que se llama Getsemaní, y dijo a sus discípulos: Sentaos aquí, entre tanto que voy allí y oro. 
        37 Y tomando a Pedro, y a los dos hijos de Zebedeo, comenzó a entristecerse y a angustiarse en gran manera. 
        38 Entonces Jesús les dijo: Mi alma está muy triste, hasta la muerte; quedaos aquí, y velad conmigo. 
        39 Yendo un poco adelante, se postró sobre su rostro, orando y diciendo: Padre mío, si es posible, pase de mí esta copa; pero no sea como yo quiero, sino como tú. 
        40 Vino luego a sus discípulos, y los halló durmiendo, y dijo a Pedro: ¿Así que no habéis podido velar conmigo una hora? 
        41 Velad y orad, para que no entréis en tentación; el espíritu a la verdad está dispuesto, pero la carne es débil. 
        42 Otra vez fue, y oró por segunda vez, diciendo: Padre mío, si no puede pasar de mí esta copa sin que yo la beba, hágase tu voluntad. 
        43 Vino otra vez y los halló durmiendo, porque los ojos de ellos estaban cargados de sueño. 
        44 Y dejándolos, se fue de nuevo, y oró por tercera vez, diciendo las mismas palabras. 
        45 Entonces vino a sus discípulos y les dijo: Dormid ya, y descansad. He aquí ha llegado la hora, y el Hijo del Hombre es entregado en manos de pecadores. 
        46 Levantaos, vamos; ved, se acerca el que me entrega.
""".trimIndent(),
        "Deuteronomio 1-2" to """
        Deuteronomio 1
        Moisés recuerda a Israel las promesas de Jehová en Horeb
        1 Estas son las palabras que habló Moisés a todo Israel a este lado del Jordán en el desierto, en el Arabá frente al Mar Rojo, entre Parán, Tofel, Labán, Hazerot y Dizahab. 
        2 Once jornadas hay desde Horeb, camino del monte de Seir, hasta Cades-barnea. 
        3 Y aconteció que a los cuarenta años, en el mes undécimo, el primero del mes, Moisés habló a los hijos de Israel conforme a todas las cosas que Jehová le había mandado acerca de ellos, 
        4 después que derrotó a Sehón rey de los amorreos, el cual habitaba en Hesbón, y a Og rey de Basán que habitaba en Astarot en Edrei. 
        5 De este lado del Jordán, en tierra de Moab, resolvió Moisés declarar esta ley, diciendo: 
        6 Jehová nuestro Dios nos habló en Horeb, diciendo: Habéis estado bastante tiempo en este monte. 
        7 Volveos e id al monte del amorreo y a todas sus comarcas, en el Arabá, en el monte, en los valles, en el Neguev, y junto a la costa del mar, a la tierra del cananeo, y al Líbano, hasta el gran río, el río Éufrates. 
        8 Mirad, yo os he entregado la tierra; entrad y poseed la tierra que Jehová juró a vuestros padres Abraham, Isaac y Jacob, que les daría a ellos y a su descendencia después de ellos.
        Nombramiento de jueces
        9 En aquel tiempo yo os hablé diciendo: Yo solo no puedo llevaros. 
        10 Jehová vuestro Dios os ha multiplicado, y he aquí hoy vosotros sois como las estrellas del cielo en multitud. 
        11 ¡Jehová Dios de vuestros padres os haga mil veces más de lo que ahora sois, y os bendiga, como os ha prometido! 
        12 ¿Cómo llevaré yo solo vuestras molestias, vuestras cargas y vuestros pleitos? 
        13 Dadme de entre vosotros, de vuestras tribus, varones sabios y entendidos y expertos, para que yo los ponga por vuestros jefes. 
        14 Y me respondisteis y dijisteis: Bueno es hacer lo que has dicho. 
        15 Y tomé a los principales de vuestras tribus, varones sabios y expertos, y los puse por jefes sobre vosotros, jefes de millares, de centenas, de cincuenta y de diez, y gobernadores de vuestras tribus. 
        16 Y entonces mandé a vuestros jueces, diciendo: Oíd entre vuestros hermanos, y juzgad justamente entre el hombre y su hermano, y el extranjero. 
        17 No hagáis distinción de persona en el juicio; así al pequeño como al grande oiréis; no tendréis temor de ninguno, porque el juicio es de Dios; y la causa que os fuere difícil, la traeréis a mí, y yo la oiré. 
        18 Os mandé, pues, en aquel tiempo, todo lo que habíais de hacer.
        Misión de los doce espías
        19 Y salidos de Horeb, anduvimos todo aquel grande y terrible desierto que habéis visto, por el camino del monte del amorreo, como Jehová nuestro Dios nos lo mandó; y llegamos hasta Cades-barnea. 
        20 Entonces os dije: Habéis llegado al monte del amorreo, el cual Jehová nuestro Dios nos da. 
        21 Mira, Jehová tu Dios te ha entregado la tierra; sube y toma posesión de ella, como Jehová el Dios de tus padres te ha dicho; no temas ni desmayes. 
        22 Y vinisteis a mí todos vosotros, y dijisteis: Enviemos varones delante de nosotros que nos reconozcan la tierra, y a su regreso nos traigan razón del camino por donde hemos de subir, y de las ciudades adonde hemos de llegar. 
        23 Y el dicho me pareció bien; y tomé doce varones de entre vosotros, un varón por cada tribu. 
        24 Y se encaminaron, y subieron al monte, y llegaron hasta el valle de Escol, y reconocieron la tierra. 
        25 Y tomaron en sus manos del fruto del país, y nos lo trajeron, y nos dieron cuenta, y dijeron: Es buena la tierra que Jehová nuestro Dios nos da. 
        26 Sin embargo, no quisisteis subir, antes fuisteis rebeldes al mandato de Jehová vuestro Dios; 
        27 y murmurasteis en vuestras tiendas, diciendo: Porque Jehová nos aborrece, nos ha sacado de tierra de Egipto, para entregarnos en manos del amorreo para destruirnos. 
        28 ¿A dónde subiremos? Nuestros hermanos han atemorizado nuestro corazón, diciendo: Este pueblo es mayor y más alto que nosotros, las ciudades grandes y amuralladas hasta el cielo; y también vimos allí a los hijos de Anac. 
        29 Entonces os dije: No temáis, ni tengáis miedo de ellos. 
        30 Jehová vuestro Dios, el cual va delante de vosotros, él peleará por vosotros, conforme a todas las cosas que hizo por vosotros en Egipto delante de vuestros ojos. 
        31 Y en el desierto has visto que Jehová tu Dios te ha traído, como trae el hombre a su hijo, por todo el camino que habéis andado, hasta llegar a este lugar. 
        32 Y aun con esto no creísteis a Jehová vuestro Dios, 33 quien iba delante de vosotros por el camino para reconoceros el lugar donde habíais de acampar, con fuego de noche para mostraros el camino por donde anduvieseis, y con nube de día.
        Dios castiga a Israel
        34 Y oyó Jehová la voz de vuestras palabras, y se enojó, y juró diciendo: 
        35 No verá hombre alguno de estos, de esta mala generación, la buena tierra que juré que había de dar a vuestros padres, 
        36 excepto Caleb hijo de Jefone; él la verá, y a él le daré la tierra que pisó, y a sus hijos; porque ha seguido fielmente a Jehová. 
        37 También contra mí se airó Jehová por vosotros, y me dijo: Tampoco tú entrarás allá. 
        38 Josué hijo de Nun, el cual te sirve, él entrará allá; anímale, porque él la hará heredar a Israel. 
        39 Y vuestros niños, de los cuales dijisteis que servirían de botín, y vuestros hijos que no saben hoy lo bueno ni lo malo, ellos entrarán allá, y a ellos la daré, y ellos la heredarán. 
        40 Pero vosotros volveos e id al desierto, camino del Mar Rojo.
        La derrota en Horma
        41 Entonces respondisteis y me dijisteis: Hemos pecado contra Jehová; nosotros subiremos y pelearemos, conforme a todo lo que Jehová nuestro Dios nos ha mandado. Y os armasteis cada uno con sus armas de guerra, y os preparasteis para subir al monte. 
        42 Y Jehová me dijo: Diles: No subáis, ni peleéis, pues no estoy entre vosotros; para que no seáis derrotados por vuestros enemigos. 
        43 Y os hablé, y no disteis oído; antes fuisteis rebeldes al mandato de Jehová, y persistiendo con altivez subisteis al monte. 
        44 Pero salió a vuestro encuentro el amorreo, que habitaba en aquel monte, y os persiguieron como hacen las avispas, y os derrotaron en Seir, hasta Horma. 
        45 Y volvisteis y llorasteis delante de Jehová, pero Jehová no escuchó vuestra voz, ni os prestó oído. 46 Y estuvisteis en Cades por muchos días, los días que habéis estado allí.
        Deuteronomio 2
        Los años en el desierto
        1 Luego volvimos y salimos al desierto, camino del Mar Rojo, como Jehová me había dicho; y rodeamos el monte de Seir por mucho tiempo. 
        2 Y Jehová me habló, diciendo: 
        3 Bastante habéis rodeado este monte; volveos al norte. 
        4 Y manda al pueblo, diciendo: Pasando vosotros por el territorio de vuestros hermanos los hijos de Esaú, que habitan en Seir, ellos tendrán miedo de vosotros; mas vosotros guardaos mucho. 
        5 No os metáis con ellos, porque no os daré de su tierra ni aun lo que cubre la planta de un pie; porque yo he dado por heredad a Esaú el monte de Seir. 
        6 Compraréis de ellos por dinero los alimentos, y comeréis; y también compraréis de ellos el agua, y beberéis; 
        7 pues Jehová tu Dios te ha bendecido en toda obra de tus manos; él sabe que andas por este gran desierto; estos cuarenta años Jehová tu Dios ha estado contigo, y nada te ha faltado. 
        8 Y nos alejamos del territorio de nuestros hermanos los hijos de Esaú, que habitaban en Seir, por el camino del Arabá desde Elat y Ezión-geber; y volvimos, y tomamos el camino del desierto de Moab.
        9 Y Jehová me dijo: No molestes a Moab, ni te empeñes con ellos en guerra, porque no te daré posesión de su tierra; porque yo he dado a Ar por heredad a los hijos de Lot. 
        10 (Los emitas habitaron en ella antes, pueblo grande y numeroso, y alto como los hijos de Anac. 
        11 Por gigantes eran ellos tenidos también, como los hijos de Anac; y los moabitas los llaman emitas. 
        12 Y en Seir habitaron antes los horeos, a los cuales echaron los hijos de Esaú; y los arrojaron de su presencia, y habitaron en lugar de ellos, como hizo Israel en la tierra que les dio Jehová por posesión.) 
        13 Levantaos ahora, y pasad el arroyo de Zered. Y pasamos el arroyo de Zered. 
        14 Y los días que anduvimos de Cades-barnea hasta cuando pasamos el arroyo de Zered fueron treinta y ocho años; hasta que se acabó toda la generación de los hombres de guerra de en medio del campamento, como Jehová les había jurado. 
        15 Y también la mano de Jehová vino sobre ellos para destruirlos de en medio del campamento, hasta acabarlos.
        16 Y aconteció que después que murieron todos los hombres de guerra de entre el pueblo, 
        17 Jehová me habló, diciendo: 
        18 Tú pasarás hoy el territorio de Moab, a Ar. 
        19 Y cuando te acerques a los hijos de Amón, no los molestes, ni contiendas con ellos; porque no te daré posesión de la tierra de los hijos de Amón, pues a los hijos de Lot la he dado por heredad. 
        20 (Por tierra de gigantes fue también ella tenida; habitaron en ella gigantes en otro tiempo, a los cuales los amonitas llamaban zomzomeos; 
        21 pueblo grande y numeroso, y alto, como los hijos de Anac; a los cuales Jehová destruyó delante de los amonitas. Estos sucedieron a aquellos, y habitaron en su lugar, 
        22 como hizo Jehová con los hijos de Esaú que habitaban en Seir, delante de los cuales destruyó a los horeos; y ellos sucedieron a estos, y habitaron en su lugar hasta hoy. 
        23 Y a los aveos que habitaban en aldeas hasta Gaza, los caftoreos que salieron de Caftor los destruyeron, y habitaron en su lugar.) 
        24 Levantaos, salid, y pasad el arroyo de Arnón; he aquí he entregado en tu mano a Sehón rey de Hesbón, amorreo, y a su tierra; comienza a tomar posesión de ella, y entra en guerra con él. 
        25 Hoy comenzaré a poner tu temor y tu espanto sobre los pueblos debajo de todo el cielo, los cuales oirán tu fama, y temblarán y se angustiarán delante de ti.
        Israel derrota a Sehón
        26 Y envié mensajeros desde el desierto de Cademot a Sehón rey de Hesbón con palabras de paz, diciendo: 
        27 Pasaré por tu tierra por el camino; por el camino iré, sin apartarme ni a diestra ni a siniestra. 
        28 La comida me venderás por dinero, y comeré; el agua también me darás por dinero, y beberé; solamente pasaré a pie, 
        29 como lo hicieron conmigo los hijos de Esaú que habitaban en Seir, y los moabitas que habitaban en Ar; hasta que cruce el Jordán a la tierra que nos da Jehová nuestro Dios. 
        30 Mas Sehón rey de Hesbón no quiso que pasásemos por el territorio suyo; porque Jehová tu Dios había endurecido su espíritu, y obstinado su corazón para entregarlo en tu mano, como hasta hoy. 
        31 Y me dijo Jehová: He aquí yo he comenzado a entregar delante de ti a Sehón y a su tierra; comienza a tomar posesión de ella para que la heredes. 
        32 Y nos salió Sehón al encuentro, él y todo su pueblo, para pelear en Jahaza. 
        33 Mas Jehová nuestro Dios lo entregó delante de nosotros; y lo derrotamos a él y a sus hijos, y a todo su pueblo. 
        34 Tomamos entonces todas sus ciudades, y destruimos todas las ciudades, hombres, mujeres y niños; no dejamos ninguno. 
        35 Solamente tomamos para nosotros los ganados, y los despojos de las ciudades que habíamos tomado. 
        36 Desde Aroer, que está junto a la ribera del arroyo de Arnón, y la ciudad que está en el valle, hasta Galaad, no hubo ciudad que escapase de nosotros; todas las entregó Jehová nuestro Dios en nuestro poder. 
        37 Solamente a la tierra de los hijos de Amón no llegamos; ni a todo lo que está a la orilla del arroyo de Jaboc ni a las ciudades del monte, ni a lugar alguno que Jehová nuestro Dios había prohibido.
""".trimIndent(),
        "Job 4" to """
        Elifaz reprende a Job
        1 Entonces respondió Elifaz temanita, y dijo:
        2 Si probáremos a hablarte, te será molesto;
        Pero ¿quién podrá detener las palabras?
        3 He aquí, tú enseñabas a muchos,
        Y fortalecías las manos débiles;
        4 Al que tropezaba enderezaban tus palabras,
        Y esforzabas las rodillas que decaían.
        5 Mas ahora que el mal ha venido sobre ti, te desalientas;
        Y cuando ha llegado hasta ti, te turbas.
        6 ¿No es tu temor a Dios tu confianza?
        ¿No es tu esperanza la integridad de tus caminos?
        7 Recapacita ahora; ¿qué inocente se ha perdido?
        Y ¿en dónde han sido destruidos los rectos?
        8 Como yo he visto, los que aran iniquidad
        Y siembran injuria, la siegan.
        9 Perecen por el aliento de Dios,
        Y por el soplo de su ira son consumidos.
        10 Los rugidos del león, y los bramidos del rugiente,
        Y los dientes de los leoncillos son quebrantados.
        11 El león viejo perece por falta de presa,
        Y los hijos de la leona se dispersan.
        12 El asunto también me era a mí oculto;
        Mas mi oído ha percibido algo de ello.
        13 En imaginaciones de visiones nocturnas,
        Cuando el sueño cae sobre los hombres,
        14 Me sobrevino un espanto y un temblor,
        Que estremeció todos mis huesos;
        15 Y al pasar un espíritu por delante de mí,
        Hizo que se erizara el pelo de mi cuerpo.
        16 Paróse delante de mis ojos un fantasma,
        Cuyo rostro yo no conocí,
        Y quedo, oí que decía:
        17 ¿Será el hombre más justo que Dios?
        ¿Será el varón más limpio que el que lo hizo?
        18 He aquí, en sus siervos no confía,
        Y notó necedad en sus ángeles;
        19 ¡Cuánto más en los que habitan en casas de barro,
        Cuyos cimientos están en el polvo,
        Y que serán quebrantados por la polilla!
        20 De la mañana a la tarde son destruidos,
        Y se pierden para siempre, sin haber quien repare en ello.
        21 Su hermosura, ¿no se pierde con ellos mismos?
        Y mueren sin haber adquirido sabiduría.
""".trimIndent(),
        "Mateo 26:47-75" to """
        Arresto de Jesús
        47 Mientras todavía hablaba, vino Judas, uno de los doce, y con él mucha gente con espadas y palos, de parte de los principales sacerdotes y de los ancianos del pueblo. 
        48 Y el que le entregaba les había dado señal, diciendo: Al que yo besare, ese es; prendedle. 
        49 Y en seguida se acercó a Jesús y dijo: ¡Salve, Maestro! Y le besó. 
        50 Y Jesús le dijo: Amigo, ¿a qué vienes? Entonces se acercaron y echaron mano a Jesús, y le prendieron. 
        51 Pero uno de los que estaban con Jesús, extendiendo la mano, sacó su espada, e hiriendo a un siervo del sumo sacerdote, le quitó la oreja. 
        52 Entonces Jesús le dijo: Vuelve tu espada a su lugar; porque todos los que tomen espada, a espada perecerán. 
        53 ¿Acaso piensas que no puedo ahora orar a mi Padre, y que él no me daría más de doce legiones de ángeles? 
        54 ¿Pero cómo entonces se cumplirían las Escrituras, de que es necesario que así se haga? 
        55 En aquella hora dijo Jesús a la gente: ¿Como contra un ladrón habéis salido con espadas y con palos para prenderme? Cada día me sentaba con vosotros enseñando en el templo, y no me prendisteis. 
        56 Mas todo esto sucede, para que se cumplan las Escrituras de los profetas. Entonces todos los discípulos, dejándole, huyeron.
        Jesús ante el concilio
        57 Los que prendieron a Jesús le llevaron al sumo sacerdote Caifás, adonde estaban reunidos los escribas y los ancianos. 
        58 Mas Pedro le seguía de lejos hasta el patio del sumo sacerdote; y entrando, se sentó con los alguaciles, para ver el fin. 
        59 Y los principales sacerdotes y los ancianos y todo el concilio, buscaban falso testimonio contra Jesús, para entregarle a la muerte, 
        60 y no lo hallaron, aunque muchos testigos falsos se presentaban. Pero al fin vinieron dos testigos falsos, 
        61 que dijeron: Este dijo: Puedo derribar el templo de Dios, y en tres días reedificarlo. 
        62 Y levantándose el sumo sacerdote, le dijo: ¿No respondes nada? ¿Qué testifican estos contra ti? 
        63 Mas Jesús callaba. Entonces el sumo sacerdote le dijo: Te conjuro por el Dios viviente, que nos digas si eres tú el Cristo, el Hijo de Dios. 
        64 Jesús le dijo: Tú lo has dicho; y además os digo, que desde ahora veréis al Hijo del Hombre sentado a la diestra del poder de Dios, y viniendo en las nubes del cielo. 
        65 Entonces el sumo sacerdote rasgó sus vestiduras, diciendo: ¡Ha blasfemado! ¿Qué más necesidad tenemos de testigos? He aquí, ahora mismo habéis oído su blasfemia. 
        66 ¿Qué os parece? Y respondiendo ellos, dijeron: ¡Es reo de muerte! 
        67 Entonces le escupieron en el rostro, y le dieron de puñetazos, y otros le abofeteaban, 
        68 diciendo: Profetízanos, Cristo, quién es el que te golpeó.
        Pedro niega a Jesús
        69 Pedro estaba sentado fuera en el patio; y se le acercó una criada, diciendo: Tú también estabas con Jesús el galileo. 
        70 Mas él negó delante de todos, diciendo: No sé lo que dices. 
        71 Saliendo él a la puerta, le vio otra, y dijo a los que estaban allí: También este estaba con Jesús el nazareno. 
        72 Pero él negó otra vez con juramento: No conozco al hombre. 
        73 Un poco después, acercándose los que por allí estaban, dijeron a Pedro: Verdaderamente también tú eres de ellos, porque aun tu manera de hablar te descubre. 
        74 Entonces él comenzó a maldecir, y a jurar: No conozco al hombre. Y en seguida cantó el gallo. 
        75 Entonces Pedro se acordó de las palabras de Jesús, que le había dicho: Antes que cante el gallo, me negarás tres veces. Y saliendo fuera, lloró amargamente.
""".trimIndent(),
        "Deuteronomio 3-4" to """
        Deuteronomio 3
        Israel derrota a Og rey de Basán
        1 Volvimos, pues, y subimos camino de Basán, y nos salió al encuentro Og rey de Basán para pelear, él y todo su pueblo, en Edrei. 
        2 Y me dijo Jehová: No tengas temor de él, porque en tu mano he entregado a él y a todo su pueblo, con su tierra; y harás con él como hiciste con Sehón rey amorreo, que habitaba en Hesbón. 
        3 Y Jehová nuestro Dios entregó también en nuestra mano a Og rey de Basán, y a todo su pueblo, al cual derrotamos hasta acabar con todos. 
        4 Y tomamos entonces todas sus ciudades; no quedó ciudad que no les tomásemos; sesenta ciudades, toda la tierra de Argob, del reino de Og en Basán. 
        5 Todas estas eran ciudades fortificadas con muros altos, con puertas y barras, sin contar otras muchas ciudades sin muro. 
        6 Y las destruimos, como hicimos a Sehón rey de Hesbón, matando en toda ciudad a hombres, mujeres y niños. 
        7 Y tomamos para nosotros todo el ganado, y los despojos de las ciudades. 
        8 También tomamos en aquel tiempo la tierra desde el arroyo de Arnón hasta el monte de Hermón, de manos de los dos reyes amorreos que estaban a este lado del Jordán. 
        9 (Los sidonios llaman a Hermón, Sirión; y los amorreos, Senir.) 
        10 Todas las ciudades de la llanura, y todo Galaad, y todo Basán hasta Salca y Edrei, ciudades del reino de Og en Basán. 
        11 Porque únicamente Og rey de Basán había quedado del resto de los gigantes. Su cama, una cama de hierro, ¿no está en Rabá de los hijos de Amón? La longitud de ella es de nueve codos, y su anchura de cuatro codos, según el codo de un hombre.
        Rubén, Gad y la media tribu de Manasés se establecen al oriente del Jordán
        12 Y esta tierra que heredamos en aquel tiempo, desde Aroer, que está junto al arroyo de Arnón, y la mitad del monte de Galaad con sus ciudades, la di a los rubenitas y a los gaditas; 
        13 y el resto de Galaad, y todo Basán, del reino de Og, toda la tierra de Argob, que se llamaba la tierra de los gigantes, lo di a la media tribu de Manasés. 
        14 Jair hijo de Manasés tomó toda la tierra de Argob hasta el límite con Gesur y Maaca, y la llamó por su nombre, Basán-havot-jair, hasta hoy. 
        15 Y Galaad se lo di a Maquir. 
        16 Y a los rubenitas y gaditas les di de Galaad hasta el arroyo de Arnón, teniendo por límite el medio del valle, hasta el arroyo de Jaboc, el cual es límite de los hijos de Amón; 
        17 también el Arabá, con el Jordán como límite desde Cineret hasta el mar del Arabá, el Mar Salado, al pie de las laderas del Pisga al oriente.
        18 Y os mandé entonces, diciendo: Jehová vuestro Dios os ha dado esta tierra por heredad; pero iréis armados todos los valientes delante de vuestros hermanos los hijos de Israel. 
        19 Solamente vuestras mujeres, vuestros hijos y vuestros ganados (yo sé que tenéis mucho ganado), quedarán en las ciudades que os he dado, 
        20 hasta que Jehová dé reposo a vuestros hermanos, así como a vosotros, y hereden ellos también la tierra que Jehová vuestro Dios les da al otro lado del Jordán; entonces os volveréis cada uno a la heredad que yo os he dado. 
        21 Ordené también a Josué en aquel tiempo, diciendo: Tus ojos vieron todo lo que Jehová vuestro Dios ha hecho a aquellos dos reyes; así hará Jehová a todos los reinos a los cuales pasarás tú. 
        22 No los temáis; porque Jehová vuestro Dios, él es el que pelea por vosotros.
        No se le permite a Moisés entrar a Canaán
        23 Y oré a Jehová en aquel tiempo, diciendo: 
        24 Señor Jehová, tú has comenzado a mostrar a tu siervo tu grandeza, y tu mano poderosa; porque ¿qué dios hay en el cielo ni en la tierra que haga obras y proezas como las tuyas? 
        25 Pase yo, te ruego, y vea aquella tierra buena que está más allá del Jordán, aquel buen monte, y el Líbano. 
        26 Pero Jehová se había enojado contra mí a causa de vosotros, por lo cual no me escuchó; y me dijo Jehová: Basta, no me hables más de este asunto. 
        27 Sube a la cumbre del Pisga y alza tus ojos al oeste, y al norte, y al sur, y al este, y mira con tus propios ojos; porque no pasarás el Jordán. 
        28 Y manda a Josué, y anímalo, y fortalécelo; porque él ha de pasar delante de este pueblo, y él les hará heredar la tierra que verás. 
        29 Y paramos en el valle delante de Bet-peor.
        Deuteronomio 4
        Moisés exhorta a la obediencia
        1 Ahora, pues, oh Israel, oye los estatutos y decretos que yo os enseño, para que los ejecutéis, y viváis, y entréis y poseáis la tierra que Jehová el Dios de vuestros padres os da. 
        2 No añadiréis a la palabra que yo os mando, ni disminuiréis de ella, para que guardéis los mandamientos de Jehová vuestro Dios que yo os ordeno. 
        3 Vuestros ojos vieron lo que hizo Jehová con motivo de Baal-peor; que a todo hombre que fue en pos de Baal-peor destruyó Jehová tu Dios de en medio de ti. 
        4 Mas vosotros que seguisteis a Jehová vuestro Dios, todos estáis vivos hoy. 
        5 Mirad, yo os he enseñado estatutos y decretos, como Jehová mi Dios me mandó, para que hagáis así en medio de la tierra en la cual entráis para tomar posesión de ella. 
        6 Guardadlos, pues, y ponedlos por obra; porque esta es vuestra sabiduría y vuestra inteligencia ante los ojos de los pueblos, los cuales oirán todos estos estatutos, y dirán: Ciertamente pueblo sabio y entendido, nación grande es esta. 
        7 Porque ¿qué nación grande hay que tenga dioses tan cercanos a ellos como lo está Jehová nuestro Dios en todo cuanto le pedimos? 
        8 Y ¿qué nación grande hay que tenga estatutos y juicios justos como es toda esta ley que yo pongo hoy delante de vosotros?
        La experiencia de Israel en Horeb
        9 Por tanto, guárdate, y guarda tu alma con diligencia, para que no te olvides de las cosas que tus ojos han visto, ni se aparten de tu corazón todos los días de tu vida; antes bien, las enseñarás a tus hijos, y a los hijos de tus hijos. 
        10 El día que estuviste delante de Jehová tu Dios en Horeb, cuando Jehová me dijo: Reúneme el pueblo, para que yo les haga oír mis palabras, las cuales aprenderán, para temerme todos los días que vivieren sobre la tierra, y las enseñarán a sus hijos; 
        11 y os acercasteis y os pusisteis al pie del monte; y el monte ardía en fuego hasta en medio de los cielos con tinieblas, nube y oscuridad; 
        12 y habló Jehová con vosotros de en medio del fuego; oísteis la voz de sus palabras, mas a excepción de oír la voz, ninguna figura visteis. 
        13 Y él os anunció su pacto, el cual os mandó poner por obra; los diez mandamientos, y los escribió en dos tablas de piedra. 
        14 A mí también me mandó Jehová en aquel tiempo que os enseñase los estatutos y juicios, para que los pusieseis por obra en la tierra a la cual pasáis a tomar posesión de ella.
        Advertencia contra la idolatría
        15 Guardad, pues, mucho vuestras almas; pues ninguna figura visteis el día que Jehová habló con vosotros de en medio del fuego; 
        16 para que no os corrompáis y hagáis para vosotros escultura, imagen de figura alguna, efigie de varón o hembra, 
        17 figura de animal alguno que está en la tierra, figura de ave alguna alada que vuele por el aire, 
        18 figura de ningún animal que se arrastre sobre la tierra, figura de pez alguno que haya en el agua debajo de la tierra. 
        19 No sea que alces tus ojos al cielo, y viendo el sol y la luna y las estrellas, y todo el ejército del cielo, seas impulsado, y te inclines a ellos y les sirvas; porque Jehová tu Dios los ha concedido a todos los pueblos debajo de todos los cielos. 
        20 Pero a vosotros Jehová os tomó, y os ha sacado del horno de hierro, de Egipto, para que seáis el pueblo de su heredad como en este día. 
        21 Y Jehová se enojó contra mí por causa de vosotros, y juró que yo no pasaría el Jordán, ni entraría en la buena tierra que Jehová tu Dios te da por heredad. 
        22 Así que yo voy a morir en esta tierra, y no pasaré el Jordán; mas vosotros pasaréis, y poseeréis aquella buena tierra. 
        23 Guardaos, no os olvidéis del pacto de Jehová vuestro Dios, que él estableció con vosotros, y no os hagáis escultura o imagen de ninguna cosa que Jehová tu Dios te ha prohibido. 
        24 Porque Jehová tu Dios es fuego consumidor, Dios celoso.
        25 Cuando hayáis engendrado hijos y nietos, y hayáis envejecido en la tierra, si os corrompiereis e hiciereis escultura o imagen de cualquier cosa, e hiciereis lo malo ante los ojos de Jehová vuestro Dios, para enojarlo; 
        26 yo pongo hoy por testigos al cielo y a la tierra, que pronto pereceréis totalmente de la tierra hacia la cual pasáis el Jordán para tomar posesión de ella; no estaréis en ella largos días sin que seáis destruidos. 
        27 Y Jehová os esparcirá entre los pueblos, y quedaréis pocos en número entre las naciones a las cuales os llevará Jehová. 
        28 Y serviréis allí a dioses hechos de manos de hombres, de madera y piedra, que no ven, ni oyen, ni comen, ni huelen. 
        29 Mas si desde allí buscares a Jehová tu Dios, lo hallarás, si lo buscares de todo tu corazón y de toda tu alma. 
        30 Cuando estuvieres en angustia, y te alcanzaren todas estas cosas, si en los postreros días te volvieres a Jehová tu Dios, y oyeres su voz; 
        31 porque Dios misericordioso es Jehová tu Dios; no te dejará, ni te destruirá, ni se olvidará del pacto que les juró a tus padres.
        32 Porque pregunta ahora si en los tiempos pasados que han sido antes de ti, desde el día que creó Dios al hombre sobre la tierra, si desde un extremo del cielo al otro se ha hecho cosa semejante a esta gran cosa, o se haya oído otra como ella. 
        33 ¿Ha oído pueblo alguno la voz de Dios, hablando de en medio del fuego, como tú la has oído, sin perecer? 
        34 ¿O ha intentado Dios venir a tomar para sí una nación de en medio de otra nación, con pruebas, con señales, con milagros y con guerra, y mano poderosa y brazo extendido, y hechos aterradores como todo lo que hizo con vosotros Jehová vuestro Dios en Egipto ante tus ojos? 
        35 A ti te fue mostrado, para que supieses que Jehová es Dios, y no hay otro fuera de él. 
        36 Desde los cielos te hizo oír su voz, para enseñarte; y sobre la tierra te mostró su gran fuego, y has oído sus palabras de en medio del fuego. 
        37 Y por cuanto él amó a tus padres, escogió a su descendencia después de ellos, y te sacó de Egipto con su presencia y con su gran poder, 
        38 para echar de delante de tu presencia naciones grandes y más fuertes que tú, y para introducirte y darte su tierra por heredad, como hoy. 
        39 Aprende pues, hoy, y reflexiona en tu corazón que Jehová es Dios arriba en el cielo y abajo en la tierra, y no hay otro. 
        40 Y guarda sus estatutos y sus mandamientos, los cuales yo te mando hoy, para que te vaya bien a ti y a tus hijos después de ti, y prolongues tus días sobre la tierra que Jehová tu Dios te da para siempre.
        Las ciudades de refugio al oriente del Jordán
        41 Entonces apartó Moisés tres ciudades a este lado del Jordán al nacimiento del sol, 
        42 para que huyese allí el homicida que matase a su prójimo sin intención, sin haber tenido enemistad con él nunca antes; y que huyendo a una de estas ciudades salvase su vida: 
        43 Beser en el desierto, en tierra de la llanura, para los rubenitas; Ramot en Galaad para los gaditas, y Golán en Basán para los de Manasés.
        Moisés recapitula la promulgación de la ley
        44 Esta, pues, es la ley que Moisés puso delante de los hijos de Israel. 
        45 Estos son los testimonios, los estatutos y los decretos que habló Moisés a los hijos de Israel cuando salieron de Egipto; 
        46 a este lado del Jordán, en el valle delante de Bet-peor, en la tierra de Sehón rey de los amorreos que habitaba en Hesbón, al cual derrotó Moisés con los hijos de Israel, cuando salieron de Egipto; 
        47 y poseyeron su tierra, y la tierra de Og rey de Basán; dos reyes de los amorreos que estaban de este lado del Jordán, al oriente. 
        48 Desde Aroer, que está junto a la ribera del arroyo de Arnón, hasta el monte de Sion, que es Hermón; 
        49 y todo el Arabá de este lado del Jordán, al oriente, hasta el mar del Arabá, al pie de las laderas del Pisga.
""".trimIndent(),
        "Mateo 26:47-75" to """
        Arresto de Jesús
        47 Mientras todavía hablaba, vino Judas, uno de los doce, y con él mucha gente con espadas y palos, de parte de los principales sacerdotes y de los ancianos del pueblo. 
        48 Y el que le entregaba les había dado señal, diciendo: Al que yo besare, ese es; prendedle. 
        49 Y en seguida se acercó a Jesús y dijo: ¡Salve, Maestro! Y le besó. 
        50 Y Jesús le dijo: Amigo, ¿a qué vienes? Entonces se acercaron y echaron mano a Jesús, y le prendieron. 
        51 Pero uno de los que estaban con Jesús, extendiendo la mano, sacó su espada, e hiriendo a un siervo del sumo sacerdote, le quitó la oreja. 
        52 Entonces Jesús le dijo: Vuelve tu espada a su lugar; porque todos los que tomen espada, a espada perecerán. 
        53 ¿Acaso piensas que no puedo ahora orar a mi Padre, y que él no me daría más de doce legiones de ángeles? 
        54 ¿Pero cómo entonces se cumplirían las Escrituras, de que es necesario que así se haga? 
        55 En aquella hora dijo Jesús a la gente: ¿Como contra un ladrón habéis salido con espadas y con palos para prenderme? Cada día me sentaba con vosotros enseñando en el templo, y no me prendisteis. 
        56 Mas todo esto sucede, para que se cumplan las Escrituras de los profetas. Entonces todos los discípulos, dejándole, huyeron.
        Jesús ante el concilio
        57 Los que prendieron a Jesús le llevaron al sumo sacerdote Caifás, adonde estaban reunidos los escribas y los ancianos. 
        58 Mas Pedro le seguía de lejos hasta el patio del sumo sacerdote; y entrando, se sentó con los alguaciles, para ver el fin. 
        59 Y los principales sacerdotes y los ancianos y todo el concilio, buscaban falso testimonio contra Jesús, para entregarle a la muerte, 
        60 y no lo hallaron, aunque muchos testigos falsos se presentaban. Pero al fin vinieron dos testigos falsos, 
        61 que dijeron: Este dijo: Puedo derribar el templo de Dios, y en tres días reedificarlo. 
        62 Y levantándose el sumo sacerdote, le dijo: ¿No respondes nada? ¿Qué testifican estos contra ti? 
        63 Mas Jesús callaba. Entonces el sumo sacerdote le dijo: Te conjuro por el Dios viviente, que nos digas si eres tú el Cristo, el Hijo de Dios. 
        64 Jesús le dijo: Tú lo has dicho; y además os digo, que desde ahora veréis al Hijo del Hombre sentado a la diestra del poder de Dios, y viniendo en las nubes del cielo. 
        65 Entonces el sumo sacerdote rasgó sus vestiduras, diciendo: ¡Ha blasfemado! ¿Qué más necesidad tenemos de testigos? He aquí, ahora mismo habéis oído su blasfemia. 
        66 ¿Qué os parece? Y respondiendo ellos, dijeron: ¡Es reo de muerte! 
        67 Entonces le escupieron en el rostro, y le dieron de puñetazos, y otros le abofeteaban, 
        68 diciendo: Profetízanos, Cristo, quién es el que te golpeó.
        Pedro niega a Jesús
        69 Pedro estaba sentado fuera en el patio; y se le acercó una criada, diciendo: Tú también estabas con Jesús el galileo. 
        70 Mas él negó delante de todos, diciendo: No sé lo que dices. 
        71 Saliendo él a la puerta, le vio otra, y dijo a los que estaban allí: También este estaba con Jesús el nazareno. 
        72 Pero él negó otra vez con juramento: No conozco al hombre. 
        73 Un poco después, acercándose los que por allí estaban, dijeron a Pedro: Verdaderamente también tú eres de ellos, porque aun tu manera de hablar te descubre. 
        74 Entonces él comenzó a maldecir, y a jurar: No conozco al hombre. Y en seguida cantó el gallo. 
        75 Entonces Pedro se acordó de las palabras de Jesús, que le había dicho: Antes que cante el gallo, me negarás tres veces. Y saliendo fuera, lloró amargamente.
""".trimIndent(),
        "Job 5" to """
        Ahora, pues, da voces; ¿habrá quien te responda?
        ¿Y a cuál de los santos te volverás?
        2 Es cierto que al necio lo mata la ira,
        Y al codicioso lo consume la envidia.
        3 Yo he visto al necio que echaba raíces,
        Y en la misma hora maldije su habitación.
        4 Sus hijos estarán lejos de la seguridad;
        En la puerta serán quebrantados,
        Y no habrá quién los libre.
        5 Su mies comerán los hambrientos,
        Y la sacarán de entre los espinos,
        Y los sedientos beberán su hacienda.
        6 Porque la aflicción no sale del polvo,
        Ni la molestia brota de la tierra.
        7 Pero como las chispas se levantan para volar por el aire,
        Así el hombre nace para la aflicción.
        8 Ciertamente yo buscaría a Dios,
        Y encomendaría a él mi causa;
        9 El cual hace cosas grandes e inescrutables,
        Y maravillas sin número;
        10 Que da la lluvia sobre la faz de la tierra,
        Y envía las aguas sobre los campos;
        11 Que pone a los humildes en altura,
        Y a los enlutados levanta a seguridad;
        12 Que frustra los pensamientos de los astutos,
        Para que sus manos no hagan nada;
        13 Que prende a los sabios en la astucia de ellos,
        Y frustra los designios de los perversos.
        14 De día tropiezan con tinieblas,
        Y a mediodía andan a tientas como de noche.
        15 Así libra de la espada al pobre, de la boca de los impíos,
        Y de la mano violenta;
        16 Pues es esperanza al menesteroso,
        Y la iniquidad cerrará su boca.
        17 He aquí, bienaventurado es el hombre a quien Dios castiga;
        Por tanto, no menosprecies la corrección del Todopoderoso.
        18 Porque él es quien hace la llaga, y él la vendará;
        Él hiere, y sus manos curan.
        19 En seis tribulaciones te librará,
        Y en la séptima no te tocará el mal.
        20 En el hambre te salvará de la muerte,
        Y del poder de la espada en la guerra.
        21 Del azote de la lengua serás encubierto;
        No temerás la destrucción cuando viniere.
        22 De la destrucción y del hambre te reirás,
        Y no temerás de las fieras del campo;
        23 Pues aun con las piedras del campo tendrás tu pacto,
        Y las fieras del campo estarán en paz contigo.
        24 Sabrás que hay paz en tu tienda;
        Visitarás tu morada, y nada te faltará.
        25 Asimismo echarás de ver que tu descendencia es mucha,
        Y tu prole como la hierba de la tierra.
        26 Vendrás en la vejez a la sepultura,
        Como la gavilla de trigo que se recoge a su tiempo.
        27 He aquí lo que hemos inquirido, lo cual es así;
        Óyelo, y conócelo tú para tu provecho.
""".trimIndent(),
        "Mateo 27:1-31" to """
        Jesús ante Pilato
        1 Venida la mañana, todos los principales sacerdotes y los ancianos del pueblo entraron en consejo contra Jesús, para entregarle a muerte. 
        2 Y le llevaron atado, y le entregaron a Poncio Pilato, el gobernador.
        Muerte de Judas
        3 Entonces Judas, el que le había entregado, viendo que era condenado, devolvió arrepentido las treinta piezas de plata a los principales sacerdotes y a los ancianos, 
        4 diciendo: Yo he pecado entregando sangre inocente. Mas ellos dijeron: ¿Qué nos importa a nosotros? ¡Allá tú! 
        5 Y arrojando las piezas de plata en el templo, salió, y fue y se ahorcó. 
        6 Los principales sacerdotes, tomando las piezas de plata, dijeron: No es lícito echarlas en el tesoro de las ofrendas, porque es precio de sangre. 
        7 Y después de consultar, compraron con ellas el campo del alfarero, para sepultura de los extranjeros. 
        8 Por lo cual aquel campo se llama hasta el día de hoy: Campo de sangre. 
        9 Así se cumplió lo dicho por el profeta Jeremías, cuando dijo: Y tomaron las treinta piezas de plata, precio del apreciado, según precio puesto por los hijos de Israel; 
        10 y las dieron para el campo del alfarero, como me ordenó el Señor.
        Pilato interroga a Jesús
        11 Jesús, pues, estaba en pie delante del gobernador; y este le preguntó, diciendo: ¿Eres tú el Rey de los judíos? Y Jesús le dijo: Tú lo dices. 
        12 Y siendo acusado por los principales sacerdotes y por los ancianos, nada respondió. 
        13 Pilato entonces le dijo: ¿No oyes cuántas cosas testifican contra ti? 
        14 Pero Jesús no le respondió ni una palabra; de tal manera que el gobernador se maravillaba mucho.
        Jesús sentenciado a muerte
        15 Ahora bien, en el día de la fiesta acostumbraba el gobernador soltar al pueblo un preso, el que quisiesen. 
        16 Y tenían entonces un preso famoso llamado Barrabás. 
        17 Reunidos, pues, ellos, les dijo Pilato: ¿A quién queréis que os suelte: a Barrabás, o a Jesús, llamado el Cristo? 
        18 Porque sabía que por envidia le habían entregado. 
        19 Y estando él sentado en el tribunal, su mujer le mandó decir: No tengas nada que ver con ese justo; porque hoy he padecido mucho en sueños por causa de él. 
        20 Pero los principales sacerdotes y los ancianos persuadieron a la multitud que pidiese a Barrabás, y que Jesús fuese muerto. 
        21 Y respondiendo el gobernador, les dijo: ¿A cuál de los dos queréis que os suelte? Y ellos dijeron: A Barrabás. 
        22 Pilato les dijo: ¿Qué, pues, haré de Jesús, llamado el Cristo? Todos le dijeron: ¡Sea crucificado! 
        23 Y el gobernador les dijo: Pues ¿qué mal ha hecho? Pero ellos gritaban aún más, diciendo: ¡Sea crucificado!
        24 Viendo Pilato que nada adelantaba, sino que se hacía más alboroto, tomó agua y se lavó las manos delante del pueblo, diciendo: Inocente soy yo de la sangre de este justo; allá vosotros. 
        25 Y respondiendo todo el pueblo, dijo: Su sangre sea sobre nosotros, y sobre nuestros hijos. 
        26 Entonces les soltó a Barrabás; y habiendo azotado a Jesús, le entregó para ser crucificado.
        27 Entonces los soldados del gobernador llevaron a Jesús al pretorio, y reunieron alrededor de él a toda la compañía; 
        28 y desnudándole, le echaron encima un manto de escarlata, 
        29 y pusieron sobre su cabeza una corona tejida de espinas, y una caña en su mano derecha; e hincando la rodilla delante de él, le escarnecían, diciendo: ¡Salve, Rey de los judíos! 
        30 Y escupiéndole, tomaban la caña y le golpeaban en la cabeza. 31 Después de haberle escarnecido, le quitaron el manto, le pusieron sus vestidos, y le llevaron para crucificarle.
""".trimIndent(),
        "Deuteronomio 5-6" to """
        Deuteronomio 5     
        Los Diez Mandamientos
        1 Llamó Moisés a todo Israel y les dijo: Oye, Israel, los estatutos y decretos que yo pronuncio hoy en vuestros oídos; aprendedlos, y guardadlos, para ponerlos por obra. 
        2 Jehová nuestro Dios hizo pacto con nosotros en Horeb. 
        3 No con nuestros padres hizo Jehová este pacto, sino con nosotros todos los que estamos aquí hoy vivos. 
        4 Cara a cara habló Jehová con vosotros en el monte de en medio del fuego. 
        5 Yo estaba entonces entre Jehová y vosotros, para declararos la palabra de Jehová; porque vosotros tuvisteis temor del fuego, y no subisteis al monte. Dijo:
        6 Yo soy Jehová tu Dios, que te saqué de tierra de Egipto, de casa de servidumbre.
        7 No tendrás dioses ajenos delante de mí.
        8 No harás para ti escultura, ni imagen alguna de cosa que está arriba en los cielos, ni abajo en la tierra, ni en las aguas debajo de la tierra. 
        9 No te inclinarás a ellas ni las servirás; porque yo soy Jehová tu Dios, fuerte, celoso, que visito la maldad de los padres sobre los hijos hasta la tercera y cuarta generación de los que me aborrecen, 
        10 y que hago misericordia a millares, a los que me aman y guardan mis mandamientos.
        11 No tomarás el nombre de Jehová tu Dios en vano; porque Jehová no dará por inocente al que tome su nombre en vano.
        12 Guardarás el día de reposo[a] para santificarlo, como Jehová tu Dios te ha mandado. 
        13 Seis días trabajarás, y harás toda tu obra; 
        14 mas el séptimo día es reposo[b] a Jehová tu Dios; ninguna obra harás tú, ni tu hijo, ni tu hija, ni tu siervo, ni tu sierva, ni tu buey, ni tu asno, ni ningún animal tuyo, ni el extranjero que está dentro de tus puertas, para que descanse tu siervo y tu sierva como tú. 
        15 Acuérdate que fuiste siervo en tierra de Egipto, y que Jehová tu Dios te sacó de allá con mano fuerte y brazo extendido; por lo cual Jehová tu Dios te ha mandado que guardes el día de reposo.[c]
        16 Honra a tu padre y a tu madre, como Jehová tu Dios te ha mandado, para que sean prolongados tus días, y para que te vaya bien sobre la tierra que Jehová tu Dios te da.
        17 No matarás.
        18 No cometerás adulterio.
        19 No hurtarás.
        20 No dirás falso testimonio contra tu prójimo.
        21 No codiciarás la mujer de tu prójimo, ni desearás la casa de tu prójimo, ni su tierra, ni su siervo, ni su sierva, ni su buey, ni su asno, ni cosa alguna de tu prójimo.
        El terror del pueblo
        22 Estas palabras habló Jehová a toda vuestra congregación en el monte, de en medio del fuego, de la nube y de la oscuridad, a gran voz; y no añadió más. Y las escribió en dos tablas de piedra, las cuales me dio a mí. 
        23 Y aconteció que cuando vosotros oísteis la voz de en medio de las tinieblas, y visteis al monte que ardía en fuego, vinisteis a mí, todos los príncipes de vuestras tribus, y vuestros ancianos, 
        24 y dijisteis: He aquí Jehová nuestro Dios nos ha mostrado su gloria y su grandeza, y hemos oído su voz de en medio del fuego; hoy hemos visto que Jehová habla al hombre, y este aún vive. 
        25 Ahora, pues, ¿por qué vamos a morir? Porque este gran fuego nos consumirá; si oyéremos otra vez la voz de Jehová nuestro Dios, moriremos. 
        26 Porque ¿qué es el hombre, para que oiga la voz del Dios viviente que habla de en medio del fuego, como nosotros la oímos, y aún viva? 
        27 Acércate tú, y oye todas las cosas que dijere Jehová nuestro Dios; y tú nos dirás todo lo que Jehová nuestro Dios te dijere, y nosotros oiremos y haremos.
        28 Y oyó Jehová la voz de vuestras palabras cuando me hablabais, y me dijo Jehová: He oído la voz de las palabras de este pueblo, que ellos te han hablado; bien está todo lo que han dicho. 
        29 ¡Quién diera que tuviesen tal corazón, que me temiesen y guardasen todos los días todos mis mandamientos, para que a ellos y a sus hijos les fuese bien para siempre! 
        30 Ve y diles: Volveos a vuestras tiendas. 
        31 Y tú quédate aquí conmigo, y te diré todos los mandamientos y estatutos y decretos que les enseñarás, a fin de que los pongan ahora por obra en la tierra que yo les doy por posesión. 
        32 Mirad, pues, que hagáis como Jehová vuestro Dios os ha mandado; no os apartéis a diestra ni a siniestra. 
        33 Andad en todo el camino que Jehová vuestro Dios os ha mandado, para que viváis y os vaya bien, y tengáis largos días en la tierra que habéis de poseer.
        Deuteronomio 6
        El gran mandamiento
        1 Estos, pues, son los mandamientos, estatutos y decretos que Jehová vuestro Dios mandó que os enseñase, para que los pongáis por obra en la tierra a la cual pasáis vosotros para tomarla; 
        2 para que temas a Jehová tu Dios, guardando todos sus estatutos y sus mandamientos que yo te mando, tú, tu hijo, y el hijo de tu hijo, todos los días de tu vida, para que tus días sean prolongados. 
        3 Oye, pues, oh Israel, y cuida de ponerlos por obra, para que te vaya bien en la tierra que fluye leche y miel, y os multipliquéis, como te ha dicho Jehová el Dios de tus padres.
        4 Oye, Israel: Jehová nuestro Dios, Jehová uno es. 
        5 Y amarás a Jehová tu Dios de todo tu corazón, y de toda tu alma, y con todas tus fuerzas. 
        6 Y estas palabras que yo te mando hoy, estarán sobre tu corazón; 
        7 y las repetirás a tus hijos, y hablarás de ellas estando en tu casa, y andando por el camino, y al acostarte, y cuando te levantes. 
        8 Y las atarás como una señal en tu mano, y estarán como frontales entre tus ojos; 
        9 y las escribirás en los postes de tu casa, y en tus puertas.
        Exhortaciones a la obediencia
        10 Cuando Jehová tu Dios te haya introducido en la tierra que juró a tus padres Abraham, Isaac y Jacob que te daría, en ciudades grandes y buenas que tú no edificaste, 
        11 y casas llenas de todo bien, que tú no llenaste, y cisternas cavadas que tú no cavaste, viñas y olivares que no plantaste, y luego que comas y te sacies, 
        12 cuídate de no olvidarte de Jehová, que te sacó de la tierra de Egipto, de casa de servidumbre. 
        13 A Jehová tu Dios temerás, y a él sólo servirás, y por su nombre jurarás. 
        14 No andaréis en pos de dioses ajenos, de los dioses de los pueblos que están en vuestros contornos; 
        15 porque el Dios celoso, Jehová tu Dios, en medio de ti está; para que no se inflame el furor de Jehová tu Dios contra ti, y te destruya de sobre la tierra.
        16 No tentaréis a Jehová vuestro Dios, como lo tentasteis en Masah. 
        17 Guardad cuidadosamente los mandamientos de Jehová vuestro Dios, y sus testimonios y sus estatutos que te ha mandado. 
        18 Y haz lo recto y bueno ante los ojos de Jehová, para que te vaya bien, y entres y poseas la buena tierra que Jehová juró a tus padres; 
        19 para que él arroje a tus enemigos de delante de ti, como Jehová ha dicho.
        20 Mañana cuando te preguntare tu hijo, diciendo: ¿Qué significan los testimonios y estatutos y decretos que Jehová nuestro Dios os mandó? 
        21 entonces dirás a tu hijo: Nosotros éramos siervos de Faraón en Egipto, y Jehová nos sacó de Egipto con mano poderosa. 
        22 Jehová hizo señales y milagros grandes y terribles en Egipto, sobre Faraón y sobre toda su casa, delante de nuestros ojos; 
        23 y nos sacó de allá, para traernos y darnos la tierra que juró a nuestros padres. 
        24 Y nos mandó Jehová que cumplamos todos estos estatutos, y que temamos a Jehová nuestro Dios, para que nos vaya bien todos los días, y para que nos conserve la vida, como hasta hoy. 
        25 Y tendremos justicia cuando cuidemos de poner por obra todos estos mandamientos delante de Jehová nuestro Dios, como él nos ha mandado.
""".trimIndent(),
        "Job 6" to """
        Job reprocha la actitud de sus amigos
        1 Respondió entonces Job, y dijo:
        2 ¡Oh, que pesasen justamente mi queja y mi tormento,
        Y se alzasen igualmente en balanza!
        3 Porque pesarían ahora más que la arena del mar;
        Por eso mis palabras han sido precipitadas.
        4 Porque las saetas del Todopoderoso están en mí,
        Cuyo veneno bebe mi espíritu;
        Y terrores de Dios me combaten.
        5 ¿Acaso gime el asno montés junto a la hierba?
        ¿Muge el buey junto a su pasto?
        6 ¿Se comerá lo desabrido sin sal?
        ¿Habrá gusto en la clara del huevo?
        7 Las cosas que mi alma no quería tocar,
        Son ahora mi alimento.
        8 ¡Quién me diera que viniese mi petición,
        Y que me otorgase Dios lo que anhelo,
        9 Y que agradara a Dios quebrantarme;
        Que soltara su mano, y acabara conmigo!
        10 Sería aún mi consuelo,
        Si me asaltase con dolor sin dar más tregua,
        Que yo no he escondido las palabras del Santo.
        11 ¿Cuál es mi fuerza para esperar aún?
        ¿Y cuál mi fin para que tenga aún paciencia?
        12 ¿Es mi fuerza la de las piedras,
        O es mi carne de bronce?
        13 ¿No es así que ni aun a mí mismo me puedo valer,
        Y que todo auxilio me ha faltado?
        14 El atribulado es consolado por su compañero;
        Aun aquel que abandona el temor del Omnipotente.
        15 Pero mis hermanos me traicionaron como un torrente;
        Pasan como corrientes impetuosas
        16 Que están escondidas por la helada,
        Y encubiertas por la nieve;
        17 Que al tiempo del calor son deshechas,
        Y al calentarse, desaparecen de su lugar;
        18 Se apartan de la senda de su rumbo,
        Van menguando, y se pierden.
        19 Miraron los caminantes de Temán,
        Los caminantes de Sabá esperaron en ellas;
        20 Pero fueron avergonzados por su esperanza;
        Porque vinieron hasta ellas, y se hallaron confusos.
        21 Ahora ciertamente como ellas sois vosotros;
        Pues habéis visto el tormento, y teméis.
        22 ¿Os he dicho yo: Traedme,
        Y pagad por mí de vuestra hacienda;
        23 Libradme de la mano del opresor,
        Y redimidme del poder de los violentos?
        24 Enseñadme, y yo callaré;
        Hacedme entender en qué he errado.
        25 ¡Cuán eficaces son las palabras rectas!
        Pero ¿qué reprende la censura vuestra?
        26 ¿Pensáis censurar palabras,
        Y los discursos de un desesperado, que son como el viento?
        27 También os arrojáis sobre el huérfano,
        Y caváis un hoyo para vuestro amigo.
        28 Ahora, pues, si queréis, miradme,
        Y ved si digo mentira delante de vosotros.
        29 Volved ahora, y no haya iniquidad;
        Volved aún a considerar mi justicia en esto.
        30 ¿Hay iniquidad en mi lengua?
        ¿Acaso no puede mi paladar discernir las cosas inicuas?
""".trimIndent(),
        "Mateo 27:32-66" to """
        Crucifixión y muerte de Jesús
        32 Cuando salían, hallaron a un hombre de Cirene que se llamaba Simón; a este obligaron a que llevase la cruz. 
        33 Y cuando llegaron a un lugar llamado Gólgota, que significa: Lugar de la Calavera, 
        34 le dieron a beber vinagre mezclado con hiel; pero después de haberlo probado, no quiso beberlo. 
        35 Cuando le hubieron crucificado, repartieron entre sí sus vestidos, echando suertes, para que se cumpliese lo dicho por el profeta: Partieron entre sí mis vestidos, y sobre mi ropa echaron suertes. 
        36 Y sentados le guardaban allí. 
        37 Y pusieron sobre su cabeza su causa escrita: ESTE ES JESÚS, EL REY DE LOS JUDÍOS. 
        38 Entonces crucificaron con él a dos ladrones, uno a la derecha, y otro a la izquierda. 
        39 Y los que pasaban le injuriaban, meneando la cabeza, 
        40 y diciendo: Tú que derribas el templo, y en tres días lo reedificas, sálvate a ti mismo; si eres Hijo de Dios, desciende de la cruz. 
        41 De esta manera también los principales sacerdotes, escarneciéndole con los escribas y los fariseos y los ancianos, decían: 
        42 A otros salvó, a sí mismo no se puede salvar; si es el Rey de Israel, descienda ahora de la cruz, y creeremos en él. 
        43 Confió en Dios; líbrele ahora si le quiere; porque ha dicho: Soy Hijo de Dios. 
        44 Lo mismo le injuriaban también los ladrones que estaban crucificados con él.
        45 Y desde la hora sexta hubo tinieblas sobre toda la tierra hasta la hora novena. 
        46 Cerca de la hora novena, Jesús clamó a gran voz, diciendo: Elí, Elí, ¿lama sabactani? Esto es: Dios mío, Dios mío, ¿por qué me has desamparado? 
        47 Algunos de los que estaban allí decían, al oírlo: A Elías llama este. 
        48 Y al instante, corriendo uno de ellos, tomó una esponja, y la empapó de vinagre, y poniéndola en una caña, le dio a beber. 
        49 Pero los otros decían: Deja, veamos si viene Elías a librarle. 
        50 Mas Jesús, habiendo otra vez clamado a gran voz, entregó el espíritu.
        51 Y he aquí, el velo del templo se rasgó en dos, de arriba abajo; y la tierra tembló, y las rocas se partieron; 
        52 y se abrieron los sepulcros, y muchos cuerpos de santos que habían dormido, se levantaron;
         53 y saliendo de los sepulcros, después de la resurrección de él, vinieron a la santa ciudad, y aparecieron a muchos. 
         54 El centurión, y los que estaban con él guardando a Jesús, visto el terremoto, y las cosas que habían sido hechas, temieron en gran manera, y dijeron: Verdaderamente este era Hijo de Dios.
        55 Estaban allí muchas mujeres mirando de lejos, las cuales habían seguido a Jesús desde Galilea, sirviéndole, 
        56 entre las cuales estaban María Magdalena, María la madre de Jacobo y de José, y la madre de los hijos de Zebedeo.
        Jesús es sepultado
        57 Cuando llegó la noche, vino un hombre rico de Arimatea, llamado José, que también había sido discípulo de Jesús. 
        58 Este fue a Pilato y pidió el cuerpo de Jesús. Entonces Pilato mandó que se le diese el cuerpo. 
        59 Y tomando José el cuerpo, lo envolvió en una sábana limpia, 
        60 y lo puso en su sepulcro nuevo, que había labrado en la peña; y después de hacer rodar una gran piedra a la entrada del sepulcro, se fue. 
        61 Y estaban allí María Magdalena, y la otra María, sentadas delante del sepulcro.
        La guardia ante la tumba
        62 Al día siguiente, que es después de la preparación, se reunieron los principales sacerdotes y los fariseos ante Pilato, 
        63 diciendo: Señor, nos acordamos que aquel engañador dijo, viviendo aún: Después de tres días resucitaré. 
        64 Manda, pues, que se asegure el sepulcro hasta el tercer día, no sea que vengan sus discípulos de noche, y lo hurten, y digan al pueblo: Resucitó de entre los muertos. Y será el postrer error peor que el primero. 
        65 Y Pilato les dijo: Ahí tenéis una guardia; id, aseguradlo como sabéis. 
        66 Entonces ellos fueron y aseguraron el sepulcro, sellando la piedra y poniendo la guardia.
""".trimIndent(),
        "Deuteronomio 7-8" to """
        Deuteronomio 7
        Advertencias contra la idolatría de Canaán
        1 Cuando Jehová tu Dios te haya introducido en la tierra en la cual entrarás para tomarla, y haya echado de delante de ti a muchas naciones, al heteo, al gergeseo, al amorreo, al cananeo, al ferezeo, al heveo y al jebuseo, siete naciones mayores y más poderosas que tú, 
        2 y Jehová tu Dios las haya entregado delante de ti, y las hayas derrotado, las destruirás del todo; no harás con ellas alianza, ni tendrás de ellas misericordia. 
        3 Y no emparentarás con ellas; no darás tu hija a su hijo, ni tomarás a su hija para tu hijo. 
        4 Porque desviará a tu hijo de en pos de mí, y servirán a dioses ajenos; y el furor de Jehová se encenderá sobre vosotros, y te destruirá pronto. 
        5 Mas así habéis de hacer con ellos: sus altares destruiréis, y quebraréis sus estatuas, y destruiréis sus imágenes de Asera, y quemaréis sus esculturas en el fuego.
        Un pueblo santo para Jehová
        6 Porque tú eres pueblo santo para Jehová tu Dios; Jehová tu Dios te ha escogido para serle un pueblo especial, más que todos los pueblos que están sobre la tierra. 
        7 No por ser vosotros más que todos los pueblos os ha querido Jehová y os ha escogido, pues vosotros erais el más insignificante de todos los pueblos; 
        8 sino por cuanto Jehová os amó, y quiso guardar el juramento que juró a vuestros padres, os ha sacado Jehová con mano poderosa, y os ha rescatado de servidumbre, de la mano de Faraón rey de Egipto. 
        9 Conoce, pues, que Jehová tu Dios es Dios, Dios fiel, que guarda el pacto y la misericordia a los que le aman y guardan sus mandamientos, hasta mil generaciones; 
        10 y que da el pago en persona al que le aborrece, destruyéndolo; y no se demora con el que le odia, en persona le dará el pago. 
        11 Guarda, por tanto, los mandamientos, estatutos y decretos que yo te mando hoy que cumplas.
        Bendiciones de la obediencia
        12 Y por haber oído estos decretos y haberlos guardado y puesto por obra, Jehová tu Dios guardará contigo el pacto y la misericordia que juró a tus padres. 
        13 Y te amará, te bendecirá y te multiplicará, y bendecirá el fruto de tu vientre y el fruto de tu tierra, tu grano, tu mosto, tu aceite, la cría de tus vacas, y los rebaños de tus ovejas, en la tierra que juró a tus padres que te daría. 
        14 Bendito serás más que todos los pueblos; no habrá en ti varón ni hembra estéril, ni en tus ganados. 
        15 Y quitará Jehová de ti toda enfermedad; y todas las malas plagas de Egipto, que tú conoces, no las pondrá sobre ti, antes las pondrá sobre todos los que te aborrecieren. 
        16 Y consumirás a todos los pueblos que te da Jehová tu Dios; no los perdonará tu ojo, ni servirás a sus dioses, porque te será tropiezo.
        17 Si dijeres en tu corazón: Estas naciones son mucho más numerosas que yo; ¿cómo las podré exterminar? 
        18 no tengas temor de ellas; acuérdate bien de lo que hizo Jehová tu Dios con Faraón y con todo Egipto; 
        19 de las grandes pruebas que vieron tus ojos, y de las señales y milagros, y de la mano poderosa y el brazo extendido con que Jehová tu Dios te sacó; así hará Jehová tu Dios con todos los pueblos de cuya presencia tú temieres. 
        20 También enviará Jehová tu Dios avispas sobre ellos, hasta que perezcan los que quedaren y los que se hubieren escondido de delante de ti. 
        21 No desmayes delante de ellos, porque Jehová tu Dios está en medio de ti, Dios grande y temible. 
        22 Y Jehová tu Dios echará a estas naciones de delante de ti poco a poco; no podrás acabar con ellas en seguida, para que las fieras del campo no se aumenten contra ti. 
        23 Mas Jehová tu Dios las entregará delante de ti, y él las quebrantará con grande destrozo, hasta que sean destruidas. 
        24 Él entregará sus reyes en tu mano, y tú destruirás el nombre de ellos de debajo del cielo; nadie te hará frente hasta que los destruyas. 
        25 Las esculturas de sus dioses quemarás en el fuego; no codiciarás plata ni oro de ellas para tomarlo para ti, para que no tropieces en ello, pues es abominación a Jehová tu Dios; 
        26 y no traerás cosa abominable a tu casa, para que no seas anatema; del todo la aborrecerás y la abominarás, porque es anatema.
        Deuteronomio 8
        La buena tierra que han de poseer
        1 Cuidaréis de poner por obra todo mandamiento que yo os ordeno hoy, para que viváis, y seáis multiplicados, y entréis y poseáis la tierra que Jehová prometió con juramento a vuestros padres. 
        2 Y te acordarás de todo el camino por donde te ha traído Jehová tu Dios estos cuarenta años en el desierto, para afligirte, para probarte, para saber lo que había en tu corazón, si habías de guardar o no sus mandamientos. 
        3 Y te afligió, y te hizo tener hambre, y te sustentó con maná, comida que no conocías tú, ni tus padres la habían conocido, para hacerte saber que no solo de pan vivirá el hombre, mas de todo lo que sale de la boca de Jehová vivirá el hombre. 
        4 Tu vestido nunca se envejeció sobre ti, ni el pie se te ha hinchado en estos cuarenta años. 
        5 Reconoce asimismo en tu corazón, que como castiga el hombre a su hijo, así Jehová tu Dios te castiga. 
        6 Guardarás, pues, los mandamientos de Jehová tu Dios, andando en sus caminos, y temiéndole. 
        7 Porque Jehová tu Dios te introduce en la buena tierra, tierra de arroyos, de aguas, de fuentes y de manantiales, que brotan en vegas y montes; 
        8 tierra de trigo y cebada, de vides, higueras y granados; tierra de olivos, de aceite y de miel; 
        9 tierra en la cual no comerás el pan con escasez, ni te faltará nada en ella; tierra cuyas piedras son hierro, y de cuyos montes sacarás cobre. 
        10 Y comerás y te saciarás, y bendecirás a Jehová tu Dios por la buena tierra que te habrá dado.
        Amonestación de no olvidar a Dios
        11 Cuídate de no olvidarte de Jehová tu Dios, para cumplir sus mandamientos, sus decretos y sus estatutos que yo te ordeno hoy; 
        12 no suceda que comas y te sacies, y edifiques buenas casas en que habites, 
        13 y tus vacas y tus ovejas se aumenten, y la plata y el oro se te multipliquen, y todo lo que tuvieres se aumente; 
        14 y se enorgullezca tu corazón, y te olvides de Jehová tu Dios, que te sacó de tierra de Egipto, de casa de servidumbre; 
        15 que te hizo caminar por un desierto grande y espantoso, lleno de serpientes ardientes, y de escorpiones, y de sed, donde no había agua, y él te sacó agua de la roca del pedernal; 
        16 que te sustentó con maná en el desierto, comida que tus padres no habían conocido, afligiéndote y probándote, para a la postre hacerte bien; 
        17 y digas en tu corazón: Mi poder y la fuerza de mi mano me han traído esta riqueza. 
        18 Sino acuérdate de Jehová tu Dios, porque él te da el poder para hacer las riquezas, a fin de confirmar su pacto que juró a tus padres, como en este día. 
        19 Mas si llegares a olvidarte de Jehová tu Dios y anduvieres en pos de dioses ajenos, y les sirvieres y a ellos te inclinares, yo lo afirmo hoy contra vosotros, que de cierto pereceréis. 
        20 Como las naciones que Jehová destruirá delante de vosotros, así pereceréis, por cuanto no habréis atendido a la voz de Jehová vuestro Dios.
""".trimIndent(),
        "Job 7" to """
        Job argumenta contra Dios
        1 ¿No es acaso brega la vida del hombre sobre la tierra,
        Y sus días como los días del jornalero?
        2 Como el siervo suspira por la sombra,
        Y como el jornalero espera el reposo de su trabajo,
        3 Así he recibido meses de calamidad,
        Y noches de trabajo me dieron por cuenta.
        4 Cuando estoy acostado, digo: ¿Cuándo me levantaré?
        Mas la noche es larga, y estoy lleno de inquietudes hasta el alba.
        5 Mi carne está vestida de gusanos, y de costras de polvo;
        Mi piel hendida y abominable.
        6 Y mis días fueron más veloces que la lanzadera del tejedor,
        Y fenecieron sin esperanza.
        7 Acuérdate que mi vida es un soplo,
        Y que mis ojos no volverán a ver el bien.
        8 Los ojos de los que me ven, no me verán más;
        Fijarás en mí tus ojos, y dejaré de ser.
        9 Como la nube se desvanece y se va,
        Así el que desciende al Seol no subirá;
        10 No volverá más a su casa,
        Ni su lugar le conocerá más.
        11 Por tanto, no refrenaré mi boca;
        Hablaré en la angustia de mi espíritu,
        Y me quejaré con la amargura de mi alma.
        12 ¿Soy yo el mar, o un monstruo marino,
        Para que me pongas guarda?
        13 Cuando digo: Me consolará mi lecho,
        Mi cama atenuará mis quejas;
        14 Entonces me asustas con sueños,
        Y me aterras con visiones.
        15 Y así mi alma tuvo por mejor la estrangulación,
        Y quiso la muerte más que mis huesos.
        16 Abomino de mi vida; no he de vivir para siempre;
        Déjame, pues, porque mis días son vanidad.
        17 ¿Qué es el hombre, para que lo engrandezcas,
        Y para que pongas sobre él tu corazón,
        18 Y lo visites todas las mañanas,
        Y todos los momentos lo pruebes?
        19 ¿Hasta cuándo no apartarás de mí tu mirada,
        Y no me soltarás siquiera hasta que trague mi saliva?
        20 Si he pecado, ¿qué puedo hacerte a ti, oh Guarda de los hombres?
        ¿Por qué me pones por blanco tuyo,
        Hasta convertirme en una carga para mí mismo?
        21 ¿Y por qué no quitas mi rebelión, y perdonas mi iniquidad?
        Porque ahora dormiré en el polvo,
        Y si me buscares de mañana, ya no existiré.
""".trimIndent(),
        "Mateo 28" to """
        La resurrección
        1 Pasado el día de reposo,[a] al amanecer del primer día de la semana, vinieron María Magdalena y la otra María, a ver el sepulcro. 
        2 Y hubo un gran terremoto; porque un ángel del Señor, descendiendo del cielo y llegando, removió la piedra, y se sentó sobre ella. 
        3 Su aspecto era como un relámpago, y su vestido blanco como la nieve. 
        4 Y de miedo de él los guardas temblaron y se quedaron como muertos. 
        5 Mas el ángel, respondiendo, dijo a las mujeres: No temáis vosotras; porque yo sé que buscáis a Jesús, el que fue crucificado. 
        6 No está aquí, pues ha resucitado, como dijo. Venid, ved el lugar donde fue puesto el Señor. 
        7 E id pronto y decid a sus discípulos que ha resucitado de los muertos, y he aquí va delante de vosotros a Galilea; allí le veréis. He aquí, os lo he dicho. 
        8 Entonces ellas, saliendo del sepulcro con temor y gran gozo, fueron corriendo a dar las nuevas a sus discípulos. Y mientras iban a dar las nuevas a los discípulos, 
        9 he aquí, Jesús les salió al encuentro, diciendo: ¡Salve! Y ellas, acercándose, abrazaron sus pies, y le adoraron. 
        10 Entonces Jesús les dijo: No temáis; id, dad las nuevas a mis hermanos, para que vayan a Galilea, y allí me verán.
        El informe de la guardia
        11 Mientras ellas iban, he aquí unos de la guardia fueron a la ciudad, y dieron aviso a los principales sacerdotes de todas las cosas que habían acontecido. 
        12 Y reunidos con los ancianos, y habido consejo, dieron mucho dinero a los soldados, 
        13 diciendo: Decid vosotros: Sus discípulos vinieron de noche, y lo hurtaron, estando nosotros dormidos. 
        14 Y si esto lo oyere el gobernador, nosotros le persuadiremos, y os pondremos a salvo. 
        15 Y ellos, tomando el dinero, hicieron como se les había instruido. Este dicho se ha divulgado entre los judíos hasta el día de hoy.
        La gran comisión
        16 Pero los once discípulos se fueron a Galilea, al monte donde Jesús les había ordenado. 
        17 Y cuando le vieron, le adoraron; pero algunos dudaban. 
        18 Y Jesús se acercó y les habló diciendo: Toda potestad me es dada en el cielo y en la tierra. 
        19 Por tanto, id, y haced discípulos a todas las naciones, bautizándolos en el nombre del Padre, y del Hijo, y del Espíritu Santo; 
        20 enseñándoles que guarden todas las cosas que os he mandado; y he aquí yo estoy con vosotros todos los días, hasta el fin del mundo. Amén.
""".trimIndent(),
        "Deuteronomio 9-10" to """
        Deuteronomio 9
        Dios destruirá a las naciones de Canaán
        1 Oye, Israel: tú vas hoy a pasar el Jordán, para entrar a desposeer a naciones más numerosas y más poderosas que tú, ciudades grandes y amuralladas hasta el cielo; 
        2 un pueblo grande y alto, hijos de los anaceos, de los cuales tienes tú conocimiento, y has oído decir: ¿Quién se sostendrá delante de los hijos de Anac? 
        3 Entiende, pues, hoy, que es Jehová tu Dios el que pasa delante de ti como fuego consumidor, que los destruirá y humillará delante de ti; y tú los echarás, y los destruirás en seguida, como Jehová te ha dicho.
        4 No pienses en tu corazón cuando Jehová tu Dios los haya echado de delante de ti, diciendo: Por mi justicia me ha traído Jehová a poseer esta tierra; pues por la impiedad de estas naciones Jehová las arroja de delante de ti. 
        5 No por tu justicia, ni por la rectitud de tu corazón entras a poseer la tierra de ellos, sino por la impiedad de estas naciones Jehová tu Dios las arroja de delante de ti, y para confirmar la palabra que Jehová juró a tus padres Abraham, Isaac y Jacob.
        La rebelión de Israel en Horeb
        6 Por tanto, sabe que no es por tu justicia que Jehová tu Dios te da esta buena tierra para tomarla; porque pueblo duro de cerviz eres tú. 
        7 Acuérdate, no olvides que has provocado la ira de Jehová tu Dios en el desierto; desde el día que saliste de la tierra de Egipto, hasta que entrasteis en este lugar, habéis sido rebeldes a Jehová. 
        8 En Horeb provocasteis a ira a Jehová, y se enojó Jehová contra vosotros para destruiros. 
        9 Cuando yo subí al monte para recibir las tablas de piedra, las tablas del pacto que Jehová hizo con vosotros, estuve entonces en el monte cuarenta días y cuarenta noches, sin comer pan ni beber agua; 
        10 y me dio Jehová las dos tablas de piedra escritas con el dedo de Dios; y en ellas estaba escrito según todas las palabras que os habló Jehová en el monte, de en medio del fuego, el día de la asamblea. 
        11 Sucedió al fin de los cuarenta días y cuarenta noches, que Jehová me dio las dos tablas de piedra, las tablas del pacto. 
        12 Y me dijo Jehová: Levántate, desciende pronto de aquí, porque tu pueblo que sacaste de Egipto se ha corrompido; pronto se han apartado del camino que yo les mandé; se han hecho una imagen de fundición.
        13 Y me habló Jehová, diciendo: He observado a ese pueblo, y he aquí que es pueblo duro de cerviz. 
        14 Déjame que los destruya, y borre su nombre de debajo del cielo, y yo te pondré sobre una nación fuerte y mucho más numerosa que ellos. 
        15 Y volví y descendí del monte, el cual ardía en fuego, con las tablas del pacto en mis dos manos. 
        16 Y miré, y he aquí habíais pecado contra Jehová vuestro Dios; os habíais hecho un becerro de fundición, apartándoos pronto del camino que Jehová os había mandado. 
        17 Entonces tomé las dos tablas y las arrojé de mis dos manos, y las quebré delante de vuestros ojos. 
        18 Y me postré delante de Jehová como antes, cuarenta días y cuarenta noches; no comí pan ni bebí agua, a causa de todo vuestro pecado que habíais cometido haciendo el mal ante los ojos de Jehová para enojarlo. 
        19 Porque temí a causa del furor y de la ira con que Jehová estaba enojado contra vosotros para destruiros. Pero Jehová me escuchó aun esta vez. 
        20 Contra Aarón también se enojó Jehová en gran manera para destruirlo; y también oré por Aarón en aquel entonces. 
        21 Y tomé el objeto de vuestro pecado, el becerro que habíais hecho, y lo quemé en el fuego, y lo desmenucé moliéndolo muy bien, hasta que fue reducido a polvo; y eché el polvo de él en el arroyo que descendía del monte.
        22 También en Tabera, en Masah y en Kibrot-hataava provocasteis a ira a Jehová. 
        23 Y cuando Jehová os envió desde Cades-barnea, diciendo: Subid y poseed la tierra que yo os he dado, también fuisteis rebeldes al mandato de Jehová vuestro Dios, y no le creísteis, ni obedecisteis a su voz. 
        24 Rebeldes habéis sido a Jehová desde el día que yo os conozco.
        25 Me postré, pues, delante de Jehová; cuarenta días y cuarenta noches estuve postrado, porque Jehová dijo que os había de destruir. 
        26 Y oré a Jehová, diciendo: Oh Señor Jehová, no destruyas a tu pueblo y a tu heredad que has redimido con tu grandeza, que sacaste de Egipto con mano poderosa. 
        27 Acuérdate de tus siervos Abraham, Isaac y Jacob; no mires a la dureza de este pueblo, ni a su impiedad ni a su pecado, 
        28 no sea que digan los de la tierra de donde nos sacaste: Por cuanto no pudo Jehová introducirlos en la tierra que les había prometido, o porque los aborrecía, los sacó para matarlos en el desierto. 
        29 Y ellos son tu pueblo y tu heredad, que sacaste con tu gran poder y con tu brazo extendido.
        Deuteronomio 10
        El pacto renovado
        1 En aquel tiempo Jehová me dijo: Lábrate dos tablas de piedra como las primeras, y sube a mí al monte, y hazte un arca de madera; 
        2 y escribiré en aquellas tablas las palabras que estaban en las primeras tablas que quebraste; y las pondrás en el arca. 
        3 E hice un arca de madera de acacia, y labré dos tablas de piedra como las primeras, y subí al monte con las dos tablas en mi mano. 
        4 Y escribió en las tablas conforme a la primera escritura, los diez mandamientos que Jehová os había hablado en el monte de en medio del fuego, el día de la asamblea; y me las dio Jehová. 
        5 Y volví y descendí del monte, y puse las tablas en el arca que había hecho; y allí están, como Jehová me mandó. 
        6 (Después salieron los hijos de Israel de Beerot-bene-jaacán[a] a Mosera; allí murió Aarón, y allí fue sepultado, y en lugar suyo tuvo el sacerdocio su hijo Eleazar. 
        7 De allí partieron a Gudgoda, y de Gudgoda a Jotbata, tierra de arroyos de aguas. 
        8 En aquel tiempo apartó Jehová la tribu de Leví para que llevase el arca del pacto de Jehová, para que estuviese delante de Jehová para servirle, y para bendecir en su nombre, hasta hoy, 
        9 por lo cual Leví no tuvo parte ni heredad con sus hermanos; Jehová es su heredad, como Jehová tu Dios le dijo.)
        10 Y yo estuve en el monte como los primeros días, cuarenta días y cuarenta noches; y Jehová también me escuchó esta vez, y no quiso Jehová destruirte. 
        11 Y me dijo Jehová: Levántate, anda, para que marches delante del pueblo, para que entren y posean la tierra que juré a sus padres que les había de dar.
        Lo que Dios exige
        12 Ahora, pues, Israel, ¿qué pide Jehová tu Dios de ti, sino que temas a Jehová tu Dios, que andes en todos sus caminos, y que lo ames, y sirvas a Jehová tu Dios con todo tu corazón y con toda tu alma; 
        13 que guardes los mandamientos de Jehová y sus estatutos, que yo te prescribo hoy, para que tengas prosperidad? 
        14 He aquí, de Jehová tu Dios son los cielos, y los cielos de los cielos, la tierra, y todas las cosas que hay en ella. 
        15 Solamente de tus padres se agradó Jehová para amarlos, y escogió su descendencia después de ellos, a vosotros, de entre todos los pueblos, como en este día. 
        16 Circuncidad, pues, el prepucio de vuestro corazón, y no endurezcáis más vuestra cerviz. 
        17 Porque Jehová vuestro Dios es Dios de dioses y Señor de señores, Dios grande, poderoso y temible, que no hace acepción de personas, ni toma cohecho; 
        18 que hace justicia al huérfano y a la viuda; que ama también al extranjero dándole pan y vestido. 
        19 Amaréis, pues, al extranjero; porque extranjeros fuisteis en la tierra de Egipto. 
        20 A Jehová tu Dios temerás, a él solo servirás, a él seguirás, y por su nombre jurarás. 
        21 Él es el objeto de tu alabanza, y él es tu Dios, que ha hecho contigo estas cosas grandes y terribles que tus ojos han visto. 
        22 Con setenta personas descendieron tus padres a Egipto, y ahora Jehová te ha hecho como las estrellas del cielo en multitud.
""".trimIndent(),
        "Job 8" to """
        Bildad proclama la justicia de Dios
        1 Respondió Bildad suhita, y dijo:
        2 ¿Hasta cuándo hablarás tales cosas,
        Y las palabras de tu boca serán como viento impetuoso?
        3 ¿Acaso torcerá Dios el derecho,
        O pervertirá el Todopoderoso la justicia?
        4 Si tus hijos pecaron contra él,
        Él los echó en el lugar de su pecado.
        5 Si tú de mañana buscares a Dios,
        Y rogares al Todopoderoso;
        6 Si fueres limpio y recto,
        Ciertamente luego se despertará por ti,
        Y hará próspera la morada de tu justicia.
        7 Y aunque tu principio haya sido pequeño,
        Tu postrer estado será muy grande.
        8 Porque pregunta ahora a las generaciones pasadas,
        Y disponte para inquirir a los padres de ellas;
        9 Pues nosotros somos de ayer, y nada sabemos,
        Siendo nuestros días sobre la tierra como sombra.
        10 ¿No te enseñarán ellos, te hablarán,
        Y de su corazón sacarán palabras?
        11 ¿Crece el junco sin lodo?
        ¿Crece el prado sin agua?
        12 Aun en su verdor, y sin haber sido cortado,
        Con todo, se seca primero que toda hierba.
        13 Tales son los caminos de todos los que olvidan a Dios;
        Y la esperanza del impío perecerá;
        14 Porque su esperanza será cortada,
        Y su confianza es tela de araña.
        15 Se apoyará él en su casa, mas no permanecerá ella en pie;
        Se asirá de ella, mas no resistirá.
        16 A manera de un árbol está verde delante del sol,
        Y sus renuevos salen sobre su huerto;
        17 Se van entretejiendo sus raíces junto a una fuente,
        Y enlazándose hasta un lugar pedregoso.
        18 Si le arrancaren de su lugar,
        Este le negará entonces, diciendo: Nunca te vi.
        19 Ciertamente este será el gozo de su camino;
        Y del polvo mismo nacerán otros.
        20 He aquí, Dios no aborrece al perfecto,
        Ni apoya la mano de los malignos.
        21 Aún llenará tu boca de risa,
        Y tus labios de júbilo.
        22 Los que te aborrecen serán vestidos de confusión;
        Y la habitación de los impíos perecerá.
""".trimIndent(),
        "Hechos 1" to """
        La promesa del Espíritu Santo
        1 En el primer tratado, oh Teófilo, hablé acerca de todas las cosas que Jesús comenzó a hacer y a enseñar, 
        2 hasta el día en que fue recibido arriba, después de haber dado mandamientos por el Espíritu Santo a los apóstoles que había escogido; 
        3 a quienes también, después de haber padecido, se presentó vivo con muchas pruebas indubitables, apareciéndoseles durante cuarenta días y hablándoles acerca del reino de Dios. 
        4 Y estando juntos, les mandó que no se fueran de Jerusalén, sino que esperasen la promesa del Padre, la cual, les dijo, oísteis de mí. 
        5 Porque Juan ciertamente bautizó con agua, mas vosotros seréis bautizados con el Espíritu Santo dentro de no muchos días.
        La ascensión
        6 Entonces los que se habían reunido le preguntaron, diciendo: Señor, ¿restaurarás el reino a Israel en este tiempo? 
        7 Y les dijo: No os toca a vosotros saber los tiempos o las sazones, que el Padre puso en su sola potestad; 
        8 pero recibiréis poder, cuando haya venido sobre vosotros el Espíritu Santo, y me seréis testigos en Jerusalén, en toda Judea, en Samaria, y hasta lo último de la tierra. 
        9 Y habiendo dicho estas cosas, viéndolo ellos, fue alzado, y le recibió una nube que le ocultó de sus ojos. 
        10 Y estando ellos con los ojos puestos en el cielo, entre tanto que él se iba, he aquí se pusieron junto a ellos dos varones con vestiduras blancas, 
        11 los cuales también les dijeron: Varones galileos, ¿por qué estáis mirando al cielo? Este mismo Jesús, que ha sido tomado de vosotros al cielo, así vendrá como le habéis visto ir al cielo.
        Elección del sucesor de Judas
        12 Entonces volvieron a Jerusalén desde el monte que se llama del Olivar, el cual está cerca de Jerusalén, camino de un día de reposo.[a] 
        13 Y entrados, subieron al aposento alto, donde moraban Pedro y Jacobo, Juan, Andrés, Felipe, Tomás, Bartolomé, Mateo, Jacobo hijo de Alfeo, Simón el Zelote y Judas hermano de Jacobo. 
        14 Todos estos perseveraban unánimes en oración y ruego, con las mujeres, y con María la madre de Jesús, y con sus hermanos.
        15 En aquellos días Pedro se levantó en medio de los hermanos (y los reunidos eran como ciento veinte en número), y dijo: 
        16 Varones hermanos, era necesario que se cumpliese la Escritura en que el Espíritu Santo habló antes por boca de David acerca de Judas, que fue guía de los que prendieron a Jesús, 
        17 y era contado con nosotros, y tenía parte en este ministerio. 
        18 Este, pues, con el salario de su iniquidad adquirió un campo, y cayendo de cabeza, se reventó por la mitad, y todas sus entrañas se derramaron. 
        19 Y fue notorio a todos los habitantes de Jerusalén, de tal manera que aquel campo se llama en su propia lengua, Acéldama, que quiere decir, Campo de sangre. 
        20 Porque está escrito en el libro de los Salmos:
        Sea hecha desierta su habitación,
        Y no haya quien more en ella;
        y: Tome otro su oficio.
        21 Es necesario, pues, que de estos hombres que han estado juntos con nosotros todo el tiempo que el Señor Jesús entraba y salía entre nosotros, 
        22 comenzando desde el bautismo de Juan hasta el día en que de entre nosotros fue recibido arriba, uno sea hecho testigo con nosotros, de su resurrección. 
        23 Y señalaron a dos: a José, llamado Barsabás, que tenía por sobrenombre Justo, y a Matías. 
        24 Y orando, dijeron: Tú, Señor, que conoces los corazones de todos, muestra cuál de estos dos has escogido, 
        25 para que tome la parte de este ministerio y apostolado, de que cayó Judas por transgresión, para irse a su propio lugar. 
        26 Y les echaron suertes, y la suerte cayó sobre Matías; y fue contado con los once apóstoles.
""".trimIndent(),
        "Deuteronomio 11-12" to """
        Deuteronomio 11
        La grandeza de Jehová
        1 Amarás, pues, a Jehová tu Dios, y guardarás sus ordenanzas, sus estatutos, sus decretos y sus mandamientos, todos los días. 
        2 Y comprended hoy, porque no hablo con vuestros hijos que no han sabido ni visto el castigo de Jehová vuestro Dios, su grandeza, su mano poderosa, y su brazo extendido, 
        3 y sus señales, y sus obras que hizo en medio de Egipto a Faraón rey de Egipto, y a toda su tierra; 
        4 y lo que hizo al ejército de Egipto, a sus caballos y a sus carros; cómo precipitó las aguas del Mar Rojo sobre ellos, cuando venían tras vosotros, y Jehová los destruyó hasta hoy; 
        5 y lo que ha hecho con vosotros en el desierto, hasta que habéis llegado a este lugar; 
        6 y lo que hizo con Datán y Abiram, hijos de Eliab hijo de Rubén; cómo abrió su boca la tierra, y los tragó con sus familias, sus tiendas, y todo su ganado, en medio de todo Israel. 
        7 Mas vuestros ojos han visto todas las grandes obras que Jehová ha hecho.
        Bendiciones de la Tierra Prometida
        8 Guardad, pues, todos los mandamientos que yo os prescribo hoy, para que seáis fortalecidos, y entréis y poseáis la tierra a la cual pasáis para tomarla; 
        9 y para que os sean prolongados los días sobre la tierra, de la cual juró Jehová a vuestros padres, que había de darla a ellos y a su descendencia, tierra que fluye leche y miel. 
        10 La tierra a la cual entras para tomarla no es como la tierra de Egipto de donde habéis salido, donde sembrabas tu semilla, y regabas con tu pie, como huerto de hortaliza. 
        11 La tierra a la cual pasáis para tomarla es tierra de montes y de vegas, que bebe las aguas de la lluvia del cielo; 
        12 tierra de la cual Jehová tu Dios cuida; siempre están sobre ella los ojos de Jehová tu Dios, desde el principio del año hasta el fin.
        13 Si obedeciereis cuidadosamente a mis mandamientos que yo os prescribo hoy, amando a Jehová vuestro Dios, y sirviéndole con todo vuestro corazón, y con toda vuestra alma, 
        14 yo daré la lluvia de vuestra tierra a su tiempo, la temprana y la tardía; y recogerás tu grano, tu vino y tu aceite. 
        15 Daré también hierba en tu campo para tus ganados; y comerás, y te saciarás. 
        16 Guardaos, pues, que vuestro corazón no se infatúe, y os apartéis y sirváis a dioses ajenos, y os inclinéis a ellos; 
        17 y se encienda el furor de Jehová sobre vosotros, y cierre los cielos, y no haya lluvia, ni la tierra dé su fruto, y perezcáis pronto de la buena tierra que os da Jehová.
        18 Por tanto, pondréis estas mis palabras en vuestro corazón y en vuestra alma, y las ataréis como señal en vuestra mano, y serán por frontales entre vuestros ojos. 
        19 Y las enseñaréis a vuestros hijos, hablando de ellas cuando te sientes en tu casa, cuando andes por el camino, cuando te acuestes, y cuando te levantes, 
        20 y las escribirás en los postes de tu casa, y en tus puertas; 
        21 para que sean vuestros días, y los días de vuestros hijos, tan numerosos sobre la tierra que Jehová juró a vuestros padres que les había de dar, como los días de los cielos sobre la tierra. 
        22 Porque si guardareis cuidadosamente todos estos mandamientos que yo os prescribo para que los cumpláis, y si amareis a Jehová vuestro Dios, andando en todos sus caminos, y siguiéndole a él, 
        23 Jehová también echará de delante de vosotros a todas estas naciones, y desposeeréis naciones grandes y más poderosas que vosotros. 
        24 Todo lugar que pisare la planta de vuestro pie será vuestro; desde el desierto hasta el Líbano, desde el río Éufrates hasta el mar occidental será vuestro territorio. 
        25 Nadie se sostendrá delante de vosotros; miedo y temor de vosotros pondrá Jehová vuestro Dios sobre toda la tierra que pisareis, como él os ha dicho.
        26 He aquí yo pongo hoy delante de vosotros la bendición y la maldición: 
        27 la bendición, si oyereis los mandamientos de Jehová vuestro Dios, que yo os prescribo hoy, 
        28 y la maldición, si no oyereis los mandamientos de Jehová vuestro Dios, y os apartareis del camino que yo os ordeno hoy, para ir en pos de dioses ajenos que no habéis conocido. 
        29 Y cuando Jehová tu Dios te haya introducido en la tierra a la cual vas para tomarla, pondrás la bendición sobre el monte Gerizim, y la maldición sobre el monte Ebal, 
        30 los cuales están al otro lado del Jordán, tras el camino del occidente en la tierra del cananeo, que habita en el Arabá frente a Gilgal, junto al encinar de More. 
        31 Porque vosotros pasáis el Jordán para ir a poseer la tierra que os da Jehová vuestro Dios; y la tomaréis, y habitaréis en ella. 
        32 Cuidaréis, pues, de cumplir todos los estatutos y decretos que yo presento hoy delante de vosotros.
        Deuteronomio 12
        El santuario único
        1 Estos son los estatutos y decretos que cuidaréis de poner por obra en la tierra que Jehová el Dios de tus padres te ha dado para que tomes posesión de ella, todos los días que vosotros viviereis sobre la tierra. 
        2 Destruiréis enteramente todos los lugares donde las naciones que vosotros heredaréis sirvieron a sus dioses, sobre los montes altos, y sobre los collados, y debajo de todo árbol frondoso. 
        3 Derribaréis sus altares, y quebraréis sus estatuas, y sus imágenes de Asera consumiréis con fuego; y destruiréis las esculturas de sus dioses, y raeréis su nombre de aquel lugar. 
        4 No haréis así a Jehová vuestro Dios, 
        5 sino que el lugar que Jehová vuestro Dios escogiere de entre todas vuestras tribus, para poner allí su nombre para su habitación, ese buscaréis, y allá iréis. 
        6 Y allí llevaréis vuestros holocaustos, vuestros sacrificios, vuestros diezmos, y la ofrenda elevada de vuestras manos, vuestros votos, vuestras ofrendas voluntarias, y las primicias de vuestras vacas y de vuestras ovejas; 
        7 y comeréis allí delante de Jehová vuestro Dios, y os alegraréis, vosotros y vuestras familias, en toda obra de vuestras manos en la cual Jehová tu Dios te hubiere bendecido. 
        8 No haréis como todo lo que hacemos nosotros aquí ahora, cada uno lo que bien le parece, 
        9 porque hasta ahora no habéis entrado al reposo y a la heredad que os da Jehová vuestro Dios. 
        10 Mas pasaréis el Jordán, y habitaréis en la tierra que Jehová vuestro Dios os hace heredar; y él os dará reposo de todos vuestros enemigos alrededor, y habitaréis seguros. 
        11 Y al lugar que Jehová vuestro Dios escogiere para poner en él su nombre, allí llevaréis todas las cosas que yo os mando: vuestros holocaustos, vuestros sacrificios, vuestros diezmos, las ofrendas elevadas de vuestras manos, y todo lo escogido de los votos que hubiereis prometido a Jehová. 
        12 Y os alegraréis delante de Jehová vuestro Dios, vosotros, vuestros hijos, vuestras hijas, vuestros siervos y vuestras siervas, y el levita que habite en vuestras poblaciones; por cuanto no tiene parte ni heredad con vosotros. 
        13 Cuídate de no ofrecer tus holocaustos en cualquier lugar que vieres; 
        14 sino que en el lugar que Jehová escogiere, en una de tus tribus, allí ofrecerás tus holocaustos, y allí harás todo lo que yo te mando.
        15 Con todo, podrás matar y comer carne en todas tus poblaciones conforme a tu deseo, según la bendición que Jehová tu Dios te haya dado; el inmundo y el limpio la podrá comer, como la de gacela o de ciervo. 
        16 Solamente que sangre no comeréis; sobre la tierra la derramaréis como agua. 
        17 Ni comerás en tus poblaciones el diezmo de tu grano, de tu vino o de tu aceite, ni las primicias de tus vacas, ni de tus ovejas, ni los votos que prometieres, ni las ofrendas voluntarias, ni las ofrendas elevadas de tus manos; 
        18 sino que delante de Jehová tu Dios las comerás, en el lugar que Jehová tu Dios hubiere escogido, tú, tu hijo, tu hija, tu siervo, tu sierva, y el levita que habita en tus poblaciones; te alegrarás delante de Jehová tu Dios de toda la obra de tus manos. 
        19 Ten cuidado de no desamparar al levita en todos tus días sobre la tierra.
        20 Cuando Jehová tu Dios ensanchare tu territorio, como él te ha dicho, y tú dijeres: Comeré carne, porque deseaste comerla, conforme a lo que deseaste podrás comer. 
        21 Si estuviere lejos de ti el lugar que Jehová tu Dios escogiere para poner allí su nombre, podrás matar de tus vacas y de tus ovejas que Jehová te hubiere dado, como te he mandado yo, y comerás en tus puertas según todo lo que deseares. 
        22 Lo mismo que se come la gacela y el ciervo, así las podrás comer; el inmundo y el limpio podrán comer también de ellas. 
        23 Solamente que te mantengas firme en no comer sangre; porque la sangre es la vida, y no comerás la vida juntamente con su carne. 
        24 No la comerás; en tierra la derramarás como agua. 
        25 No comerás de ella, para que te vaya bien a ti y a tus hijos después de ti, cuando hicieres lo recto ante los ojos de Jehová. 
        26 Pero las cosas que hubieres consagrado, y tus votos, las tomarás, y vendrás con ellas al lugar que Jehová hubiere escogido; 
        27 y ofrecerás tus holocaustos, la carne y la sangre, sobre el altar de Jehová tu Dios; y la sangre de tus sacrificios será derramada sobre el altar de Jehová tu Dios, y podrás comer la carne. 
        28 Guarda y escucha todas estas palabras que yo te mando, para que haciendo lo bueno y lo recto ante los ojos de Jehová tu Dios, te vaya bien a ti y a tus hijos después de ti para siempre.
        Advertencias contra la idolatría
        29 Cuando Jehová tu Dios haya destruido delante de ti las naciones adonde tú vas para poseerlas, y las heredes, y habites en su tierra, 
        30 guárdate que no tropieces yendo en pos de ellas, después que sean destruidas delante de ti; no preguntes acerca de sus dioses, diciendo: De la manera que servían aquellas naciones a sus dioses, yo también les serviré. 
        31 No harás así a Jehová tu Dios; porque toda cosa abominable que Jehová aborrece, hicieron ellos a sus dioses; pues aun a sus hijos y a sus hijas quemaban en el fuego a sus dioses.
        32 Cuidarás de hacer todo lo que yo te mando; no añadirás a ello, ni de ello quitarás.
""".trimIndent(),
        "Job 9" to """
        Incapacidad de Job para responder a Dios
        1 Respondió Job, y dijo:
        2 Ciertamente yo sé que es así;
        ¿Y cómo se justificará el hombre con Dios?
        3 Si quisiere contender con él,
        No le podrá responder a una cosa entre mil.
        4 Él es sabio de corazón, y poderoso en fuerzas;
        ¿Quién se endureció contra él, y le fue bien?
        5 Él arranca los montes con su furor,
        Y no saben quién los trastornó;
        6 Él remueve la tierra de su lugar,
        Y hace temblar sus columnas;
        7 Él manda al sol, y no sale;
        Y sella las estrellas;
        8 Él solo extendió los cielos,
        Y anda sobre las olas del mar;
        9 Él hizo la Osa, el Orión y las Pléyades,
        Y los lugares secretos del sur;
        10 Él hace cosas grandes e incomprensibles,
        Y maravillosas, sin número.
        11 He aquí que él pasará delante de mí, y yo no lo veré;
        Pasará, y no lo entenderé.
        12 He aquí, arrebatará; ¿quién le hará restituir?
        ¿Quién le dirá: Qué haces?
        13 Dios no volverá atrás su ira,
        Y debajo de él se abaten los que ayudan a los soberbios.
        14 ¿Cuánto menos le responderé yo,
        Y hablaré con él palabras escogidas?
        15 Aunque fuese yo justo, no respondería;
        Antes habría de rogar a mi juez.
        16 Si yo le invocara, y él me respondiese,
        Aún no creeré que haya escuchado mi voz.
        17 Porque me ha quebrantado con tempestad,
        Y ha aumentado mis heridas sin causa.
        18 No me ha concedido que tome aliento,
        Sino que me ha llenado de amarguras.
        19 Si habláremos de su potencia, por cierto es fuerte;
        Si de juicio, ¿quién me emplazará?
        20 Si yo me justificare, me condenaría mi boca;
        Si me dijere perfecto, esto me haría inicuo.
        21 Si fuese íntegro, no haría caso de mí mismo;
        Despreciaría mi vida.
        22 Una cosa resta que yo diga:
        Al perfecto y al impío él los consume.
        23 Si azote mata de repente,
        Se ríe del sufrimiento de los inocentes.
        24 La tierra es entregada en manos de los impíos,
        Y él cubre el rostro de sus jueces.
        Si no es él, ¿quién es? ¿Dónde está?
        25 Mis días han sido más ligeros que un correo;
        Huyeron, y no vieron el bien.
        26 Pasaron cual naves veloces;
        Como el águila que se arroja sobre la presa.
        27 Si yo dijere: Olvidaré mi queja,
        Dejaré mi triste semblante, y me esforzaré,
        28 Me turban todos mis dolores;
        Sé que no me tendrás por inocente.
        29 Yo soy impío;
        ¿Para qué trabajaré en vano?
        30 Aunque me lave con aguas de nieve,
        Y limpie mis manos con la limpieza misma,
        31 Aún me hundirás en el hoyo,
        Y mis propios vestidos me abominarán.
        32 Porque no es hombre como yo, para que yo le responda,
        Y vengamos juntamente a juicio.
        33 No hay entre nosotros árbitro
        Que ponga su mano sobre nosotros dos.
        34 Quite de sobre mí su vara,
        Y su terror no me espante.
        35 Entonces hablaré, y no le temeré;
        Porque en este estado no estoy en mí.
""".trimIndent(),
        "Hechos 2:1-13" to """
        La venida del Espíritu Santo
        1 Cuando llegó el día de Pentecostés, estaban todos unánimes juntos. 
        2 Y de repente vino del cielo un estruendo como de un viento recio que soplaba, el cual llenó toda la casa donde estaban sentados; 
        3 y se les aparecieron lenguas repartidas, como de fuego, asentándose sobre cada uno de ellos. 
        4 Y fueron todos llenos del Espíritu Santo, y comenzaron a hablar en otras lenguas, según el Espíritu les daba que hablasen.
        5 Moraban entonces en Jerusalén judíos, varones piadosos, de todas las naciones bajo el cielo. 
        6 Y hecho este estruendo, se juntó la multitud; y estaban confusos, porque cada uno les oía hablar en su propia lengua. 
        7 Y estaban atónitos y maravillados, diciendo: Mirad, ¿no son galileos todos estos que hablan? 
        8 ¿Cómo, pues, les oímos nosotros hablar cada uno en nuestra lengua en la que hemos nacido? 
        9 Partos, medos, elamitas, y los que habitamos en Mesopotamia, en Judea, en Capadocia, en el Ponto y en Asia, 
        10 en Frigia y Panfilia, en Egipto y en las regiones de África más allá de Cirene, y romanos aquí residentes, tanto judíos como prosélitos, 
        11 cretenses y árabes, les oímos hablar en nuestras lenguas las maravillas de Dios. 
        12 Y estaban todos atónitos y perplejos, diciéndose unos a otros: ¿Qué quiere decir esto? 
        13 Mas otros, burlándose, decían: Están llenos de mosto.
""".trimIndent(),
        "Deuteronomio 13-14" to """
        Deuteronomio 13
        1 Cuando se levantare en medio de ti profeta, o soñador de sueños, y te anunciare señal o prodigios, 
        2 y si se cumpliere la señal o prodigio que él te anunció, diciendo: Vamos en pos de dioses ajenos, que no conociste, y sirvámosles; 
        3 no darás oído a las palabras de tal profeta, ni al tal soñador de sueños; porque Jehová vuestro Dios os está probando, para saber si amáis a Jehová vuestro Dios con todo vuestro corazón, y con toda vuestra alma. 
        4 En pos de Jehová vuestro Dios andaréis; a él temeréis, guardaréis sus mandamientos y escucharéis su voz, a él serviréis, y a él seguiréis. 
        5 Tal profeta o soñador de sueños ha de ser muerto, por cuanto aconsejó rebelión contra Jehová vuestro Dios que te sacó de tierra de Egipto y te rescató de casa de servidumbre, y trató de apartarte del camino por el cual Jehová tu Dios te mandó que anduvieses; y así quitarás el mal de en medio de ti.
        6 Si te incitare tu hermano, hijo de tu madre, o tu hijo, tu hija, tu mujer o tu amigo íntimo, diciendo en secreto: Vamos y sirvamos a dioses ajenos, que ni tú ni tus padres conocisteis, 
        7 de los dioses de los pueblos que están en vuestros alrededores, cerca de ti o lejos de ti, desde un extremo de la tierra hasta el otro extremo de ella; 
        8 no consentirás con él, ni le prestarás oído; ni tu ojo le compadecerá, ni le tendrás misericordia, ni lo encubrirás, 
        9 sino que lo matarás; tu mano se alzará primero sobre él para matarle, y después la mano de todo el pueblo. 
        10 Le apedrearás hasta que muera, por cuanto procuró apartarte de Jehová tu Dios, que te sacó de tierra de Egipto, de casa de servidumbre; 
        11 para que todo Israel oiga, y tema, y no vuelva a hacer en medio de ti cosa semejante a esta.
        12 Si oyeres que se dice de alguna de tus ciudades que Jehová tu Dios te da para vivir en ellas, 
        13 que han salido de en medio de ti hombres impíos que han instigado a los moradores de su ciudad, diciendo: Vamos y sirvamos a dioses ajenos, que vosotros no conocisteis; 
        14 tú inquirirás, y buscarás y preguntarás con diligencia; y si pareciere verdad, cosa cierta, que tal abominación se hizo en medio de ti, 
        15 irremisiblemente herirás a filo de espada a los moradores de aquella ciudad, destruyéndola con todo lo que en ella hubiere, y también matarás sus ganados a filo de espada. 
        16 Y juntarás todo su botín en medio de la plaza, y consumirás con fuego la ciudad y todo su botín, todo ello, como holocausto a Jehová tu Dios, y llegará a ser un montón de ruinas para siempre; nunca más será edificada. 
        17 Y no se pegará a tu mano nada del anatema, para que Jehová se aparte del ardor de su ira, y tenga de ti misericordia, y tenga compasión de ti, y te multiplique, como lo juró a tus padres, 
        18 cuando obedecieres a la voz de Jehová tu Dios, guardando todos sus mandamientos que yo te mando hoy, para hacer lo recto ante los ojos de Jehová tu Dios.
        Deuteronomio 14
        1 Hijos sois de Jehová vuestro Dios; no os sajaréis, ni os raparéis a causa de muerto. 
        2 Porque eres pueblo santo a Jehová tu Dios, y Jehová te ha escogido para que le seas un pueblo único de entre todos los pueblos que están sobre la tierra.
        Animales limpios e inmundos
        3 Nada abominable comerás. 
        4 Estos son los animales que podréis comer: el buey, la oveja, la cabra, 
        5 el ciervo, la gacela, el corzo, la cabra montés, el íbice, el antílope y el carnero montés. 
        6 Y todo animal de pezuñas, que tiene hendidura de dos uñas, y que rumiare entre los animales, ese podréis comer. 
        7 Pero estos no comeréis, entre los que rumian o entre los que tienen pezuña hendida: camello, liebre y conejo; porque rumian, mas no tienen pezuña hendida, serán inmundos; 
        8 ni cerdo, porque tiene pezuña hendida, mas no rumia; os será inmundo. De la carne de estos no comeréis, ni tocaréis sus cuerpos muertos.
        9 De todo lo que está en el agua, de estos podréis comer: todo lo que tiene aleta y escama. 
        10 Mas todo lo que no tiene aleta y escama, no comeréis; inmundo será.
        11 Toda ave limpia podréis comer. 
        12 Y estas son de las que no podréis comer: el águila, el quebrantahuesos, el azor, 
        13 el gallinazo, el milano según su especie, 
        14 todo cuervo según su especie, 
        15 el avestruz, la lechuza, la gaviota y el gavilán según sus especies, 
        16 el búho, el ibis, el calamón, 17 el pelícano, el buitre, el somormujo, 
        18 la cigüeña, la garza según su especie, la abubilla y el murciélago. 
        19 Todo insecto alado será inmundo; no se comerá. 
        20 Toda ave limpia podréis comer.
        21 Ninguna cosa mortecina comeréis; al extranjero que está en tus poblaciones la darás, y él podrá comerla; o véndela a un extranjero, porque tú eres pueblo santo a Jehová tu Dios. No cocerás el cabrito en la leche de su madre.
        La ley del diezmo
        22 Indefectiblemente diezmarás todo el producto del grano que rindiere tu campo cada año. 
        23 Y comerás delante de Jehová tu Dios en el lugar que él escogiere para poner allí su nombre, el diezmo de tu grano, de tu vino y de tu aceite, y las primicias de tus manadas y de tus ganados, para que aprendas a temer a Jehová tu Dios todos los días. 
        24 Y si el camino fuere tan largo que no puedas llevarlo, por estar lejos de ti el lugar que Jehová tu Dios hubiere escogido para poner en él su nombre, cuando Jehová tu Dios te bendijere, 
        25 entonces lo venderás y guardarás el dinero en tu mano, y vendrás al lugar que Jehová tu Dios escogiere; 
        26 y darás el dinero por todo lo que deseas, por vacas, por ovejas, por vino, por sidra, o por cualquier cosa que tú deseares; y comerás allí delante de Jehová tu Dios, y te alegrarás tú y tu familia. 
        27 Y no desampararás al levita que habitare en tus poblaciones; porque no tiene parte ni heredad contigo.
        28 Al fin de cada tres años sacarás todo el diezmo de tus productos de aquel año, y lo guardarás en tus ciudades. 
        29 Y vendrá el levita, que no tiene parte ni heredad contigo, y el extranjero, el huérfano y la viuda que hubiere en tus poblaciones, y comerán y serán saciados; para que Jehová tu Dios te bendiga en toda obra que tus manos hicieren.
""".trimIndent(),
        "Job 10" to """
        Job lamenta su condición
        1 Está mi alma hastiada de mi vida;
        Daré libre curso a mi queja,
        Hablaré con amargura de mi alma.
        2 Diré a Dios: No me condenes;
        Hazme entender por qué contiendes conmigo.
        3 ¿Te parece bien que oprimas,
        Que deseches la obra de tus manos,
        Y que favorezcas los designios de los impíos?
        4 ¿Tienes tú acaso ojos de carne?
        ¿Ves tú como ve el hombre?
        5 ¿Son tus días como los días del hombre,
        O tus años como los tiempos humanos,
        6 Para que inquieras mi iniquidad,
        Y busques mi pecado,
        7 Aunque tú sabes que no soy impío,
        Y que no hay quien de tu mano me libre?
        8 Tus manos me hicieron y me formaron;
        ¿Y luego te vuelves y me deshaces?
        9 Acuérdate que como a barro me diste forma;
        ¿Y en polvo me has de volver?
        10 ¿No me vaciaste como leche,
        Y como queso me cuajaste?
        11 Me vestiste de piel y carne,
        Y me tejiste con huesos y nervios.
        12 Vida y misericordia me concediste,
        Y tu cuidado guardó mi espíritu.
        13 Estas cosas tienes guardadas en tu corazón;
        Yo sé que están cerca de ti.
        14 Si pequé, tú me has observado,
        Y no me tendrás por limpio de mi iniquidad.
        15 Si fuere malo, ¡ay de mí!
        Y si fuere justo, no levantaré mi cabeza,
        Estando hastiado de deshonra, y de verme afligido.
        16 Si mi cabeza se alzare, cual león tú me cazas;
        Y vuelves a hacer en mí maravillas.
        17 Renuevas contra mí tus pruebas,
        Y aumentas conmigo tu furor como tropas de relevo.
        18 ¿Por qué me sacaste de la matriz?
        Hubiera yo expirado, y ningún ojo me habría visto.
        19 Fuera como si nunca hubiera existido,
        Llevado del vientre a la sepultura.
        20 ¿No son pocos mis días?
        Cesa, pues, y déjame, para que me consuele un poco,
        21 Antes que vaya para no volver,
        A la tierra de tinieblas y de sombra de muerte;
        22 Tierra de oscuridad, lóbrega,
        Como sombra de muerte y sin orden,
        Y cuya luz es como densas tinieblas.
""".trimIndent(),
        "Hechos 2:14-47" to """
        Primer discurso de Pedro
        14 Entonces Pedro, poniéndose en pie con los once, alzó la voz y les habló diciendo: Varones judíos, y todos los que habitáis en Jerusalén, esto os sea notorio, y oíd mis palabras. 
        15 Porque estos no están ebrios, como vosotros suponéis, puesto que es la hora tercera del día. 16 Mas esto es lo dicho por el profeta Joel:
        17 Y en los postreros días, dice Dios,
        Derramaré de mi Espíritu sobre toda carne,
        Y vuestros hijos y vuestras hijas profetizarán;
        Vuestros jóvenes verán visiones,
        Y vuestros ancianos soñarán sueños;
        18 Y de cierto sobre mis siervos y sobre mis siervas en aquellos días
        Derramaré de mi Espíritu, y profetizarán.
        19 Y daré prodigios arriba en el cielo,
        Y señales abajo en la tierra,
        Sangre y fuego y vapor de humo;
        20 El sol se convertirá en tinieblas,
        Y la luna en sangre,
        Antes que venga el día del Señor,
        Grande y manifiesto;
        21 Y todo aquel que invocare el nombre del Señor, será salvo.
        22 Varones israelitas, oíd estas palabras: Jesús nazareno, varón aprobado por Dios entre vosotros con las maravillas, prodigios y señales que Dios hizo entre vosotros por medio de él, como vosotros mismos sabéis; 
        23 a este, entregado por el determinado consejo y anticipado conocimiento de Dios, prendisteis y matasteis por manos de inicuos, crucificándole; 
        24 al cual Dios levantó, sueltos los dolores de la muerte, por cuanto era imposible que fuese retenido por ella. 
        25 Porque David dice de él:
        Veía al Señor siempre delante de mí;
        Porque está a mi diestra, no seré conmovido.
        26 Por lo cual mi corazón se alegró, y se gozó mi lengua,
        Y aun mi carne descansará en esperanza;
        27 Porque no dejarás mi alma en el Hades,
        Ni permitirás que tu Santo vea corrupción.
        28 Me hiciste conocer los caminos de la vida;
        Me llenarás de gozo con tu presencia.
        29 Varones hermanos, se os puede decir libremente del patriarca David, que murió y fue sepultado, y su sepulcro está con nosotros hasta el día de hoy. 
        30 Pero siendo profeta, y sabiendo que con juramento Dios le había jurado que de su descendencia, en cuanto a la carne, levantaría al Cristo para que se sentase en su trono, 
        31 viéndolo antes, habló de la resurrección de Cristo, que su alma no fue dejada en el Hades, ni su carne vio corrupción. 
        32 A este Jesús resucitó Dios, de lo cual todos nosotros somos testigos. 
        33 Así que, exaltado por la diestra de Dios, y habiendo recibido del Padre la promesa del Espíritu Santo, ha derramado esto que vosotros veis y oís. 
        34 Porque David no subió a los cielos; pero él mismo dice:
        Dijo el Señor a mi Señor:
        Siéntate a mi diestra,
        35 Hasta que ponga a tus enemigos por estrado de tus pies.
        36 Sepa, pues, ciertísimamente toda la casa de Israel, que a este Jesús a quien vosotros crucificasteis, Dios le ha hecho Señor y Cristo.
        37 Al oír esto, se compungieron de corazón, y dijeron a Pedro y a los otros apóstoles: Varones hermanos, ¿qué haremos? 
        38 Pedro les dijo: Arrepentíos, y bautícese cada uno de vosotros en el nombre de Jesucristo para perdón de los pecados; y recibiréis el don del Espíritu Santo. 
        39 Porque para vosotros es la promesa, y para vuestros hijos, y para todos los que están lejos; para cuantos el Señor nuestro Dios llamare. 
        40 Y con otras muchas palabras testificaba y les exhortaba, diciendo: Sed salvos de esta perversa generación. 
        41 Así que, los que recibieron su palabra fueron bautizados; y se añadieron aquel día como tres mil personas. 
        42 Y perseveraban en la doctrina de los apóstoles, en la comunión unos con otros, en el partimiento del pan y en las oraciones.
        La vida de los primeros cristianos
        43 Y sobrevino temor a toda persona; y muchas maravillas y señales eran hechas por los apóstoles. 
        44 Todos los que habían creído estaban juntos, y tenían en común todas las cosas; 
        45 y vendían sus propiedades y sus bienes, y lo repartían a todos según la necesidad de cada uno. 
        46 Y perseverando unánimes cada día en el templo, y partiendo el pan en las casas, comían juntos con alegría y sencillez de corazón, 
        47 alabando a Dios, y teniendo favor con todo el pueblo. Y el Señor añadía cada día a la iglesia los que habían de ser salvos.
""".trimIndent(),
        "Deuteronomio 15-16" to """
        Deuteronomio 15
        El año de remisión
        1 Cada siete años harás remisión. 
        2 Y esta es la manera de la remisión: perdonará a su deudor todo aquel que hizo empréstito de su mano, con el cual obligó a su prójimo; no lo demandará más a su prójimo, o a su hermano, porque es pregonada la remisión de Jehová. 
        3 Del extranjero demandarás el reintegro; pero lo que tu hermano tuviere tuyo, lo perdonará tu mano, 
        4 para que así no haya en medio de ti mendigo; porque Jehová te bendecirá con abundancia en la tierra que Jehová tu Dios te da por heredad para que la tomes en posesión, 
        5 si escuchares fielmente la voz de Jehová tu Dios, para guardar y cumplir todos estos mandamientos que yo te ordeno hoy. 
        6 Ya que Jehová tu Dios te habrá bendecido, como te ha dicho, prestarás entonces a muchas naciones, mas tú no tomarás prestado; tendrás dominio sobre muchas naciones, pero sobre ti no tendrán dominio.
        Préstamos a los pobres
        7 Cuando haya en medio de ti menesteroso de alguno de tus hermanos en alguna de tus ciudades, en la tierra que Jehová tu Dios te da, no endurecerás tu corazón, ni cerrarás tu mano contra tu hermano pobre, 
        8 sino abrirás a él tu mano liberalmente, y en efecto le prestarás lo que necesite. 
        9 Guárdate de tener en tu corazón pensamiento perverso, diciendo: Cerca está el año séptimo, el de la remisión, y mires con malos ojos a tu hermano menesteroso para no darle; porque él podrá clamar contra ti a Jehová, y se te contará por pecado. 
        10 Sin falta le darás, y no serás de mezquino corazón cuando le des; porque por ello te bendecirá Jehová tu Dios en todos tus hechos, y en todo lo que emprendas. 
        11 Porque no faltarán menesterosos en medio de la tierra; por eso yo te mando, diciendo: Abrirás tu mano a tu hermano, al pobre y al menesteroso en tu tierra.
        Leyes sobre los esclavos
        12 Si se vendiere a ti tu hermano hebreo o hebrea, y te hubiere servido seis años, al séptimo le despedirás libre. 
        13 Y cuando lo despidieres libre, no le enviarás con las manos vacías. 
        14 Le abastecerás liberalmente de tus ovejas, de tu era y de tu lagar; le darás de aquello en que Jehová te hubiere bendecido. 
        15 Y te acordarás de que fuiste siervo en la tierra de Egipto, y que Jehová tu Dios te rescató; por tanto yo te mando esto hoy. 
        16 Si él te dijere: No te dejaré; porque te ama a ti y a tu casa, y porque le va bien contigo; 
        17 entonces tomarás una lesna, y horadarás su oreja contra la puerta, y será tu siervo para siempre; así también harás a tu criada. 
        18 No te parezca duro cuando le enviares libre, pues por la mitad del costo de un jornalero te sirvió seis años; y Jehová tu Dios te bendecirá en todo cuanto hicieres.
        Consagración de los primogénitos machos
        19 Consagrarás a Jehová tu Dios todo primogénito macho de tus vacas y de tus ovejas; no te servirás del primogénito de tus vacas, ni trasquilarás el primogénito de tus ovejas. 
        20 Delante de Jehová tu Dios los comerás cada año, tú y tu familia, en el lugar que Jehová escogiere. 
        21 Y si hubiere en él defecto, si fuere ciego, o cojo, o hubiere en él cualquier falta, no lo sacrificarás a Jehová tu Dios. 
        22 En tus poblaciones lo comerás; el inmundo lo mismo que el limpio comerán de él, como de una gacela o de un ciervo. 
        23 Solamente que no comas su sangre; sobre la tierra la derramarás como agua.
        Deuteronomio 15
        Fiestas anuales
        1 Guardarás el mes de Abib, y harás pascua a Jehová tu Dios; porque en el mes de Abib te sacó Jehová tu Dios de Egipto, de noche. 
        2 Y sacrificarás la pascua a Jehová tu Dios, de las ovejas y de las vacas, en el lugar que Jehová escogiere para que habite allí su nombre. 
        3 No comerás con ella pan con levadura; siete días comerás con ella pan sin levadura, pan de aflicción, porque aprisa saliste de tierra de Egipto; para que todos los días de tu vida te acuerdes del día en que saliste de la tierra de Egipto. 
        4 Y no se verá levadura contigo en todo tu territorio por siete días; y de la carne que matares en la tarde del primer día, no quedará hasta la mañana. 
        5 No podrás sacrificar la pascua en cualquiera de las ciudades que Jehová tu Dios te da; 
        6 sino en el lugar que Jehová tu Dios escogiere para que habite allí su nombre, sacrificarás la pascua por la tarde a la puesta del sol, a la hora que saliste de Egipto. 
        7 Y la asarás y comerás en el lugar que Jehová tu Dios hubiere escogido; y por la mañana regresarás y volverás a tu habitación. 
        8 Seis días comerás pan sin levadura, y el séptimo día será fiesta solemne a Jehová tu Dios; no trabajarás en él.
        9 Siete semanas contarás; desde que comenzare a meterse la hoz en las mieses comenzarás a contar las siete semanas. 
        10 Y harás la fiesta solemne de las semanas a Jehová tu Dios; de la abundancia voluntaria de tu mano será lo que dieres, según Jehová tu Dios te hubiere bendecido. 
        11 Y te alegrarás delante de Jehová tu Dios, tú, tu hijo, tu hija, tu siervo, tu sierva, el levita que habitare en tus ciudades, y el extranjero, el huérfano y la viuda que estuvieren en medio de ti, en el lugar que Jehová tu Dios hubiere escogido para poner allí su nombre. 
        12 Y acuérdate de que fuiste siervo en Egipto; por tanto, guardarás y cumplirás estos estatutos.
        13 La fiesta solemne de los tabernáculos harás por siete días, cuando hayas hecho la cosecha de tu era y de tu lagar. 
        14 Y te alegrarás en tus fiestas solemnes, tú, tu hijo, tu hija, tu siervo, tu sierva, y el levita, el extranjero, el huérfano y la viuda que viven en tus poblaciones. 
        15 Siete días celebrarás fiesta solemne a Jehová tu Dios en el lugar que Jehová escogiere; porque te habrá bendecido Jehová tu Dios en todos tus frutos, y en toda la obra de tus manos, y estarás verdaderamente alegre.
        16 Tres veces cada año aparecerá todo varón tuyo delante de Jehová tu Dios en el lugar que él escogiere: en la fiesta solemne de los panes sin levadura, y en la fiesta solemne de las semanas, y en la fiesta solemne de los tabernáculos. Y ninguno se presentará delante de Jehová con las manos vacías; 
        17 cada uno con la ofrenda de su mano, conforme a la bendición que Jehová tu Dios te hubiere dado.
        Administración de la justicia
        18 Jueces y oficiales pondrás en todas tus ciudades que Jehová tu Dios te dará en tus tribus, los cuales juzgarán al pueblo con justo juicio. 
        19 No tuerzas el derecho; no hagas acepción de personas, ni tomes soborno; porque el soborno ciega los ojos de los sabios, y pervierte las palabras de los justos. 
        20 La justicia, la justicia seguirás, para que vivas y heredes la tierra que Jehová tu Dios te da.
        21 No plantarás ningún árbol para Asera cerca del altar de Jehová tu Dios, que tú te habrás hecho, 22 ni te levantarás estatua, lo cual aborrece Jehová tu Dios.
""".trimIndent(),
        "Job 11" to """
        Zofar acusa de maldad a Job
        1 Respondió Zofar naamatita, y dijo:
        2 ¿Las muchas palabras no han de tener respuesta?
        ¿Y el hombre que habla mucho será justificado?
        3 ¿Harán tus falacias callar a los hombres?
        ¿Harás escarnio y no habrá quien te avergüence?
        4 Tú dices: Mi doctrina es pura,
        Y yo soy limpio delante de tus ojos.
        5 Mas ¡oh, quién diera que Dios hablara,
        Y abriera sus labios contigo,
        6 Y te declarara los secretos de la sabiduría,
        Que son de doble valor que las riquezas!
        Conocerías entonces que Dios te ha castigado menos de lo que tu iniquidad merece.
        7 ¿Descubrirás tú los secretos de Dios?
        ¿Llegarás tú a la perfección del Todopoderoso?
        8 Es más alta que los cielos; ¿qué harás?
        Es más profunda que el Seol; ¿cómo la conocerás?
        9 Su dimensión es más extensa que la tierra,
        Y más ancha que el mar.
        10 Si él pasa, y aprisiona, y llama a juicio,
        ¿Quién podrá contrarrestarle?
        11 Porque él conoce a los hombres vanos;
        Ve asimismo la iniquidad, ¿y no hará caso?
        12 El hombre vano se hará entendido,
        Cuando un pollino de asno montés nazca hombre.
        13 Si tú dispusieres tu corazón,
        Y extendieres a él tus manos;
        14 Si alguna iniquidad hubiere en tu mano, y la echares de ti,
        Y no consintieres que more en tu casa la injusticia,
        15 Entonces levantarás tu rostro limpio de mancha,
        Y serás fuerte, y nada temerás;
        16 Y olvidarás tu miseria,
        O te acordarás de ella como de aguas que pasaron.
        17 La vida te será más clara que el mediodía;
        Aunque oscureciere, será como la mañana.
        18 Tendrás confianza, porque hay esperanza;
        Mirarás alrededor, y dormirás seguro.
        19 Te acostarás, y no habrá quien te espante;
        Y muchos suplicarán tu favor.
        20 Pero los ojos de los malos se consumirán,
        Y no tendrán refugio;
        Y su esperanza será dar su último suspiro.
""".trimIndent(),
        "Hechos 3" to """
        Curación de un cojo
        1 Pedro y Juan subían juntos al templo a la hora novena, la de la oración. 
        2 Y era traído un hombre cojo de nacimiento, a quien ponían cada día a la puerta del templo que se llama la Hermosa, para que pidiese limosna de los que entraban en el templo. 
        3 Este, cuando vio a Pedro y a Juan que iban a entrar en el templo, les rogaba que le diesen limosna. 
        4 Pedro, con Juan, fijando en él los ojos, le dijo: Míranos. 
        5 Entonces él les estuvo atento, esperando recibir de ellos algo. 
        6 Mas Pedro dijo: No tengo plata ni oro, pero lo que tengo te doy; en el nombre de Jesucristo de Nazaret, levántate y anda. 
        7 Y tomándole por la mano derecha le levantó; y al momento se le afirmaron los pies y tobillos; 
        8 y saltando, se puso en pie y anduvo; y entró con ellos en el templo, andando, y saltando, y alabando a Dios. 
        9 Y todo el pueblo le vio andar y alabar a Dios. 
        10 Y le reconocían que era el que se sentaba a pedir limosna a la puerta del templo, la Hermosa; y se llenaron de asombro y espanto por lo que le había sucedido.
        Discurso de Pedro en el pórtico de Salomón
        11 Y teniendo asidos a Pedro y a Juan el cojo que había sido sanado, todo el pueblo, atónito, concurrió a ellos al pórtico que se llama de Salomón. 
        12 Viendo esto Pedro, respondió al pueblo: Varones israelitas, ¿por qué os maravilláis de esto?, ¿o por qué ponéis los ojos en nosotros, como si por nuestro poder o piedad hubiésemos hecho andar a este? 
        13 El Dios de Abraham, de Isaac y de Jacob, el Dios de nuestros padres, ha glorificado a su Hijo Jesús, a quien vosotros entregasteis y negasteis delante de Pilato, cuando este había resuelto ponerle en libertad. 
        14 Mas vosotros negasteis al Santo y al Justo, y pedisteis que se os diese un homicida, 
        15 y matasteis al Autor de la vida, a quien Dios ha resucitado de los muertos, de lo cual nosotros somos testigos. 
        16 Y por la fe en su nombre, a este, que vosotros veis y conocéis, le ha confirmado su nombre; y la fe que es por él ha dado a este esta completa sanidad en presencia de todos vosotros.
        17 Mas ahora, hermanos, sé que por ignorancia lo habéis hecho, como también vuestros gobernantes. 
        18 Pero Dios ha cumplido así lo que había antes anunciado por boca de todos sus profetas, que su Cristo había de padecer. 
        19 Así que, arrepentíos y convertíos, para que sean borrados vuestros pecados; para que vengan de la presencia del Señor tiempos de refrigerio, 
        20 y él envíe a Jesucristo, que os fue antes anunciado; 
        21 a quien de cierto es necesario que el cielo reciba hasta los tiempos de la restauración de todas las cosas, de que habló Dios por boca de sus santos profetas que han sido desde tiempo antiguo. 
        22 Porque Moisés dijo a los padres: El Señor vuestro Dios os levantará profeta de entre vuestros hermanos, como a mí; a él oiréis en todas las cosas que os hable; 
        23 y toda alma que no oiga a aquel profeta, será desarraigada del pueblo. 
        24 Y todos los profetas desde Samuel en adelante, cuantos han hablado, también han anunciado estos días. 
        25 Vosotros sois los hijos de los profetas, y del pacto que Dios hizo con nuestros padres, diciendo a Abraham: En tu simiente serán benditas todas las familias de la tierra. 
        26 A vosotros primeramente, Dios, habiendo levantado a su Hijo, lo envió para que os bendijese, a fin de que cada uno se convierta de su maldad.
""".trimIndent(),
        "Deuteronomio 17-18" to """
        Deuteronomio 17
        1 No ofrecerás en sacrificio a Jehová tu Dios, buey o cordero en el cual haya falta o alguna cosa mala, pues es abominación a Jehová tu Dios.
        2 Cuando se hallare en medio de ti, en alguna de tus ciudades que Jehová tu Dios te da, hombre o mujer que haya hecho mal ante los ojos de Jehová tu Dios traspasando su pacto, 
        3 que hubiere ido y servido a dioses ajenos, y se hubiere inclinado a ellos, ya sea al sol, o a la luna, o a todo el ejército del cielo, lo cual yo he prohibido; 
        4 y te fuere dado aviso, y después que oyeres y hubieres indagado bien, la cosa pareciere de verdad cierta, que tal abominación ha sido hecha en Israel; 
        5 entonces sacarás a tus puertas al hombre o a la mujer que hubiere hecho esta mala cosa, sea hombre o mujer, y los apedrearás, y así morirán. 
        6 Por dicho de dos o de tres testigos morirá el que hubiere de morir; no morirá por el dicho de un solo testigo. 
        7 La mano de los testigos caerá primero sobre él para matarlo, y después la mano de todo el pueblo; así quitarás el mal de en medio de ti.
        8 Cuando alguna cosa te fuere difícil en el juicio, entre una clase de homicidio y otra, entre una clase de derecho legal y otra, y entre una clase de herida y otra, en negocios de litigio en tus ciudades; entonces te levantarás y recurrirás al lugar que Jehová tu Dios escogiere; 
        9 y vendrás a los sacerdotes levitas, y al juez que hubiere en aquellos días, y preguntarás; y ellos te enseñarán la sentencia del juicio. 
        10 Y harás según la sentencia que te indiquen los del lugar que Jehová escogiere, y cuidarás de hacer según todo lo que te manifiesten. 
        11 Según la ley que te enseñen, y según el juicio que te digan, harás; no te apartarás ni a diestra ni a siniestra de la sentencia que te declaren. 
        12 Y el hombre que procediere con soberbia, no obedeciendo al sacerdote que está para ministrar allí delante de Jehová tu Dios, o al juez, el tal morirá; y quitarás el mal de en medio de Israel. 
        13 Y todo el pueblo oirá, y temerá, y no se ensoberbecerá.
        Instrucciones acerca de un rey
        14 Cuando hayas entrado en la tierra que Jehová tu Dios te da, y tomes posesión de ella y la habites, y digas: Pondré un rey sobre mí, como todas las naciones que están en mis alrededores; 
        15 ciertamente pondrás por rey sobre ti al que Jehová tu Dios escogiere; de entre tus hermanos pondrás rey sobre ti; no podrás poner sobre ti a hombre extranjero, que no sea tu hermano. 
        16 Pero él no aumentará para sí caballos, ni hará volver al pueblo a Egipto con el fin de aumentar caballos; porque Jehová os ha dicho: No volváis nunca por este camino. 
        17 Ni tomará para sí muchas mujeres, para que su corazón no se desvíe; ni plata ni oro amontonará para sí en abundancia. 
        18 Y cuando se siente sobre el trono de su reino, entonces escribirá para sí en un libro una copia de esta ley, del original que está al cuidado de los sacerdotes levitas; 
        19 y lo tendrá consigo, y leerá en él todos los días de su vida, para que aprenda a temer a Jehová su Dios, para guardar todas las palabras de esta ley y estos estatutos, para ponerlos por obra; 
        20 para que no se eleve su corazón sobre sus hermanos, ni se aparte del mandamiento a diestra ni a siniestra; a fin de que prolongue sus días en su reino, él y sus hijos, en medio de Israel.
        Deuteronomio 18
        Las porciones de los levitas
        1 Los sacerdotes levitas, es decir, toda la tribu de Leví, no tendrán parte ni heredad en Israel; de las ofrendas quemadas a Jehová y de la heredad de él comerán. 
        2 No tendrán, pues, heredad entre sus hermanos; Jehová es su heredad, como él les ha dicho. 
        3 Y este será el derecho de los sacerdotes de parte del pueblo, de los que ofrecieren en sacrificio buey o cordero: darán al sacerdote la espaldilla, las quijadas y el cuajar. 
        4 Las primicias de tu grano, de tu vino y de tu aceite, y las primicias de la lana de tus ovejas le darás; 
        5 porque le ha escogido Jehová tu Dios de entre todas tus tribus, para que esté para administrar en el nombre de Jehová, él y sus hijos para siempre.
        6 Y cuando saliere un levita de alguna de tus ciudades de entre todo Israel, donde hubiere vivido, y viniere con todo el deseo de su alma al lugar que Jehová escogiere, 
        7 ministrará en el nombre de Jehová su Dios como todos sus hermanos los levitas que estuvieren allí delante de Jehová. 
        8 Igual ración a la de los otros comerá, además de sus patrimonios.
        Amonestación contra costumbres paganas
        9 Cuando entres a la tierra que Jehová tu Dios te da, no aprenderás a hacer según las abominaciones de aquellas naciones. 
        10 No sea hallado en ti quien haga pasar a su hijo o a su hija por el fuego, ni quien practique adivinación, ni agorero, ni sortílego, ni hechicero, 
        11 ni encantador, ni adivino, ni mago, ni quien consulte a los muertos. 
        12 Porque es abominación para con Jehová cualquiera que hace estas cosas, y por estas abominaciones Jehová tu Dios echa estas naciones de delante de ti. 
        13 Perfecto serás delante de Jehová tu Dios. 
        14 Porque estas naciones que vas a heredar, a agoreros y a adivinos oyen; mas a ti no te ha permitido esto Jehová tu Dios.
        Dios promete un profeta como Moisés
        15 Profeta de en medio de ti, de tus hermanos, como yo, te levantará Jehová tu Dios; a él oiréis; 
        16 conforme a todo lo que pediste a Jehová tu Dios en Horeb el día de la asamblea, diciendo: No vuelva yo a oír la voz de Jehová mi Dios, ni vea yo más este gran fuego, para que no muera. 
        17 Y Jehová me dijo: Han hablado bien en lo que han dicho. 
        18 Profeta les levantaré de en medio de sus hermanos, como tú; y pondré mis palabras en su boca, y él les hablará todo lo que yo le mandare. 
        19 Mas a cualquiera que no oyere mis palabras que él hablare en mi nombre, yo le pediré cuenta. 
        20 El profeta que tuviere la presunción de hablar palabra en mi nombre, a quien yo no le haya mandado hablar, o que hablare en nombre de dioses ajenos, el tal profeta morirá. 
        21 Y si dijeres en tu corazón: ¿Cómo conoceremos la palabra que Jehová no ha hablado?; 
        22 si el profeta hablare en nombre de Jehová, y no se cumpliere lo que dijo, ni aconteciere, es palabra que Jehová no ha hablado; con presunción la habló el tal profeta; no tengas temor de él.
""".trimIndent(),
        "Job 12" to """
        Job proclama el poder y la sabiduría de Dios
        1 Respondió entonces Job, diciendo:
        2 Ciertamente vosotros sois el pueblo,
        Y con vosotros morirá la sabiduría.
        3 También tengo yo entendimiento como vosotros;
        No soy yo menos que vosotros;
        ¿Y quién habrá que no pueda decir otro tanto?
        4 Yo soy uno de quien su amigo se mofa,
        Que invoca a Dios, y él le responde;
        Con todo, el justo y perfecto es escarnecido.
        5 Aquel cuyos pies van a resbalar
        Es como una lámpara despreciada de aquel que está a sus anchas.
        6 Prosperan las tiendas de los ladrones,
        Y los que provocan a Dios viven seguros,
        En cuyas manos él ha puesto cuanto tienen.
        7 Y en efecto, pregunta ahora a las bestias, y ellas te enseñarán;
        A las aves de los cielos, y ellas te lo mostrarán;
        8 O habla a la tierra, y ella te enseñará;
        Los peces del mar te lo declararán también.
        9 ¿Qué cosa de todas estas no entiende
        Que la mano de Jehová la hizo?
        10 En su mano está el alma de todo viviente,
        Y el hálito de todo el género humano.
        11 Ciertamente el oído distingue las palabras,
        Y el paladar gusta las viandas.
        12 En los ancianos está la ciencia,
        Y en la larga edad la inteligencia.
        13 Con Dios está la sabiduría y el poder;
        Suyo es el consejo y la inteligencia.
        14 Si él derriba, no hay quien edifique;
        Encerrará al hombre, y no habrá quien le abra.
        15 Si él detiene las aguas, todo se seca;
        Si las envía, destruyen la tierra.
        16 Con él está el poder y la sabiduría;
        Suyo es el que yerra, y el que hace errar.
        17 Él hace andar despojados de consejo a los consejeros,
        Y entontece a los jueces.
        18 Él rompe las cadenas de los tiranos,
        Y les ata una soga a sus lomos.
        19 Él lleva despojados a los príncipes,
        Y trastorna a los poderosos.
        20 Priva del habla a los que dicen verdad,
        Y quita a los ancianos el consejo.
        21 Él derrama menosprecio sobre los príncipes,
        Y desata el cinto de los fuertes.
        22 Él descubre las profundidades de las tinieblas,
        Y saca a luz la sombra de muerte.
        23 Él multiplica las naciones, y él las destruye;
        Esparce a las naciones, y las vuelve a reunir.
        24 Él quita el entendimiento a los jefes del pueblo de la tierra,
        Y los hace vagar como por un yermo sin camino.
        25 Van a tientas, como en tinieblas y sin luz,
        Y los hace errar como borrachos.
""".trimIndent(),
        "Hechos 4:1-22" to """
        Pedro y Juan ante el concilio
        1 Hablando ellos al pueblo, vinieron sobre ellos los sacerdotes con el jefe de la guardia del templo, y los saduceos, 
        2 resentidos de que enseñasen al pueblo, y anunciasen en Jesús la resurrección de entre los muertos. 
        3 Y les echaron mano, y los pusieron en la cárcel hasta el día siguiente, porque era ya tarde. 
        4 Pero muchos de los que habían oído la palabra, creyeron; y el número de los varones era como cinco mil.
        5 Aconteció al día siguiente, que se reunieron en Jerusalén los gobernantes, los ancianos y los escribas, 
        6 y el sumo sacerdote Anás, y Caifás y Juan y Alejandro, y todos los que eran de la familia de los sumos sacerdotes; 
        7 y poniéndoles en medio, les preguntaron: ¿Con qué potestad, o en qué nombre, habéis hecho vosotros esto? 
        8 Entonces Pedro, lleno del Espíritu Santo, les dijo: Gobernantes del pueblo, y ancianos de Israel: 
        9 Puesto que hoy se nos interroga acerca del beneficio hecho a un hombre enfermo, de qué manera este haya sido sanado, 
        10 sea notorio a todos vosotros, y a todo el pueblo de Israel, que en el nombre de Jesucristo de Nazaret, a quien vosotros crucificasteis y a quien Dios resucitó de los muertos, por él este hombre está en vuestra presencia sano. 
        11 Este Jesús es la piedra reprobada por vosotros los edificadores, la cual ha venido a ser cabeza del ángulo. 
        12 Y en ningún otro hay salvación; porque no hay otro nombre bajo el cielo, dado a los hombres, en que podamos ser salvos.
        13 Entonces viendo el denuedo de Pedro y de Juan, y sabiendo que eran hombres sin letras y del vulgo, se maravillaban; y les reconocían que habían estado con Jesús. 
        14 Y viendo al hombre que había sido sanado, que estaba en pie con ellos, no podían decir nada en contra. 
        15 Entonces les ordenaron que saliesen del concilio; y conferenciaban entre sí, 
        16 diciendo: ¿Qué haremos con estos hombres? Porque de cierto, señal manifiesta ha sido hecha por ellos, notoria a todos los que moran en Jerusalén, y no lo podemos negar. 
        17 Sin embargo, para que no se divulgue más entre el pueblo, amenacémosles para que no hablen de aquí en adelante a hombre alguno en este nombre. 
        18 Y llamándolos, les intimaron que en ninguna manera hablasen ni enseñasen en el nombre de Jesús. 
        19 Mas Pedro y Juan respondieron diciéndoles: Juzgad si es justo delante de Dios obedecer a vosotros antes que a Dios; 
        20 porque no podemos dejar de decir lo que hemos visto y oído. 
        21 Ellos entonces les amenazaron y les soltaron, no hallando ningún modo de castigarles, por causa del pueblo; porque todos glorificaban a Dios por lo que se había hecho, 
        22 ya que el hombre en quien se había hecho este milagro de sanidad, tenía más de cuarenta años.
""".trimIndent(),
        "Deuteronomio 19-20" to """
        Deuteronomio 19
        Las ciudades de refugio
        1 Cuando Jehová tu Dios destruya a las naciones cuya tierra Jehová tu Dios te da a ti, y tú las heredes, y habites en sus ciudades, y en sus casas; 
        2 te apartarás tres ciudades en medio de la tierra que Jehová tu Dios te da para que la poseas. 
        3 Arreglarás los caminos, y dividirás en tres partes la tierra que Jehová tu Dios te dará en heredad, y será para que todo homicida huya allí.
        4 Y este es el caso del homicida que huirá allí, y vivirá: aquel que hiriere a su prójimo sin intención y sin haber tenido enemistad con él anteriormente; 
        5 como el que fuere con su prójimo al monte a cortar leña, y al dar su mano el golpe con el hacha para cortar algún leño, saltare el hierro del cabo, y diere contra su prójimo y este muriere; aquel huirá a una de estas ciudades, y vivirá; 
        6 no sea que el vengador de la sangre, enfurecido, persiga al homicida, y le alcance por ser largo el camino, y le hiera de muerte, no debiendo ser condenado a muerte por cuanto no tenía enemistad con su prójimo anteriormente. 
        7 Por tanto yo te mando, diciendo: Separarás tres ciudades. 
        8 Y si Jehová tu Dios ensanchare tu territorio, como lo juró a tus padres, y te diere toda la tierra que prometió dar a tus padres, 
        9 siempre y cuando guardares todos estos mandamientos que yo te prescribo hoy, para ponerlos por obra; que ames a Jehová tu Dios y andes en sus caminos todos los días; entonces añadirás tres ciudades más a estas tres, 
        10 para que no sea derramada sangre inocente en medio de la tierra que Jehová tu Dios te da por heredad, y no seas culpado de derramamiento de sangre.
        11 Pero si hubiere alguno que aborreciere a su prójimo y lo acechare, y se levantare contra él y lo hiriere de muerte, y muriere; si huyere a alguna de estas ciudades, 
        12 entonces los ancianos de su ciudad enviarán y lo sacarán de allí, y lo entregarán en mano del vengador de la sangre para que muera. 
        13 No le compadecerás; y quitarás de Israel la sangre inocente, y te irá bien.
        14 En la heredad que poseas en la tierra que Jehová tu Dios te da, no reducirás los límites de la propiedad de tu prójimo, que fijaron los antiguos.
        Leyes sobre el testimonio
        15 No se tomará en cuenta a un solo testigo contra ninguno en cualquier delito ni en cualquier pecado, en relación con cualquiera ofensa cometida. Solo por el testimonio de dos o tres testigos se mantendrá la acusación. 
        16 Cuando se levantare testigo falso contra alguno, para testificar contra él, 
        17 entonces los dos litigantes se presentarán delante de Jehová, y delante de los sacerdotes y de los jueces que hubiere en aquellos días. 
        18 Y los jueces inquirirán bien; y si aquel testigo resultare falso, y hubiere acusado falsamente a su hermano, 
        19 entonces haréis a él como él pensó hacer a su hermano; y quitarás el mal de en medio de ti. 
        20 Y los que quedaren oirán y temerán, y no volverán a hacer más una maldad semejante en medio de ti. 
        21 Y no le compadecerás; vida por vida, ojo por ojo, diente por diente, mano por mano, pie por pie.
        Deuteronomio 20
        Leyes sobre la guerra
        1 Cuando salgas a la guerra contra tus enemigos, si vieres caballos y carros, y un pueblo más grande que tú, no tengas temor de ellos, porque Jehová tu Dios está contigo, el cual te sacó de tierra de Egipto. 
        2 Y cuando os acerquéis para combatir, se pondrá en pie el sacerdote y hablará al pueblo, 
        3 y les dirá: Oye, Israel, vosotros os juntáis hoy en batalla contra vuestros enemigos; no desmaye vuestro corazón, no temáis, ni os azoréis, ni tampoco os desalentéis delante de ellos; 
        4 porque Jehová vuestro Dios va con vosotros, para pelear por vosotros contra vuestros enemigos, para salvaros. 
        5 Y los oficiales hablarán al pueblo, diciendo: ¿Quién ha edificado casa nueva, y no la ha estrenado? Vaya, y vuélvase a su casa, no sea que muera en la batalla, y algún otro la estrene. 
        6 ¿Y quién ha plantado viña, y no ha disfrutado de ella? Vaya, y vuélvase a su casa, no sea que muera en la batalla, y algún otro la disfrute. 
        7 ¿Y quién se ha desposado con mujer, y no la ha tomado? Vaya, y vuélvase a su casa, no sea que muera en la batalla, y algún otro la tome. 
        8 Y volverán los oficiales a hablar al pueblo, y dirán: ¿Quién es hombre medroso y pusilánime? Vaya, y vuélvase a su casa, y no apoque el corazón de sus hermanos, como el corazón suyo. 
        9 Y cuando los oficiales acaben de hablar al pueblo, entonces los capitanes del ejército tomarán el mando a la cabeza del pueblo.
        10 Cuando te acerques a una ciudad para combatirla, le intimarás la paz. 
        11 Y si respondiere: Paz, y te abriere, todo el pueblo que en ella fuere hallado te será tributario, y te servirá. 
        12 Mas si no hiciere paz contigo, y emprendiere guerra contigo, entonces la sitiarás. 
        13 Luego que Jehová tu Dios la entregue en tu mano, herirás a todo varón suyo a filo de espada. 
        14 Solamente las mujeres y los niños, y los animales, y todo lo que haya en la ciudad, todo su botín tomarás para ti; y comerás del botín de tus enemigos, los cuales Jehová tu Dios te entregó. 
        15 Así harás a todas las ciudades que estén muy lejos de ti, que no sean de las ciudades de estas naciones. 
        16 Pero de las ciudades de estos pueblos que Jehová tu Dios te da por heredad, ninguna persona dejarás con vida, 
        17 sino que los destruirás completamente: al heteo, al amorreo, al cananeo, al ferezeo, al heveo y al jebuseo, como Jehová tu Dios te ha mandado; 
        18 para que no os enseñen a hacer según todas sus abominaciones que ellos han hecho para sus dioses, y pequéis contra Jehová vuestro Dios.
        19 Cuando sities a alguna ciudad, peleando contra ella muchos días para tomarla, no destruirás sus árboles metiendo hacha en ellos, porque de ellos podrás comer; y no los talarás, porque el árbol del campo no es hombre para venir contra ti en el sitio. 
        20 Mas el árbol que sepas que no lleva fruto, podrás destruirlo y talarlo, para construir baluarte contra la ciudad que te hace la guerra, hasta sojuzgarla.
""".trimIndent(),
        "Job 13" to """
        Job defiende su integridad
        1 He aquí que todas estas cosas han visto mis ojos,
        Y oído y entendido mis oídos.
        2 Como vosotros lo sabéis, lo sé yo;
        No soy menos que vosotros.
        3 Mas yo hablaría con el Todopoderoso,
        Y querría razonar con Dios.
        4 Porque ciertamente vosotros sois fraguadores de mentira;
        Sois todos vosotros médicos nulos.
        5 Ojalá callarais por completo,
        Porque esto os fuera sabiduría.
        6 Oíd ahora mi razonamiento,
        Y estad atentos a los argumentos de mis labios.
        7 ¿Hablaréis iniquidad por Dios?
        ¿Hablaréis por él engaño?
        8 ¿Haréis acepción de personas a su favor?
        ¿Contenderéis vosotros por Dios?
        9 ¿Sería bueno que él os escudriñase?
        ¿Os burlaréis de él como quien se burla de algún hombre?
        10 Él os reprochará de seguro,
        Si solapadamente hacéis acepción de personas.
        11 De cierto su alteza os habría de espantar,
        Y su pavor habría de caer sobre vosotros.
        12 Vuestras máximas son refranes de ceniza,
        Y vuestros baluartes son baluartes de lodo.
        13 Escuchadme, y hablaré yo,
        Y que me venga después lo que viniere.
        14 ¿Por qué quitaré yo mi carne con mis dientes,
        Y tomaré mi vida en mi mano?
        15 He aquí, aunque él me matare, en él esperaré;
        No obstante, defenderé delante de él mis caminos,
        16 Y él mismo será mi salvación,
        Porque no entrará en su presencia el impío.
        17 Oíd con atención mi razonamiento,
        Y mi declaración entre en vuestros oídos.
        18 He aquí ahora, si yo expusiere mi causa,
        Sé que seré justificado.
        19 ¿Quién es el que contenderá conmigo?
        Porque si ahora yo callara, moriría.
        20 A lo menos dos cosas no hagas conmigo;
        Entonces no me esconderé de tu rostro:
        21 Aparta de mí tu mano,
        Y no me asombre tu terror.
        22 Llama luego, y yo responderé;
        O yo hablaré, y respóndeme tú.
        23 ¿Cuántas iniquidades y pecados tengo yo?
        Hazme entender mi transgresión y mi pecado.
        24 ¿Por qué escondes tu rostro,
        Y me cuentas por tu enemigo?
        25 ¿A la hoja arrebatada has de quebrantar,
        Y a una paja seca has de perseguir?
        26 ¿Por qué escribes contra mí amarguras,
        Y me haces cargo de los pecados de mi juventud?
        27 Pones además mis pies en el cepo, y observas todos mis caminos,
        Trazando un límite para las plantas de mis pies.
        28 Y mi cuerpo se va gastando como de carcoma,
        Como vestido que roe la polilla.
""".trimIndent(),
        "Hechos 4:23-37" to """
        Los creyentes piden confianza y valor
        23 Y puestos en libertad, vinieron a los suyos y contaron todo lo que los principales sacerdotes y los ancianos les habían dicho. 
        24 Y ellos, habiéndolo oído, alzaron unánimes la voz a Dios, y dijeron: Soberano Señor, tú eres el Dios que hiciste el cielo y la tierra, el mar y todo lo que en ellos hay; 
        25 que por boca de David tu siervo dijiste:
        26 ¿Por qué se amotinan las gentes,
        Y los pueblos piensan cosas vanas?
        Se reunieron los reyes de la tierra,
        Y los príncipes se juntaron en uno
        Contra el Señor, y contra su Cristo.
        27 Porque verdaderamente se unieron en esta ciudad contra tu santo Hijo Jesús, a quien ungiste, Herodes y Poncio Pilato, con los gentiles y el pueblo de Israel, 
        28 para hacer cuanto tu mano y tu consejo habían antes determinado que sucediera. 
        29 Y ahora, Señor, mira sus amenazas, y concede a tus siervos que con todo denuedo hablen tu palabra, 
        30 mientras extiendes tu mano para que se hagan sanidades y señales y prodigios mediante el nombre de tu santo Hijo Jesús. 
        31 Cuando hubieron orado, el lugar en que estaban congregados tembló; y todos fueron llenos del Espíritu Santo, y hablaban con denuedo la palabra de Dios.
        Todas las cosas en común
        32 Y la multitud de los que habían creído era de un corazón y un alma; y ninguno decía ser suyo propio nada de lo que poseía, sino que tenían todas las cosas en común. 
        33 Y con gran poder los apóstoles daban testimonio de la resurrección del Señor Jesús, y abundante gracia era sobre todos ellos. 
        34 Así que no había entre ellos ningún necesitado; porque todos los que poseían heredades o casas, las vendían, y traían el precio de lo vendido, 
        35 y lo ponían a los pies de los apóstoles; y se repartía a cada uno según su necesidad. 
        36 Entonces José, a quien los apóstoles pusieron por sobrenombre Bernabé (que traducido es, Hijo de consolación), levita, natural de Chipre, 
        37 como tenía una heredad, la vendió y trajo el precio y lo puso a los pies de los apóstoles.
""".trimIndent(),
        "Deuteronomio 21-22" to """
        Deuteronomio 21
        Expiación de un asesinato cuyo autor se desconoce
        1 Si en la tierra que Jehová tu Dios te da para que la poseas, fuere hallado alguien muerto, tendido en el campo, y no se supiere quién lo mató, 
        2 entonces tus ancianos y tus jueces saldrán y medirán la distancia hasta las ciudades que están alrededor del muerto. 
        3 Y los ancianos de la ciudad más cercana al lugar donde fuere hallado el muerto, tomarán de las vacas una becerra que no haya trabajado, que no haya llevado yugo;
        4 y los ancianos de aquella ciudad traerán la becerra a un valle escabroso, que nunca haya sido arado ni sembrado, y quebrarán la cerviz de la becerra allí en el valle. 
        5 Entonces vendrán los sacerdotes hijos de Leví, porque a ellos escogió Jehová tu Dios para que le sirvan, y para bendecir en el nombre de Jehová; y por la palabra de ellos se decidirá toda disputa y toda ofensa. 
        6 Y todos los ancianos de la ciudad más cercana al lugar donde fuere hallado el muerto lavarán sus manos sobre la becerra cuya cerviz fue quebrada en el valle; 
        7 y protestarán y dirán: Nuestras manos no han derramado esta sangre, ni nuestros ojos lo han visto. 
        8 Perdona a tu pueblo Israel, al cual redimiste, oh Jehová; y no culpes de sangre inocente a tu pueblo Israel. Y la sangre les será perdonada. 
        9 Y tú quitarás la culpa de la sangre inocente de en medio de ti, cuando hicieres lo que es recto ante los ojos de Jehová.
        Diversas leyes
        10 Cuando salieres a la guerra contra tus enemigos, y Jehová tu Dios los entregare en tu mano, y tomares de ellos cautivos, 
        11 y vieres entre los cautivos a alguna mujer hermosa, y la codiciares, y la tomares para ti por mujer, 
        12 la meterás en tu casa; y ella rapará su cabeza, y cortará sus uñas, 
        13 y se quitará el vestido de su cautiverio, y se quedará en tu casa; y llorará a su padre y a su madre un mes entero; y después podrás llegarte a ella, y tú serás su marido, y ella será tu mujer. 
        14 Y si no te agradare, la dejarás en libertad; no la venderás por dinero, ni la tratarás como esclava, por cuanto la humillaste.
        15 Si un hombre tuviere dos mujeres, la una amada y la otra aborrecida, y la amada y la aborrecida le hubieren dado hijos, y el hijo primogénito fuere de la aborrecida; 
        16 en el día que hiciere heredar a sus hijos lo que tuviere, no podrá dar el derecho de primogenitura al hijo de la amada con preferencia al hijo de la aborrecida, que es el primogénito; 
        17 mas al hijo de la aborrecida reconocerá como primogénito, para darle el doble de lo que correspondiere a cada uno de los demás; porque él es el principio de su vigor, y suyo es el derecho de la primogenitura.
        18 Si alguno tuviere un hijo contumaz y rebelde, que no obedeciere a la voz de su padre ni a la voz de su madre, y habiéndole castigado, no les obedeciere; 
        19 entonces lo tomarán su padre y su madre, y lo sacarán ante los ancianos de su ciudad, y a la puerta del lugar donde viva; 
        20 y dirán a los ancianos de la ciudad: Este nuestro hijo es contumaz y rebelde, no obedece a nuestra voz; es glotón y borracho. 
        21 Entonces todos los hombres de su ciudad lo apedrearán, y morirá; así quitarás el mal de en medio de ti, y todo Israel oirá, y temerá.
        22 Si alguno hubiere cometido algún crimen digno de muerte, y lo hiciereis morir, y lo colgareis en un madero, 
        23 no dejaréis que su cuerpo pase la noche sobre el madero; sin falta lo enterrarás el mismo día, porque maldito por Dios es el colgado; y no contaminarás tu tierra que Jehová tu Dios te da por heredad.
        Deuteronomio 22
        1 Si vieres extraviado el buey de tu hermano, o su cordero, no le negarás tu ayuda; lo volverás a tu hermano. 
        2 Y si tu hermano no fuere tu vecino, o no lo conocieres, lo recogerás en tu casa, y estará contigo hasta que tu hermano lo busque, y se lo devolverás. 
        3 Así harás con su asno, así harás también con su vestido, y lo mismo harás con toda cosa de tu hermano que se le perdiere y tú la hallares; no podrás negarle tu ayuda. 
        4 Si vieres el asno de tu hermano, o su buey, caído en el camino, no te apartarás de él; le ayudarás a levantarlo.
        5 No vestirá la mujer traje de hombre, ni el hombre vestirá ropa de mujer; porque abominación es a Jehová tu Dios cualquiera que esto hace.
        6 Cuando encuentres por el camino algún nido de ave en cualquier árbol, o sobre la tierra, con pollos o huevos, y la madre echada sobre los pollos o sobre los huevos, no tomarás la madre con los hijos. 
        7 Dejarás ir a la madre, y tomarás los pollos para ti, para que te vaya bien, y prolongues tus días.
        8 Cuando edifiques casa nueva, harás pretil a tu terrado, para que no eches culpa de sangre sobre tu casa, si de él cayere alguno.
        9 No sembrarás tu viña con semillas diversas, no sea que se pierda todo, tanto la semilla que sembraste como el fruto de la viña. 
        10 No ararás con buey y con asno juntamente. 
        11 No vestirás ropa de lana y lino juntamente.
        12 Te harás flecos en las cuatro puntas de tu manto con que te cubras.
        Leyes sobre la castidad
        13 Cuando alguno tomare mujer, y después de haberse llegado a ella la aborreciere, 
        14 y le atribuyere faltas que den que hablar, y dijere: A esta mujer tomé, y me llegué a ella, y no la hallé virgen; 
        15 entonces el padre de la joven y su madre tomarán y sacarán las señales de la virginidad de la doncella a los ancianos de la ciudad, en la puerta; 
        16 y dirá el padre de la joven a los ancianos: Yo di mi hija a este hombre por mujer, y él la aborrece; 
        17 y he aquí, él le atribuye faltas que dan que hablar, diciendo: No he hallado virgen a tu hija; pero ved aquí las señales de la virginidad de mi hija. Y extenderán la vestidura delante de los ancianos de la ciudad. 
        18 Entonces los ancianos de la ciudad tomarán al hombre y lo castigarán; 
        19 y le multarán en cien piezas de plata, las cuales darán al padre de la joven, por cuanto esparció mala fama sobre una virgen de Israel; y la tendrá por mujer, y no podrá despedirla en todos sus días. 
        20 Mas si resultare ser verdad que no se halló virginidad en la joven, 
        21 entonces la sacarán a la puerta de la casa de su padre, y la apedrearán los hombres de su ciudad, y morirá, por cuanto hizo vileza en Israel fornicando en casa de su padre; así quitarás el mal de en medio de ti.
        22 Si fuere sorprendido alguno acostado con una mujer casada con marido, ambos morirán, el hombre que se acostó con la mujer, y la mujer también; así quitarás el mal de Israel.
        23 Si hubiere una muchacha virgen desposada con alguno, y alguno la hallare en la ciudad, y se acostare con ella; 
        24 entonces los sacaréis a ambos a la puerta de la ciudad, y los apedrearéis, y morirán; la joven porque no dio voces en la ciudad, y el hombre porque humilló a la mujer de su prójimo; así quitarás el mal de en medio de ti.
        25 Mas si un hombre hallare en el campo a la joven desposada, y la forzare aquel hombre, acostándose con ella, morirá solamente el hombre que se acostó con ella; 
        26 mas a la joven no le harás nada; no hay en ella culpa de muerte; pues como cuando alguno se levanta contra su prójimo y le quita la vida, así es en este caso. 
        27 Porque él la halló en el campo; dio voces la joven desposada, y no hubo quien la librase.
        28 Cuando algún hombre hallare a una joven virgen que no fuere desposada, y la tomare y se acostare con ella, y fueren descubiertos; 
        29 entonces el hombre que se acostó con ella dará al padre de la joven cincuenta piezas de plata, y ella será su mujer, por cuanto la humilló; no la podrá despedir en todos sus días.
        30 Ninguno tomará la mujer de su padre, ni profanará el lecho de su padre.
""".trimIndent(),
        "Job 14" to """
        Job discurre sobre la brevedad de la vida
        1 El hombre nacido de mujer,
        Corto de días, y hastiado de sinsabores,
        2 Sale como una flor y es cortado,
        Y huye como la sombra y no permanece.
        3 ¿Sobre este abres tus ojos,
        Y me traes a juicio contigo?
        4 ¿Quién hará limpio a lo inmundo?
        Nadie.
        5 Ciertamente sus días están determinados,
        Y el número de sus meses está cerca de ti;
        Le pusiste límites, de los cuales no pasará.
        6 Si tú lo abandonares, él dejará de ser;
        Entre tanto deseará, como el jornalero, su día.
        7 Porque si el árbol fuere cortado, aún queda de él esperanza;
        Retoñará aún, y sus renuevos no faltarán.
        8 Si se envejeciere en la tierra su raíz,
        Y su tronco fuere muerto en el polvo,
        9 Al percibir el agua reverdecerá,
        Y hará copa como planta nueva.
        10 Mas el hombre morirá, y será cortado;
        Perecerá el hombre, ¿y dónde estará él?
        11 Como las aguas se van del mar,
        Y el río se agota y se seca,
        12 Así el hombre yace y no vuelve a levantarse;
        Hasta que no haya cielo, no despertarán,
        Ni se levantarán de su sueño.
        13 ¡Oh, quién me diera que me escondieses en el Seol,
        Que me encubrieses hasta apaciguarse tu ira,
        Que me pusieses plazo, y de mí te acordaras!
        14 Si el hombre muriere, ¿volverá a vivir?
        Todos los días de mi edad esperaré,
        Hasta que venga mi liberación.
        15 Entonces llamarás, y yo te responderé;
        Tendrás afecto a la hechura de tus manos.
        16 Pero ahora me cuentas los pasos,
        Y no das tregua a mi pecado;
        17 Tienes sellada en saco mi prevaricación,
        Y tienes cosida mi iniquidad.
        18 Ciertamente el monte que cae se deshace,
        Y las peñas son removidas de su lugar;
        19 Las piedras se desgastan con el agua impetuosa, que se lleva el polvo de la tierra;
        De igual manera haces tú perecer la esperanza del hombre.
        20 Para siempre serás más fuerte que él, y él se va;
        Demudarás su rostro, y le despedirás.
        21 Sus hijos tendrán honores, pero él no lo sabrá;
        O serán humillados, y no entenderá de ello.
        22 Mas su carne sobre él se dolerá,
        Y se entristecerá en él su alma.
""".trimIndent(),
        "Hechos 5:1-16" to """
        Ananías y Safira
        1 Pero cierto hombre llamado Ananías, con Safira su mujer, vendió una heredad, 
        2 y sustrajo del precio, sabiéndolo también su mujer; y trayendo sólo una parte, la puso a los pies de los apóstoles. 
        3 Y dijo Pedro: Ananías, ¿por qué llenó Satanás tu corazón para que mintieses al Espíritu Santo, y sustrajeses del precio de la heredad? 
        4 Reteniéndola, ¿no se te quedaba a ti? y vendida, ¿no estaba en tu poder? ¿Por qué pusiste esto en tu corazón? No has mentido a los hombres, sino a Dios. 
        5 Al oír Ananías estas palabras, cayó y expiró. Y vino un gran temor sobre todos los que lo oyeron. 
        6 Y levantándose los jóvenes, lo envolvieron, y sacándolo, lo sepultaron.
        7 Pasado un lapso como de tres horas, sucedió que entró su mujer, no sabiendo lo que había acontecido. 
        8 Entonces Pedro le dijo: Dime, ¿vendisteis en tanto la heredad? Y ella dijo: Sí, en tanto. 
        9 Y Pedro le dijo: ¿Por qué convinisteis en tentar al Espíritu del Señor? He aquí a la puerta los pies de los que han sepultado a tu marido, y te sacarán a ti. 
        10 Al instante ella cayó a los pies de él, y expiró; y cuando entraron los jóvenes, la hallaron muerta; y la sacaron, y la sepultaron junto a su marido. 
        11 Y vino gran temor sobre toda la iglesia, y sobre todos los que oyeron estas cosas.
        Muchas señales y maravillas
        12 Y por la mano de los apóstoles se hacían muchas señales y prodigios en el pueblo; y estaban todos unánimes en el pórtico de Salomón. 
        13 De los demás, ninguno se atrevía a juntarse con ellos; mas el pueblo los alababa grandemente. 
        14 Y los que creían en el Señor aumentaban más, gran número así de hombres como de mujeres; 
        15 tanto que sacaban los enfermos a las calles, y los ponían en camas y lechos, para que al pasar Pedro, a lo menos su sombra cayese sobre alguno de ellos. 
        16 Y aun de las ciudades vecinas muchos venían a Jerusalén, trayendo enfermos y atormentados de espíritus inmundos; y todos eran sanados.
""".trimIndent(),
        "Deuteronomio 23-24" to """
        Deuteronomio 23
        Los excluidos de la congregación
        1 No entrará en la congregación de Jehová el que tenga magullados los testículos, o amputado su miembro viril.
        2 No entrará bastardo en la congregación de Jehová; ni hasta la décima generación no entrarán en la congregación de Jehová.
        3 No entrará amonita ni moabita en la congregación de Jehová, ni hasta la décima generación de ellos; no entrarán en la congregación de Jehová para siempre, 
        4 por cuanto no os salieron a recibir con pan y agua al camino, cuando salisteis de Egipto, y porque alquilaron contra ti a Balaam hijo de Beor, de Petor en Mesopotamia, para maldecirte. 
        5 Mas no quiso Jehová tu Dios oír a Balaam; y Jehová tu Dios te convirtió la maldición en bendición, porque Jehová tu Dios te amaba. 
        6 No procurarás la paz de ellos ni su bien en todos los días para siempre.
        7 No aborrecerás al edomita, porque es tu hermano; no aborrecerás al egipcio, porque forastero fuiste en su tierra. 
        8 Los hijos que nacieren de ellos, en la tercera generación entrarán en la congregación de Jehová.
        Leyes sanitarias
        9 Cuando salieres a campaña contra tus enemigos, te guardarás de toda cosa mala.
        10 Si hubiere en medio de ti alguno que no fuere limpio, por razón de alguna impureza acontecida de noche, saldrá fuera del campamento, y no entrará en él. 
        11 Pero al caer la noche se lavará con agua, y cuando se hubiere puesto el sol, podrá entrar en el campamento.
        12 Tendrás un lugar fuera del campamento adonde salgas; 
        13 tendrás también entre tus armas una estaca; y cuando estuvieres allí fuera, cavarás con ella, y luego al volverte cubrirás tu excremento; 
        14 porque Jehová tu Dios anda en medio de tu campamento, para librarte y para entregar a tus enemigos delante de ti; por tanto, tu campamento ha de ser santo, para que él no vea en ti cosa inmunda, y se vuelva de en pos de ti.
        Leyes humanitarias
        15 No entregarás a su señor el siervo que se huyere a ti de su amo. 
        16 Morará contigo, en medio de ti, en el lugar que escogiere en alguna de tus ciudades, donde a bien tuviere; no le oprimirás.
        17 No haya ramera de entre las hijas de Israel, ni haya sodomita de entre los hijos de Israel. 
        18 No traerás la paga de una ramera ni el precio de un perro a la casa de Jehová tu Dios por ningún voto; porque abominación es a Jehová tu Dios tanto lo uno como lo otro.
        19 No exigirás de tu hermano interés de dinero, ni interés de comestibles, ni de cosa alguna de que se suele exigir interés. 
        20 Del extraño podrás exigir interés, mas de tu hermano no lo exigirás, para que te bendiga Jehová tu Dios en toda obra de tus manos en la tierra adonde vas para tomar posesión de ella.
        21 Cuando haces voto a Jehová tu Dios, no tardes en pagarlo; porque ciertamente lo demandará Jehová tu Dios de ti, y sería pecado en ti. 
        22 Mas cuando te abstengas de prometer, no habrá en ti pecado. 
        23 Pero lo que hubiere salido de tus labios, lo guardarás y lo cumplirás, conforme lo prometiste a Jehová tu Dios, pagando la ofrenda voluntaria que prometiste con tu boca.
        24 Cuando entres en la viña de tu prójimo, podrás comer uvas hasta saciarte; mas no pondrás en tu cesto. 
        25 Cuando entres en la mies de tu prójimo, podrás arrancar espigas con tu mano; mas no aplicarás hoz a la mies de tu prójimo.
        Deuteronomio 24
        1 Cuando alguno tomare mujer y se casare con ella, si no le agradare por haber hallado en ella alguna cosa indecente, le escribirá carta de divorcio, y se la entregará en su mano, y la despedirá de su casa. 
        2 Y salida de su casa, podrá ir y casarse con otro hombre. 
        3 Pero si la aborreciere este último, y le escribiere carta de divorcio, y se la entregare en su mano, y la despidiere de su casa; o si hubiere muerto el postrer hombre que la tomó por mujer, 
        4 no podrá su primer marido, que la despidió, volverla a tomar para que sea su mujer, después que fue envilecida; porque es abominación delante de Jehová, y no has de pervertir la tierra que Jehová tu Dios te da por heredad.
        5 Cuando alguno fuere recién casado, no saldrá a la guerra, ni en ninguna cosa se le ocupará; libre estará en su casa por un año, para alegrar a la mujer que tomó.
        6 No tomarás en prenda la muela del molino, ni la de abajo ni la de arriba; porque sería tomar en prenda la vida del hombre.
        7 Cuando fuere hallado alguno que hubiere hurtado a uno de sus hermanos los hijos de Israel, y le hubiere esclavizado, o le hubiere vendido, morirá el tal ladrón, y quitarás el mal de en medio de ti.
        8 En cuanto a la plaga de la lepra, ten cuidado de observar diligentemente y hacer según todo lo que os enseñaren los sacerdotes levitas; según yo les he mandado, así cuidaréis de hacer. 
        9 Acuérdate de lo que hizo Jehová tu Dios a María en el camino, después que salisteis de Egipto.
        10 Cuando entregares a tu prójimo alguna cosa prestada, no entrarás en su casa para tomarle prenda. 
        11 Te quedarás fuera, y el hombre a quien prestaste te sacará la prenda. 
        12 Y si el hombre fuere pobre, no te acostarás reteniendo aún su prenda. 
        13 Sin falta le devolverás la prenda cuando el sol se ponga, para que pueda dormir en su ropa, y te bendiga; y te será justicia delante de Jehová tu Dios.
        14 No oprimirás al jornalero pobre y menesteroso, ya sea de tus hermanos o de los extranjeros que habitan en tu tierra dentro de tus ciudades. 
        15 En su día le darás su jornal, y no se pondrá el sol sin dárselo; pues es pobre, y con él sustenta su vida; para que no clame contra ti a Jehová, y sea en ti pecado.
        16 Los padres no morirán por los hijos, ni los hijos por los padres; cada uno morirá por su pecado.
        17 No torcerás el derecho del extranjero ni del huérfano, ni tomarás en prenda la ropa de la viuda, 
        18 sino que te acordarás que fuiste siervo en Egipto, y que de allí te rescató Jehová tu Dios; por tanto, yo te mando que hagas esto.
        19 Cuando siegues tu mies en tu campo, y olvides alguna gavilla en el campo, no volverás para recogerla; será para el extranjero, para el huérfano y para la viuda; para que te bendiga Jehová tu Dios en toda obra de tus manos. 
        20 Cuando sacudas tus olivos, no recorrerás las ramas que hayas dejado tras de ti; serán para el extranjero, para el huérfano y para la viuda. 
        21 Cuando vendimies tu viña, no rebuscarás tras de ti; será para el extranjero, para el huérfano y para la viuda. 
        22 Y acuérdate que fuiste siervo en tierra de Egipto; por tanto, yo te mando que hagas esto.
""".trimIndent(),
        "Job 15" to """
        Elifaz reprende a Job
        1 Respondió Elifaz temanita, y dijo:
        2 ¿Proferirá el sabio vana sabiduría,
        Y llenará su vientre de viento solano?
        3 ¿Disputará con palabras inútiles,
        Y con razones sin provecho?
        4 Tú también disipas el temor,
        Y menoscabas la oración delante de Dios.
        5 Porque tu boca declaró tu iniquidad,
        Pues has escogido el hablar de los astutos.
        6 Tu boca te condenará, y no yo;
        Y tus labios testificarán contra ti.
        7 ¿Naciste tú primero que Adán?
        ¿O fuiste formado antes que los collados?
        8 ¿Oíste tú el secreto de Dios,
        Y está limitada a ti la sabiduría?
        9 ¿Qué sabes tú que no sepamos?
        ¿Qué entiendes tú que no se halle en nosotros?
        10 Cabezas canas y hombres muy ancianos hay entre nosotros,
        Mucho más avanzados en días que tu padre.
        11 ¿En tan poco tienes las consolaciones de Dios,
        Y las palabras que con dulzura se te dicen?
        12 ¿Por qué tu corazón te aleja,
        Y por qué guiñan tus ojos,
        13 Para que contra Dios vuelvas tu espíritu,
        Y saques tales palabras de tu boca?
        14 ¿Qué cosa es el hombre para que sea limpio,
        Y para que se justifique el nacido de mujer?
        15 He aquí, en sus santos no confía,
        Y ni aun los cielos son limpios delante de sus ojos;
        16 ¿Cuánto menos el hombre abominable y vil,
        Que bebe la iniquidad como agua?
        17 Escúchame; yo te mostraré,
        Y te contaré lo que he visto;
        18 Lo que los sabios nos contaron
        De sus padres, y no lo encubrieron;
        19 A quienes únicamente fue dada la tierra,
        Y no pasó extraño por en medio de ellos.
        20 Todos sus días, el impío es atormentado de dolor,
        Y el número de sus años está escondido para el violento.
        21 Estruendos espantosos hay en sus oídos;
        En la prosperidad el asolador vendrá sobre él.
        22 Él no cree que volverá de las tinieblas,
        Y descubierto está para la espada.
        23 Vaga alrededor tras el pan, diciendo: ¿En dónde está?
        Sabe que le está preparado día de tinieblas.
        24 Tribulación y angustia le turbarán,
        Y se esforzarán contra él como un rey dispuesto para la batalla,
        25 Por cuanto él extendió su mano contra Dios,
        Y se portó con soberbia contra el Todopoderoso.
        26 Corrió contra él con cuello erguido,
        Con la espesa barrera de sus escudos.
        27 Porque la gordura cubrió su rostro,
        E hizo pliegues sobre sus ijares;
        28 Y habitó las ciudades asoladas,
        Las casas inhabitadas,
        Que estaban en ruinas.
        29 No prosperará, ni durarán sus riquezas,
        Ni extenderá por la tierra su hermosura.
        30 No escapará de las tinieblas;
        La llama secará sus ramas,
        Y con el aliento de su boca perecerá.
        31 No confíe el iluso en la vanidad,
        Porque ella será su recompensa.
        32 Él será cortado antes de su tiempo,
        Y sus renuevos no reverdecerán.
        33 Perderá su agraz como la vid,
        Y derramará su flor como el olivo.
        34 Porque la congregación de los impíos será asolada,
        Y fuego consumirá las tiendas de soborno.
        35 Concibieron dolor, dieron a luz iniquidad,
        Y en sus entrañas traman engaño.
""".trimIndent(),
        "Hechos 5:17-42" to """
        Pedro y Juan son perseguidos
        17 Entonces levantándose el sumo sacerdote y todos los que estaban con él, esto es, la secta de los saduceos, se llenaron de celos; 
        18 y echaron mano a los apóstoles y los pusieron en la cárcel pública. 
        19 Mas un ángel del Señor, abriendo de noche las puertas de la cárcel y sacándolos, dijo: 
        20 Id, y puestos en pie en el templo, anunciad al pueblo todas las palabras de esta vida. 
        21 Habiendo oído esto, entraron de mañana en el templo, y enseñaban.
        Entre tanto, vinieron el sumo sacerdote y los que estaban con él, y convocaron al concilio y a todos los ancianos de los hijos de Israel, y enviaron a la cárcel para que fuesen traídos. 
        22 Pero cuando llegaron los alguaciles, no los hallaron en la cárcel; entonces volvieron y dieron aviso, 
        23 diciendo: Por cierto, la cárcel hemos hallado cerrada con toda seguridad, y los guardas afuera de pie ante las puertas; mas cuando abrimos, a nadie hallamos dentro. 
        24 Cuando oyeron estas palabras el sumo sacerdote y el jefe de la guardia del templo y los principales sacerdotes, dudaban en qué vendría a parar aquello. 
        25 Pero viniendo uno, les dio esta noticia: He aquí, los varones que pusisteis en la cárcel están en el templo, y enseñan al pueblo. 
        26 Entonces fue el jefe de la guardia con los alguaciles, y los trajo sin violencia, porque temían ser apedreados por el pueblo.
        27 Cuando los trajeron, los presentaron en el concilio, y el sumo sacerdote les preguntó, 
        28 diciendo: ¿No os mandamos estrictamente que no enseñaseis en ese nombre? Y ahora habéis llenado a Jerusalén de vuestra doctrina, y queréis echar sobre nosotros la sangre de ese hombre. 
        29 Respondiendo Pedro y los apóstoles, dijeron: Es necesario obedecer a Dios antes que a los hombres. 
        30 El Dios de nuestros padres levantó a Jesús, a quien vosotros matasteis colgándole en un madero. 
        31 A este, Dios ha exaltado con su diestra por Príncipe y Salvador, para dar a Israel arrepentimiento y perdón de pecados. 
        32 Y nosotros somos testigos suyos de estas cosas, y también el Espíritu Santo, el cual ha dado Dios a los que le obedecen.
        33 Ellos, oyendo esto, se enfurecían y querían matarlos. 
        34 Entonces levantándose en el concilio un fariseo llamado Gamaliel, doctor de la ley, venerado de todo el pueblo, mandó que sacasen fuera por un momento a los apóstoles, 
        35 y luego dijo: Varones israelitas, mirad por vosotros lo que vais a hacer respecto a estos hombres. 
        36 Porque antes de estos días se levantó Teudas, diciendo que era alguien. A este se unió un número como de cuatrocientos hombres; pero él fue muerto, y todos los que le obedecían fueron dispersados y reducidos a nada. 
        37 Después de este, se levantó Judas el galileo, en los días del censo, y llevó en pos de sí a mucho pueblo. Pereció también él, y todos los que le obedecían fueron dispersados. 
        38 Y ahora os digo: Apartaos de estos hombres, y dejadlos; porque si este consejo o esta obra es de los hombres, se desvanecerá; 
        39 mas si es de Dios, no la podréis destruir; no seáis tal vez hallados luchando contra Dios.
        40 Y convinieron con él; y llamando a los apóstoles, después de azotarlos, les intimaron que no hablasen en el nombre de Jesús, y los pusieron en libertad. 
        41 Y ellos salieron de la presencia del concilio, gozosos de haber sido tenidos por dignos de padecer afrenta por causa del Nombre. 
        42 Y todos los días, en el templo y por las casas, no cesaban de enseñar y predicar a Jesucristo.
""".trimIndent(),
        "Deuteronomio 25-27" to """
        Deuteronomio 25
        1 Si hubiere pleito entre algunos, y acudieren al tribunal para que los jueces los juzguen, estos absolverán al justo, y condenarán al culpable. 
        2 Y si el delincuente mereciere ser azotado, entonces el juez le hará echar en tierra, y le hará azotar en su presencia; según su delito será el número de azotes. 
        3 Se podrá dar cuarenta azotes, no más; no sea que, si lo hirieren con muchos azotes más que estos, se sienta tu hermano envilecido delante de tus ojos.
        4 No pondrás bozal al buey cuando trillare.
        5 Cuando hermanos habitaren juntos, y muriere alguno de ellos, y no tuviere hijo, la mujer del muerto no se casará fuera con hombre extraño; su cuñado se llegará a ella, y la tomará por su mujer, y hará con ella parentesco. 
        6 Y el primogénito que ella diere a luz sucederá en el nombre de su hermano muerto, para que el nombre de este no sea borrado de Israel. 
        7 Y si el hombre no quisiere tomar a su cuñada, irá entonces su cuñada a la puerta, a los ancianos, y dirá: Mi cuñado no quiere suscitar nombre en Israel a su hermano; no quiere emparentar conmigo. 
        8 Entonces los ancianos de aquella ciudad lo harán venir, y hablarán con él; y si él se levantare y dijere: No quiero tomarla, 
        9 se acercará entonces su cuñada a él delante de los ancianos, y le quitará el calzado del pie, y le escupirá en el rostro, y hablará y dirá: Así será hecho al varón que no quiere edificar la casa de su hermano. 
        10 Y se le dará este nombre en Israel: La casa del descalzado.
        11 Si algunos riñeren uno con otro, y se acercare la mujer de uno para librar a su marido de mano del que le hiere, y alargando su mano asiere de sus partes vergonzosas, 
        12 le cortarás entonces la mano; no la perdonarás.
        13 No tendrás en tu bolsa pesa grande y pesa chica, 
        14 ni tendrás en tu casa efa grande y efa pequeño. 
        15 Pesa exacta y justa tendrás; efa cabal y justo tendrás, para que tus días sean prolongados sobre la tierra que Jehová tu Dios te da. 
        16 Porque abominación es a Jehová tu Dios cualquiera que hace esto, y cualquiera que hace injusticia.
        Orden de exterminar a Amalec
        17 Acuérdate de lo que hizo Amalec contigo en el camino, cuando salías de Egipto; 
        18 de cómo te salió al encuentro en el camino, y te desbarató la retaguardia de todos los débiles que iban detrás de ti, cuando tú estabas cansado y trabajado; y no tuvo ningún temor de Dios. 
        19 Por tanto, cuando Jehová tu Dios te dé descanso de todos tus enemigos alrededor, en la tierra que Jehová tu Dios te da por heredad para que la poseas, borrarás la memoria de Amalec de debajo del cielo; no lo olvides.
        Deuteronomio 26
        Primicias y diezmos
        26 Cuando hayas entrado en la tierra que Jehová tu Dios te da por herencia, y tomes posesión de ella y la habites, 
        2 entonces tomarás de las primicias de todos los frutos que sacares de la tierra que Jehová tu Dios te da, y las pondrás en una canasta, e irás al lugar que Jehová tu Dios escogiere para hacer habitar allí su nombre. 
        3 Y te presentarás al sacerdote que hubiere en aquellos días, y le dirás: Declaro hoy a Jehová tu Dios, que he entrado en la tierra que juró Jehová a nuestros padres que nos daría. 
        4 Y el sacerdote tomará la canasta de tu mano, y la pondrá delante del altar de Jehová tu Dios.
        5 Entonces hablarás y dirás delante de Jehová tu Dios: Un arameo a punto de perecer fue mi padre, el cual descendió a Egipto y habitó allí con pocos hombres, y allí creció y llegó a ser una nación grande, fuerte y numerosa; 
        6 y los egipcios nos maltrataron y nos afligieron, y pusieron sobre nosotros dura servidumbre. 
        7 Y clamamos a Jehová el Dios de nuestros padres; y Jehová oyó nuestra voz, y vio nuestra aflicción, nuestro trabajo y nuestra opresión; 
        8 y Jehová nos sacó de Egipto con mano fuerte, con brazo extendido, con grande espanto, y con señales y con milagros; 
        9 y nos trajo a este lugar, y nos dio esta tierra, tierra que fluye leche y miel. 
        10 Y ahora, he aquí he traído las primicias del fruto de la tierra que me diste, oh Jehová. Y lo dejarás delante de Jehová tu Dios, y adorarás delante de Jehová tu Dios. 
        11 Y te alegrarás en todo el bien que Jehová tu Dios te haya dado a ti y a tu casa, así tú como el levita y el extranjero que está en medio de ti.
        12 Cuando acabes de diezmar todo el diezmo de tus frutos en el año tercero, el año del diezmo, darás también al levita, al extranjero, al huérfano y a la viuda; y comerán en tus aldeas, y se saciarán. 
        13 Y dirás delante de Jehová tu Dios: He sacado lo consagrado de mi casa, y también lo he dado al levita, al extranjero, al huérfano y a la viuda, conforme a todo lo que me has mandado; no he transgredido tus mandamientos, ni me he olvidado de ellos. 
        14 No he comido de ello en mi luto, ni he gastado de ello estando yo inmundo, ni de ello he ofrecido a los muertos; he obedecido a la voz de Jehová mi Dios, he hecho conforme a todo lo que me has mandado. 
        15 Mira desde tu morada santa, desde el cielo, y bendice a tu pueblo Israel, y a la tierra que nos has dado, como juraste a nuestros padres, tierra que fluye leche y miel.
        16 Jehová tu Dios te manda hoy que cumplas estos estatutos y decretos; cuida, pues, de ponerlos por obra con todo tu corazón y con toda tu alma. 
        17 Has declarado solemnemente hoy que Jehová es tu Dios, y que andarás en sus caminos, y guardarás sus estatutos, sus mandamientos y sus decretos, y que escucharás su voz. 
        18 Y Jehová ha declarado hoy que tú eres pueblo suyo, de su exclusiva posesión, como te lo ha prometido, para que guardes todos sus mandamientos; 
        19 a fin de exaltarte sobre todas las naciones que hizo, para loor y fama y gloria, y para que seas un pueblo santo a Jehová tu Dios, como él ha dicho.
        Deuteronomio 27
        Orden de escribir la ley en piedras sobre el Monte Ebal
        1 Ordenó Moisés, con los ancianos de Israel, al pueblo, diciendo: Guardaréis todos los mandamientos que yo os prescribo hoy. 
        2 Y el día que pases el Jordán a la tierra que Jehová tu Dios te da, levantarás piedras grandes, y las revocarás con cal; 
        3 y escribirás en ellas todas las palabras de esta ley, cuando hayas pasado para entrar en la tierra que Jehová tu Dios te da, tierra que fluye leche y miel, como Jehová el Dios de tus padres te ha dicho. 
        4 Cuando, pues, hayas pasado el Jordán, levantarás estas piedras que yo os mando hoy, en el monte Ebal, y las revocarás con cal; 
        5 y edificarás allí un altar a Jehová tu Dios, altar de piedras; no alzarás sobre ellas instrumento de hierro. 
        6 De piedras enteras edificarás el altar de Jehová tu Dios, y ofrecerás sobre él holocausto a Jehová tu Dios; 
        7 y sacrificarás ofrendas de paz, y comerás allí, y te alegrarás delante de Jehová tu Dios. 
        8 Y escribirás muy claramente en las piedras todas las palabras de esta ley.
        9 Y Moisés, con los sacerdotes levitas, habló a todo Israel, diciendo: Guarda silencio y escucha, oh Israel; hoy has venido a ser pueblo de Jehová tu Dios. 
        10 Oirás, pues, la voz de Jehová tu Dios, y cumplirás sus mandamientos y sus estatutos, que yo te ordeno hoy.
        Las maldiciones en el monte Ebal
        11 Y mandó Moisés al pueblo en aquel día, diciendo: 12 Cuando hayas pasado el Jordán, estos estarán sobre el monte Gerizim para bendecir al pueblo: Simeón, Leví, Judá, Isacar, José y Benjamín. 
        13 Y estos estarán sobre el monte Ebal para pronunciar la maldición: Rubén, Gad, Aser, Zabulón, Dan y Neftalí. 
        14 Y hablarán los levitas, y dirán a todo varón de Israel en alta voz:
        15 Maldito el hombre que hiciere escultura o imagen de fundición, abominación a Jehová, obra de mano de artífice, y la pusiere en oculto. Y todo el pueblo responderá y dirá: Amén.
        16 Maldito el que deshonrare a su padre o a su madre. Y dirá todo el pueblo: Amén.
        17 Maldito el que redujere el límite de su prójimo. Y dirá todo el pueblo: Amén.
        18 Maldito el que hiciere errar al ciego en el camino. Y dirá todo el pueblo: Amén.
        19 Maldito el que pervirtiere el derecho del extranjero, del huérfano y de la viuda. Y dirá todo el pueblo: Amén.
        20 Maldito el que se acostare con la mujer de su padre, por cuanto descubrió el regazo de su padre. Y dirá todo el pueblo: Amén.
        21 Maldito el que se ayuntare con cualquier bestia. Y dirá todo el pueblo: Amén.
        22 Maldito el que se acostare con su hermana, hija de su padre, o hija de su madre. Y dirá todo el pueblo: Amén.
        23 Maldito el que se acostare con su suegra. Y dirá todo el pueblo: Amén.
        24 Maldito el que hiriere a su prójimo ocultamente. Y dirá todo el pueblo: Amén.
        25 Maldito el que recibiere soborno para quitar la vida al inocente. Y dirá todo el pueblo: Amén.
        26 Maldito el que no confirmare las palabras de esta ley para hacerlas. Y dirá todo el pueblo: Amén.
""".trimIndent(),
        "Job 16" to """
        Job se queja contra Dios
        1 Respondió Job, y dijo:
        2 Muchas veces he oído cosas como estas;
        Consoladores molestos sois todos vosotros.
        3 ¿Tendrán fin las palabras vacías?
        ¿O qué te anima a responder?
        4 También yo podría hablar como vosotros,
        Si vuestra alma estuviera en lugar de la mía;
        Yo podría hilvanar contra vosotros palabras,
        Y sobre vosotros mover mi cabeza.
        5 Pero yo os alentaría con mis palabras,
        Y la consolación de mis labios apaciguaría vuestro dolor.
        6 Si hablo, mi dolor no cesa;
        Y si dejo de hablar, no se aparta de mí.
        7 Pero ahora tú me has fatigado;
        Has asolado toda mi compañía.
        8 Tú me has llenado de arrugas; testigo es mi flacura,
        Que se levanta contra mí para testificar en mi rostro.
        9 Su furor me despedazó, y me ha sido contrario;
        Crujió sus dientes contra mí;
        Contra mí aguzó sus ojos mi enemigo.
        10 Abrieron contra mí su boca;
        Hirieron mis mejillas con afrenta;
        Contra mí se juntaron todos.
        11 Me ha entregado Dios al mentiroso,
        Y en las manos de los impíos me hizo caer.
        12 Próspero estaba, y me desmenuzó;
        Me arrebató por la cerviz y me despedazó,
        Y me puso por blanco suyo.
        13 Me rodearon sus flecheros,
        Partió mis riñones, y no perdonó;
        Mi hiel derramó por tierra.
        14 Me quebrantó de quebranto en quebranto;
        Corrió contra mí como un gigante.
        15 Cosí cilicio sobre mi piel,
        Y puse mi cabeza en el polvo.
        16 Mi rostro está inflamado con el lloro,
        Y mis párpados entenebrecidos,
        17 A pesar de no haber iniquidad en mis manos,
        Y de haber sido mi oración pura.
        18 ¡Oh tierra! no cubras mi sangre,
        Y no haya lugar para mi clamor.
        19 Mas he aquí que en los cielos está mi testigo,
        Y mi testimonio en las alturas.
        20 Disputadores son mis amigos;
        Mas ante Dios derramaré mis lágrimas.
        21 ¡Ojalá pudiese disputar el hombre con Dios,
        Como con su prójimo!
        22 Mas los años contados vendrán,
        Y yo iré por el camino de donde no volveré.
""".trimIndent(),
        "Hechos 6" to """
        Elección de siete diáconos
        1 En aquellos días, como creciera el número de los discípulos, hubo murmuración de los griegos contra los hebreos, de que las viudas de aquellos eran desatendidas en la distribución diaria. 
        2 Entonces los doce convocaron a la multitud de los discípulos, y dijeron: No es justo que nosotros dejemos la palabra de Dios, para servir a las mesas. 
        3 Buscad, pues, hermanos, de entre vosotros a siete varones de buen testimonio, llenos del Espíritu Santo y de sabiduría, a quienes encarguemos de este trabajo. 
        4 Y nosotros persistiremos en la oración y en el ministerio de la palabra. 
        5 Agradó la propuesta a toda la multitud; y eligieron a Esteban, varón lleno de fe y del Espíritu Santo, a Felipe, a Prócoro, a Nicanor, a Timón, a Parmenas, y a Nicolás prosélito de Antioquía; 
        6 a los cuales presentaron ante los apóstoles, quienes, orando, les impusieron las manos.
        7 Y crecía la palabra del Señor, y el número de los discípulos se multiplicaba grandemente en Jerusalén; también muchos de los sacerdotes obedecían a la fe.
        Arresto de Esteban
        8 Y Esteban, lleno de gracia y de poder, hacía grandes prodigios y señales entre el pueblo. 
        9 Entonces se levantaron unos de la sinagoga llamada de los libertos, y de los de Cirene, de Alejandría, de Cilicia y de Asia, disputando con Esteban. 
        10 Pero no podían resistir a la sabiduría y al Espíritu con que hablaba. 
        11 Entonces sobornaron a unos para que dijesen que le habían oído hablar palabras blasfemas contra Moisés y contra Dios. 
        12 Y soliviantaron al pueblo, a los ancianos y a los escribas; y arremetiendo, le arrebataron, y le trajeron al concilio. 
        13 Y pusieron testigos falsos que decían: Este hombre no cesa de hablar palabras blasfemas contra este lugar santo y contra la ley; 
        14 pues le hemos oído decir que ese Jesús de Nazaret destruirá este lugar, y cambiará las costumbres que nos dio Moisés. 
        15 Entonces todos los que estaban sentados en el concilio, al fijar los ojos en él, vieron su rostro como el rostro de un ángel.
""".trimIndent(),
        "Deuteronomio 28" to """
        Bendiciones de la obediencia
        1 Acontecerá que si oyeres atentamente la voz de Jehová tu Dios, para guardar y poner por obra todos sus mandamientos que yo te prescribo hoy, también Jehová tu Dios te exaltará sobre todas las naciones de la tierra. 
        2 Y vendrán sobre ti todas estas bendiciones, y te alcanzarán, si oyeres la voz de Jehová tu Dios. 
        3 Bendito serás tú en la ciudad, y bendito tú en el campo. 
        4 Bendito el fruto de tu vientre, el fruto de tu tierra, el fruto de tus bestias, la cría de tus vacas y los rebaños de tus ovejas. 
        5 Benditas serán tu canasta y tu artesa de amasar. 
        6 Bendito serás en tu entrar, y bendito en tu salir.
        7 Jehová derrotará a tus enemigos que se levantaren contra ti; por un camino saldrán contra ti, y por siete caminos huirán de delante de ti. 
        8 Jehová te enviará su bendición sobre tus graneros, y sobre todo aquello en que pusieres tu mano; y te bendecirá en la tierra que Jehová tu Dios te da. 
        9 Te confirmará Jehová por pueblo santo suyo, como te lo ha jurado, cuando guardares los mandamientos de Jehová tu Dios, y anduvieres en sus caminos. 
        10 Y verán todos los pueblos de la tierra que el nombre de Jehová es invocado sobre ti, y te temerán. 
        11 Y te hará Jehová sobreabundar en bienes, en el fruto de tu vientre, en el fruto de tu bestia, y en el fruto de tu tierra, en el país que Jehová juró a tus padres que te había de dar. 
        12 Te abrirá Jehová su buen tesoro, el cielo, para enviar la lluvia a tu tierra en su tiempo, y para bendecir toda obra de tus manos. Y prestarás a muchas naciones, y tú no pedirás prestado. 
        13 Te pondrá Jehová por cabeza, y no por cola; y estarás encima solamente, y no estarás debajo, si obedecieres los mandamientos de Jehová tu Dios, que yo te ordeno hoy, para que los guardes y cumplas, 
        14 y si no te apartares de todas las palabras que yo te mando hoy, ni a diestra ni a siniestra, para ir tras dioses ajenos y servirles.
        Consecuencias de la desobediencia
        15 Pero acontecerá, si no oyeres la voz de Jehová tu Dios, para procurar cumplir todos sus mandamientos y sus estatutos que yo te intimo hoy, que vendrán sobre ti todas estas maldiciones, y te alcanzarán. 
        16 Maldito serás tú en la ciudad, y maldito en el campo. 
        17 Maldita tu canasta, y tu artesa de amasar. 
        18 Maldito el fruto de tu vientre, el fruto de tu tierra, la cría de tus vacas, y los rebaños de tus ovejas. 
        19 Maldito serás en tu entrar, y maldito en tu salir.
        20 Y Jehová enviará contra ti la maldición, quebranto y asombro en todo cuanto pusieres mano e hicieres, hasta que seas destruido, y perezcas pronto a causa de la maldad de tus obras por las cuales me habrás dejado. 
        21 Jehová traerá sobre ti mortandad, hasta que te consuma de la tierra a la cual entras para tomar posesión de ella. 
        22 Jehová te herirá de tisis, de fiebre, de inflamación y de ardor, con sequía, con calamidad repentina y con añublo; y te perseguirán hasta que perezcas. 
        23 Y los cielos que están sobre tu cabeza serán de bronce, y la tierra que está debajo de ti, de hierro. 
        24 Dará Jehová por lluvia a tu tierra polvo y ceniza; de los cielos descenderán sobre ti hasta que perezcas.
        25 Jehová te entregará derrotado delante de tus enemigos; por un camino saldrás contra ellos, y por siete caminos huirás delante de ellos; y serás vejado por todos los reinos de la tierra. 
        26 Y tus cadáveres servirán de comida a toda ave del cielo y fiera de la tierra, y no habrá quien las espante. 
        27 Jehová te herirá con la úlcera de Egipto, con tumores, con sarna, y con comezón de que no puedas ser curado. 
        28 Jehová te herirá con locura, ceguera y turbación de espíritu; 
        29 y palparás a mediodía como palpa el ciego en la oscuridad, y no serás prosperado en tus caminos; y no serás sino oprimido y robado todos los días, y no habrá quien te salve. 
        30 Te desposarás con mujer, y otro varón dormirá con ella; edificarás casa, y no habitarás en ella; plantarás viña, y no la disfrutarás. 
        31 Tu buey será matado delante de tus ojos, y tú no comerás de él; tu asno será arrebatado de delante de ti, y no te será devuelto; tus ovejas serán dadas a tus enemigos, y no tendrás quien te las rescate. 
        32 Tus hijos y tus hijas serán entregados a otro pueblo, y tus ojos lo verán, y desfallecerán por ellos todo el día; y no habrá fuerza en tu mano. 
        33 El fruto de tu tierra y de todo tu trabajo comerá pueblo que no conociste; y no serás sino oprimido y quebrantado todos los días. 
        34 Y enloquecerás a causa de lo que verás con tus ojos. 
        35 Te herirá Jehová con maligna pústula en las rodillas y en las piernas, desde la planta de tu pie hasta tu coronilla, sin que puedas ser curado.
        36 Jehová te llevará a ti, y al rey que hubieres puesto sobre ti, a nación que no conociste ni tú ni tus padres; y allá servirás a dioses ajenos, al palo y a la piedra. 
        37 Y serás motivo de horror, y servirás de refrán y de burla a todos los pueblos a los cuales te llevará Jehová. 
        38 Sacarás mucha semilla al campo, y recogerás poco, porque la langosta lo consumirá. 
        39 Plantarás viñas y labrarás, pero no beberás vino, ni recogerás uvas, porque el gusano se las comerá. 
        40 Tendrás olivos en todo tu territorio, mas no te ungirás con el aceite, porque tu aceituna se caerá. 
        41 Hijos e hijas engendrarás, y no serán para ti, porque irán en cautiverio. 
        42 Toda tu arboleda y el fruto de tu tierra serán consumidos por la langosta. 
        43 El extranjero que estará en medio de ti se elevará sobre ti muy alto, y tú descenderás muy abajo. 
        44 Él te prestará a ti, y tú no le prestarás a él; él será por cabeza, y tú serás por cola. 
        45 Y vendrán sobre ti todas estas maldiciones, y te perseguirán, y te alcanzarán hasta que perezcas; por cuanto no habrás atendido a la voz de Jehová tu Dios, para guardar sus mandamientos y sus estatutos, que él te mandó; 
        46 y serán en ti por señal y por maravilla, y en tu descendencia para siempre.
        47 Por cuanto no serviste a Jehová tu Dios con alegría y con gozo de corazón, por la abundancia de todas las cosas, 
        48 servirás, por tanto, a tus enemigos que enviare Jehová contra ti, con hambre y con sed y con desnudez, y con falta de todas las cosas; y él pondrá yugo de hierro sobre tu cuello, hasta destruirte. 
        49 Jehová traerá contra ti una nación de lejos, del extremo de la tierra, que vuele como águila, nación cuya lengua no entiendas; 
        50 gente fiera de rostro, que no tendrá respeto al anciano, ni perdonará al niño; 
        51 y comerá el fruto de tu bestia y el fruto de tu tierra, hasta que perezcas; y no te dejará grano, ni mosto, ni aceite, ni la cría de tus vacas, ni los rebaños de tus ovejas, hasta destruirte. 
        52 Pondrá sitio a todas tus ciudades, hasta que caigan tus muros altos y fortificados en que tú confías, en toda tu tierra; sitiará, pues, todas tus ciudades y toda la tierra que Jehová tu Dios te hubiere dado. 
        53 Y comerás el fruto de tu vientre, la carne de tus hijos y de tus hijas que Jehová tu Dios te dio, en el sitio y en el apuro con que te angustiará tu enemigo. 
        54 El hombre tierno en medio de ti, y el muy delicado, mirará con malos ojos a su hermano, y a la mujer de su seno, y al resto de sus hijos que le quedaren; 
        55 para no dar a alguno de ellos de la carne de sus hijos, que él comiere, por no haberle quedado nada, en el asedio y en el apuro con que tu enemigo te oprimirá en todas tus ciudades. 
        56 La tierna y la delicada entre vosotros, que nunca la planta de su pie intentaría sentar sobre la tierra, de pura delicadeza y ternura, mirará con malos ojos al marido de su seno, a su hijo, a su hija, 
        57 al recién nacido que sale de entre sus pies, y a sus hijos que diere a luz; pues los comerá ocultamente, por la carencia de todo, en el asedio y en el apuro con que tu enemigo te oprimirá en tus ciudades.
        58 Si no cuidares de poner por obra todas las palabras de esta ley que están escritas en este libro, temiendo este nombre glorioso y temible: JEHOVÁ TU DIOS, 
        59 entonces Jehová aumentará maravillosamente tus plagas y las plagas de tu descendencia, plagas grandes y permanentes, y enfermedades malignas y duraderas; 
        60 y traerá sobre ti todos los males de Egipto, delante de los cuales temiste, y no te dejarán. 
        61 Asimismo toda enfermedad y toda plaga que no está escrita en el libro de esta ley, Jehová la enviará sobre ti, hasta que seas destruido. 
        62 Y quedaréis pocos en número, en lugar de haber sido como las estrellas del cielo en multitud, por cuanto no obedecisteis a la voz de Jehová tu Dios. 
        63 Así como Jehová se gozaba en haceros bien y en multiplicaros, así se gozará Jehová en arruinaros y en destruiros; y seréis arrancados de sobre la tierra a la cual entráis para tomar posesión de ella. 
        64 Y Jehová te esparcirá por todos los pueblos, desde un extremo de la tierra hasta el otro extremo; y allí servirás a dioses ajenos que no conociste tú ni tus padres, al leño y a la piedra. 
        65 Y ni aun entre estas naciones descansarás, ni la planta de tu pie tendrá reposo; pues allí te dará Jehová corazón temeroso, y desfallecimiento de ojos, y tristeza de alma; 
        66 y tendrás tu vida como algo que pende delante de ti, y estarás temeroso de noche y de día, y no tendrás seguridad de tu vida. 
        67 Por la mañana dirás: ¡Quién diera que fuese la tarde! y a la tarde dirás: ¡Quién diera que fuese la mañana! por el miedo de tu corazón con que estarás amedrentado, y por lo que verán tus ojos. 
        68 Y Jehová te hará volver a Egipto en naves, por el camino del cual te ha dicho: Nunca más volverás; y allí seréis vendidos a vuestros enemigos por esclavos y por esclavas, y no habrá quien os compre.
""".trimIndent(),
        "Job 17" to """
        1 Mi aliento se agota, se acortan mis días,
        Y me está preparado el sepulcro.
        2 No hay conmigo sino escarnecedores,
        En cuya amargura se detienen mis ojos.
        3 Dame fianza, oh Dios; sea mi protección cerca de ti.
        Porque ¿quién querría responder por mí?
        4 Porque a estos has escondido de su corazón la inteligencia;
        Por tanto, no los exaltarás.
        5 Al que denuncia a sus amigos como presa,
        Los ojos de sus hijos desfallecerán.
        6 Él me ha puesto por refrán de pueblos,
        Y delante de ellos he sido como tamboril.
        7 Mis ojos se oscurecieron por el dolor,
        Y mis pensamientos todos son como sombra.
        8 Los rectos se maravillarán de esto,
        Y el inocente se levantará contra el impío.
        9 No obstante, proseguirá el justo su camino,
        Y el limpio de manos aumentará la fuerza.
        10 Pero volved todos vosotros, y venid ahora,
        Y no hallaré entre vosotros sabio.
        11 Pasaron mis días, fueron arrancados mis pensamientos,
        Los designios de mi corazón.
        12 Pusieron la noche por día,
        Y la luz se acorta delante de las tinieblas.
        13 Si yo espero, el Seol es mi casa;
        Haré mi cama en las tinieblas.
        14 A la corrupción he dicho: Mi padre eres tú;
        A los gusanos: Mi madre y mi hermana.
        15 ¿Dónde, pues, estará ahora mi esperanza?
        Y mi esperanza, ¿quién la verá?
        16 A la profundidad del Seol descenderán,
        Y juntamente descansarán en el polvo.
""".trimIndent(),
        "Hechos 7:1-22" to """
        Defensa y muerte de Esteban
        7 El sumo sacerdote dijo entonces: ¿Es esto así? 2 Y él dijo:
        Varones hermanos y padres, oíd: El Dios de la gloria apareció a nuestro padre Abraham, estando en Mesopotamia, antes que morase en Harán, 
        3 y le dijo: Sal de tu tierra y de tu parentela, y ven a la tierra que yo te mostraré. 
        4 Entonces salió de la tierra de los caldeos y habitó en Harán; y de allí, muerto su padre, Dios le trasladó a esta tierra, en la cual vosotros habitáis ahora. 
        5 Y no le dio herencia en ella, ni aun para asentar un pie; pero le prometió que se la daría en posesión, y a su descendencia después de él, cuando él aún no tenía hijo. 
        6 Y le dijo Dios así: Que su descendencia sería extranjera en tierra ajena, y que los reducirían a servidumbre y los maltratarían, por cuatrocientos años. 
        7 Mas yo juzgaré, dijo Dios, a la nación de la cual serán siervos; y después de esto saldrán y me servirán en este lugar. 
        8 Y le dio el pacto de la circuncisión; y así Abraham engendró a Isaac, y le circuncidó al octavo día; e Isaac a Jacob, y Jacob a los doce patriarcas. 
        9 Los patriarcas, movidos por envidia, vendieron a José para Egipto; pero Dios estaba con él, 
        10 y le libró de todas sus tribulaciones, y le dio gracia y sabiduría delante de Faraón rey de Egipto, el cual lo puso por gobernador sobre Egipto y sobre toda su casa. 
        11 Vino entonces hambre en toda la tierra de Egipto y de Canaán, y grande tribulación; y nuestros padres no hallaban alimentos. 
        12 Cuando oyó Jacob que había trigo en Egipto, envió a nuestros padres la primera vez. 
        13 Y en la segunda, José se dio a conocer a sus hermanos, y fue manifestado a Faraón el linaje de José. 
        14 Y enviando José, hizo venir a su padre Jacob, y a toda su parentela, en número de setenta y cinco personas. 
        15 Así descendió Jacob a Egipto, donde murió él, y también nuestros padres; 
        16 los cuales fueron trasladados a Siquem, y puestos en el sepulcro que a precio de dinero compró Abraham de los hijos de Hamor en Siquem.
        17 Pero cuando se acercaba el tiempo de la promesa, que Dios había jurado a Abraham, el pueblo creció y se multiplicó en Egipto, 
        18 hasta que se levantó en Egipto otro rey que no conocía a José. 
        19 Este rey, usando de astucia con nuestro pueblo, maltrató a nuestros padres, a fin de que expusiesen a la muerte a sus niños, para que no se propagasen. 
        20 En aquel mismo tiempo nació Moisés, y fue agradable a Dios; y fue criado tres meses en casa de su padre. 
        21 Pero siendo expuesto a la muerte, la hija de Faraón le recogió y le crio como a hijo suyo. 
        22 Y fue enseñado Moisés en toda la sabiduría de los egipcios; y era poderoso en sus palabras y obras.
""".trimIndent(),
        "Deuteronomio 29-30" to """
        Deuteronomio 29
        Pacto de Jehová con Israel en Moab
        1 Estas son las palabras del pacto que Jehová mandó a Moisés que celebrase con los hijos de Israel en la tierra de Moab, además del pacto que concertó con ellos en Horeb.
        2 Moisés, pues, llamó a todo Israel, y les dijo: Vosotros habéis visto todo lo que Jehová ha hecho delante de vuestros ojos en la tierra de Egipto a Faraón y a todos sus siervos, y a toda su tierra, 
        3 las grandes pruebas que vieron vuestros ojos, las señales y las grandes maravillas. 
        4 Pero hasta hoy Jehová no os ha dado corazón para entender, ni ojos para ver, ni oídos para oír. 
        5 Y yo os he traído cuarenta años en el desierto; vuestros vestidos no se han envejecido sobre vosotros, ni vuestro calzado se ha envejecido sobre vuestro pie. 
        6 No habéis comido pan, ni bebisteis vino ni sidra; para que supierais que yo soy Jehová vuestro Dios. 
        7 Y llegasteis a este lugar, y salieron Sehón rey de Hesbón y Og rey de Basán delante de nosotros para pelear, y los derrotamos; 
        8 y tomamos su tierra, y la dimos por heredad a Rubén y a Gad y a la media tribu de Manasés. 
        9 Guardaréis, pues, las palabras de este pacto, y las pondréis por obra, para que prosperéis en todo lo que hiciereis.
        10 Vosotros todos estáis hoy en presencia de Jehová vuestro Dios; los cabezas de vuestras tribus, vuestros ancianos y vuestros oficiales, todos los varones de Israel; 
        11 vuestros niños, vuestras mujeres, y tus extranjeros que habitan en medio de tu campamento, desde el que corta tu leña hasta el que saca tu agua; 
        12 para que entres en el pacto de Jehová tu Dios, y en su juramento, que Jehová tu Dios concierta hoy contigo, 
        13 para confirmarte hoy como su pueblo, y para que él te sea a ti por Dios, de la manera que él te ha dicho, y como lo juró a tus padres Abraham, Isaac y Jacob. 
        14 Y no solamente con vosotros hago yo este pacto y este juramento, 
        15 sino con los que están aquí presentes hoy con nosotros delante de Jehová nuestro Dios, y con los que no están aquí hoy con nosotros.
        16 Porque vosotros sabéis cómo habitamos en la tierra de Egipto, y cómo hemos pasado por en medio de las naciones por las cuales habéis pasado; 
        17 y habéis visto sus abominaciones y sus ídolos de madera y piedra, de plata y oro, que tienen consigo. 
        18 No sea que haya entre vosotros varón o mujer, o familia o tribu, cuyo corazón se aparte hoy de Jehová nuestro Dios, para ir a servir a los dioses de esas naciones; no sea que haya en medio de vosotros raíz que produzca hiel y ajenjo, 
        19 y suceda que al oír las palabras de esta maldición, él se bendiga en su corazón, diciendo: Tendré paz, aunque ande en la dureza de mi corazón, a fin de que con la embriaguez quite la sed. 
        20 No querrá Jehová perdonarlo, sino que entonces humeará la ira de Jehová y su celo sobre el tal hombre, y se asentará sobre él toda maldición escrita en este libro, y Jehová borrará su nombre de debajo del cielo; 
        21 y lo apartará Jehová de todas las tribus de Israel para mal, conforme a todas las maldiciones del pacto escrito en este libro de la ley. 
        22 Y dirán las generaciones venideras, vuestros hijos que se levanten después de vosotros, y el extranjero que vendrá de lejanas tierras, cuando vieren las plagas de aquella tierra, y sus enfermedades de que Jehová la habrá hecho enfermar 
        23 (azufre y sal, abrasada toda su tierra; no será sembrada, ni producirá, ni crecerá en ella hierba alguna, como sucedió en la destrucción de Sodoma y de Gomorra, de Adma y de Zeboim, las cuales Jehová destruyó en su furor y en su ira); 
        24 más aún, todas las naciones dirán: ¿Por qué hizo esto Jehová a esta tierra? ¿Qué significa el ardor de esta gran ira? 
        25 Y responderán: Por cuanto dejaron el pacto de Jehová el Dios de sus padres, que él concertó con ellos cuando los sacó de la tierra de Egipto, 
        26 y fueron y sirvieron a dioses ajenos, y se inclinaron a ellos, dioses que no conocían, y que ninguna cosa les habían dado. 
        27 Por tanto, se encendió la ira de Jehová contra esta tierra, para traer sobre ella todas las maldiciones escritas en este libro; 
        28 y Jehová los desarraigó de su tierra con ira, con furor y con grande indignación, y los arrojó a otra tierra, como hoy se ve.
        29 Las cosas secretas pertenecen a Jehová nuestro Dios; mas las reveladas son para nosotros y para nuestros hijos para siempre, para que cumplamos todas las palabras de esta ley.
        Deuteronomio 30
        Condiciones para la restauración y la bendición
        1 Sucederá que cuando hubieren venido sobre ti todas estas cosas, la bendición y la maldición que he puesto delante de ti, y te arrepintieres en medio de todas las naciones adonde te hubiere arrojado Jehová tu Dios, 
        2 y te convirtieres a Jehová tu Dios, y obedecieres a su voz conforme a todo lo que yo te mando hoy, tú y tus hijos, con todo tu corazón y con toda tu alma, 
        3 entonces Jehová hará volver a tus cautivos, y tendrá misericordia de ti, y volverá a recogerte de entre todos los pueblos adonde te hubiere esparcido Jehová tu Dios. 
        4 Aun cuando tus desterrados estuvieren en las partes más lejanas que hay debajo del cielo, de allí te recogerá Jehová tu Dios, y de allá te tomará; 
        5 y te hará volver Jehová tu Dios a la tierra que heredaron tus padres, y será tuya; y te hará bien, y te multiplicará más que a tus padres. 
        6 Y circuncidará Jehová tu Dios tu corazón, y el corazón de tu descendencia, para que ames a Jehová tu Dios con todo tu corazón y con toda tu alma, a fin de que vivas. 
        7 Y pondrá Jehová tu Dios todas estas maldiciones sobre tus enemigos, y sobre tus aborrecedores que te persiguieron. 
        8 Y tú volverás, y oirás la voz de Jehová, y pondrás por obra todos sus mandamientos que yo te ordeno hoy. 
        9 Y te hará Jehová tu Dios abundar en toda obra de tus manos, en el fruto de tu vientre, en el fruto de tu bestia, y en el fruto de tu tierra, para bien; porque Jehová volverá a gozarse sobre ti para bien, de la manera que se gozó sobre tus padres, 
        10 cuando obedecieres a la voz de Jehová tu Dios, para guardar sus mandamientos y sus estatutos escritos en este libro de la ley; cuando te convirtieres a Jehová tu Dios con todo tu corazón y con toda tu alma.
        11 Porque este mandamiento que yo te ordeno hoy no es demasiado difícil para ti, ni está lejos. 
        12 No está en el cielo, para que digas: ¿Quién subirá por nosotros al cielo, y nos lo traerá y nos lo hará oír para que lo cumplamos? 
        13 Ni está al otro lado del mar, para que digas: ¿Quién pasará por nosotros el mar, para que nos lo traiga y nos lo haga oír, a fin de que lo cumplamos? 
        14 Porque muy cerca de ti está la palabra, en tu boca y en tu corazón, para que la cumplas.
        15 Mira, yo he puesto delante de ti hoy la vida y el bien, la muerte y el mal; 
        16 porque yo te mando hoy que ames a Jehová tu Dios, que andes en sus caminos, y guardes sus mandamientos, sus estatutos y sus decretos, para que vivas y seas multiplicado, y Jehová tu Dios te bendiga en la tierra a la cual entras para tomar posesión de ella. 
        17 Mas si tu corazón se apartare y no oyeres, y te dejares extraviar, y te inclinares a dioses ajenos y les sirvieres, 
        18 yo os protesto hoy que de cierto pereceréis; no prolongaréis vuestros días sobre la tierra adonde vais, pasando el Jordán, para entrar en posesión de ella. 
        19 A los cielos y a la tierra llamo por testigos hoy contra vosotros, que os he puesto delante la vida y la muerte, la bendición y la maldición; escoge, pues, la vida, para que vivas tú y tu descendencia; 
        20 amando a Jehová tu Dios, atendiendo a su voz, y siguiéndole a él; porque él es vida para ti, y prolongación de tus días; a fin de que habites sobre la tierra que juró Jehová a tus padres, Abraham, Isaac y Jacob, que les había de dar.
""".trimIndent(),
        "Job 18" to """
        Bildad describe la suerte de los malos
        18 Respondió Bildad suhita, y dijo:
        2 ¿Cuándo pondréis fin a las palabras?
        Entended, y después hablemos.
        3 ¿Por qué somos tenidos por bestias,
        Y a vuestros ojos somos viles?
        4 Oh tú, que te despedazas en tu furor,
        ¿Será abandonada la tierra por tu causa,
        Y serán removidas de su lugar las peñas?
        5 Ciertamente la luz de los impíos será apagada,
        Y no resplandecerá la centella de su fuego.
        6 La luz se oscurecerá en su tienda,
        Y se apagará sobre él su lámpara.
        7 Sus pasos vigorosos serán acortados,
        Y su mismo consejo lo precipitará.
        8 Porque red será echada a sus pies,
        Y sobre mallas andará.
        9 Lazo prenderá su calcañar;
        Se afirmará la trampa contra él.
        10 Su cuerda está escondida en la tierra,
        Y una trampa le aguarda en la senda.
        11 De todas partes lo asombrarán temores,
        Y le harán huir desconcertado.
        12 Serán gastadas de hambre sus fuerzas,
        Y a su lado estará preparado quebrantamiento.
        13 La enfermedad roerá su piel,
        Y a sus miembros devorará el primogénito de la muerte.
        14 Su confianza será arrancada de su tienda,
        Y al rey de los espantos será conducido.
        15 En su tienda morará como si no fuese suya;
        Piedra de azufre será esparcida sobre su morada.
        16 Abajo se secarán sus raíces,
        Y arriba serán cortadas sus ramas.
        17 Su memoria perecerá de la tierra,
        Y no tendrá nombre por las calles.
        18 De la luz será lanzado a las tinieblas,
        Y echado fuera del mundo.
        19 No tendrá hijo ni nieto en su pueblo,
        Ni quien le suceda en sus moradas.
        20 Sobre su día se espantarán los de occidente,
        Y pavor caerá sobre los de oriente.
        21 Ciertamente tales son las moradas del impío,
        Y este será el lugar del que no conoció a Dios.
""".trimIndent(),
        "Hechos 7:23-8:1" to """
        23 Cuando hubo cumplido la edad de cuarenta años, le vino al corazón el visitar a sus hermanos, los hijos de Israel. 
        24 Y al ver a uno que era maltratado, lo defendió, e hiriendo al egipcio, vengó al oprimido. 
        25 Pero él pensaba que sus hermanos comprendían que Dios les daría libertad por mano suya; mas ellos no lo habían entendido así. 
        26 Y al día siguiente, se presentó a unos de ellos que reñían, y los ponía en paz, diciendo: Varones, hermanos sois, ¿por qué os maltratáis el uno al otro? 
        27 Entonces el que maltrataba a su prójimo le rechazó, diciendo: ¿Quién te ha puesto por gobernante y juez sobre nosotros? 
        28 ¿Quieres tú matarme, como mataste ayer al egipcio? 
        29 Al oír esta palabra, Moisés huyó, y vivió como extranjero en tierra de Madián, donde engendró dos hijos.
        30 Pasados cuarenta años, un ángel se le apareció en el desierto del monte Sinaí, en la llama de fuego de una zarza. 
        31 Entonces Moisés, mirando, se maravilló de la visión; y acercándose para observar, vino a él la voz del Señor: 
        32 Yo soy el Dios de tus padres, el Dios de Abraham, el Dios de Isaac, y el Dios de Jacob. Y Moisés, temblando, no se atrevía a mirar. 
        33 Y le dijo el Señor: Quita el calzado de tus pies, porque el lugar en que estás es tierra santa. 
        34 Ciertamente he visto la aflicción de mi pueblo que está en Egipto, y he oído su gemido, y he descendido para librarlos. Ahora, pues, ven, te enviaré a Egipto.
        35 A este Moisés, a quien habían rechazado, diciendo: ¿Quién te ha puesto por gobernante y juez?, a este lo envió Dios como gobernante y libertador por mano del ángel que se le apareció en la zarza. 
        36 Este los sacó, habiendo hecho prodigios y señales en tierra de Egipto, y en el Mar Rojo, y en el desierto por cuarenta años. 
        37 Este Moisés es el que dijo a los hijos de Israel: Profeta os levantará el Señor vuestro Dios de entre vuestros hermanos, como a mí; a él oiréis. 
        38 Este es aquel Moisés que estuvo en la congregación en el desierto con el ángel que le hablaba en el monte Sinaí, y con nuestros padres, y que recibió palabras de vida que darnos; 
        39 al cual nuestros padres no quisieron obedecer, sino que le desecharon, y en sus corazones se volvieron a Egipto, 
        40 cuando dijeron a Aarón: Haznos dioses que vayan delante de nosotros; porque a este Moisés, que nos sacó de la tierra de Egipto, no sabemos qué le haya acontecido. 
        41 Entonces hicieron un becerro, y ofrecieron sacrificio al ídolo, y en las obras de sus manos se regocijaron. 
        42 Y Dios se apartó, y los entregó a que rindiesen culto al ejército del cielo; como está escrito en el libro de los profetas:
        ¿Acaso me ofrecisteis víctimas y sacrificios
        En el desierto por cuarenta años, casa de Israel?
        43 Antes bien llevasteis el tabernáculo de Moloc,
        Y la estrella de vuestro dios Renfán,
        Figuras que os hicisteis para adorarlas.
        Os transportaré, pues, más allá de Babilonia.
        44 Tuvieron nuestros padres el tabernáculo del testimonio en el desierto, como había ordenado Dios cuando dijo a Moisés que lo hiciese conforme al modelo que había visto. 
        45 El cual, recibido a su vez por nuestros padres, lo introdujeron con Josué al tomar posesión de la tierra de los gentiles, a los cuales Dios arrojó de la presencia de nuestros padres, hasta los días de David. 
        46 Este halló gracia delante de Dios, y pidió proveer tabernáculo para el Dios de Jacob. 
        47 Mas Salomón le edificó casa; 
        48 si bien el Altísimo no habita en templos hechos de mano, como dice el profeta:
        49 El cielo es mi trono,
        Y la tierra el estrado de mis pies.
        ¿Qué casa me edificaréis? dice el Señor;
        ¿O cuál es el lugar de mi reposo?
        50 ¿No hizo mi mano todas estas cosas?
        51 ¡Duros de cerviz, e incircuncisos de corazón y de oídos! Vosotros resistís siempre al Espíritu Santo; como vuestros padres, así también vosotros. 
        52 ¿A cuál de los profetas no persiguieron vuestros padres? Y mataron a los que anunciaron de antemano la venida del Justo, de quien vosotros ahora habéis sido entregadores y matadores; 
        53 vosotros que recibisteis la ley por disposición de ángeles, y no la guardasteis.
        54 Oyendo estas cosas, se enfurecían en sus corazones, y crujían los dientes contra él. 
        55 Pero Esteban, lleno del Espíritu Santo, puestos los ojos en el cielo, vio la gloria de Dios, y a Jesús que estaba a la diestra de Dios, 
        56 y dijo: He aquí, veo los cielos abiertos, y al Hijo del Hombre que está a la diestra de Dios. 
        57 Entonces ellos, dando grandes voces, se taparon los oídos, y arremetieron a una contra él. 
        58 Y echándole fuera de la ciudad, le apedrearon; y los testigos pusieron sus ropas a los pies de un joven que se llamaba Saulo. 
        59 Y apedreaban a Esteban, mientras él invocaba y decía: Señor Jesús, recibe mi espíritu. 
        60 Y puesto de rodillas, clamó a gran voz: Señor, no les tomes en cuenta este pecado. Y habiendo dicho esto, durmió.
        Hechos 8
        Saulo persigue a la iglesia
        1 Y Saulo consentía en su muerte. En aquel día hubo una gran persecución contra la iglesia que estaba en Jerusalén; y todos fueron esparcidos por las tierras de Judea y de Samaria, salvo los apóstoles.
""".trimIndent(),
        "Deuteronomio 31-32" to """
        Deuteronomio 31
        Josué es instalado como sucesor de Moisés
        1 Fue Moisés y habló estas palabras a todo Israel, 
        2 y les dijo: Este día soy de edad de ciento veinte años; no puedo más salir ni entrar; además de esto Jehová me ha dicho: No pasarás este Jordán. 
        3 Jehová tu Dios, él pasa delante de ti; él destruirá a estas naciones delante de ti, y las heredarás; Josué será el que pasará delante de ti, como Jehová ha dicho. 
        4 Y hará Jehová con ellos como hizo con Sehón y con Og, reyes de los amorreos, y con su tierra, a quienes destruyó. 
        5 Y los entregará Jehová delante de vosotros, y haréis con ellos conforme a todo lo que os he mandado. 
        6 Esforzaos y cobrad ánimo; no temáis, ni tengáis miedo de ellos, porque Jehová tu Dios es el que va contigo; no te dejará, ni te desamparará.
        7 Y llamó Moisés a Josué, y le dijo en presencia de todo Israel: Esfuérzate y anímate; porque tú entrarás con este pueblo a la tierra que juró Jehová a sus padres que les daría, y tú se la harás heredar. 
        8 Y Jehová va delante de ti; él estará contigo, no te dejará, ni te desamparará; no temas ni te intimides.
        9 Y escribió Moisés esta ley, y la dio a los sacerdotes hijos de Leví, que llevaban el arca del pacto de Jehová, y a todos los ancianos de Israel. 
        10 Y les mandó Moisés, diciendo: Al fin de cada siete años, en el año de la remisión, en la fiesta de los tabernáculos, 
        11 cuando viniere todo Israel a presentarse delante de Jehová tu Dios en el lugar que él escogiere, leerás esta ley delante de todo Israel a oídos de ellos. 
        12 Harás congregar al pueblo, varones y mujeres y niños, y tus extranjeros que estuvieren en tus ciudades, para que oigan y aprendan, y teman a Jehová vuestro Dios, y cuiden de cumplir todas las palabras de esta ley; 
        13 y los hijos de ellos que no supieron, oigan, y aprendan a temer a Jehová vuestro Dios todos los días que viviereis sobre la tierra adonde vais, pasando el Jordán, para tomar posesión de ella.
        14 Y Jehová dijo a Moisés: He aquí se ha acercado el día de tu muerte; llama a Josué, y esperad en el tabernáculo de reunión para que yo le dé el cargo. Fueron, pues, Moisés y Josué, y esperaron en el tabernáculo de reunión. 
        15 Y se apareció Jehová en el tabernáculo, en la columna de nube; y la columna de nube se puso sobre la puerta del tabernáculo.
        16 Y Jehová dijo a Moisés: He aquí, tú vas a dormir con tus padres, y este pueblo se levantará y fornicará tras los dioses ajenos de la tierra adonde va para estar en medio de ella; y me dejará, e invalidará mi pacto que he concertado con él; 
        17 y se encenderá mi furor contra él en aquel día; y los abandonaré, y esconderé de ellos mi rostro, y serán consumidos; y vendrán sobre ellos muchos males y angustias, y dirán en aquel día: ¿No me han venido estos males porque no está mi Dios en medio de mí? 
        18 Pero ciertamente yo esconderé mi rostro en aquel día, por todo el mal que ellos habrán hecho, por haberse vuelto a dioses ajenos. 
        19 Ahora pues, escribíos este cántico, y enséñalo a los hijos de Israel; ponlo en boca de ellos, para que este cántico me sea por testigo contra los hijos de Israel. 
        20 Porque yo les introduciré en la tierra que juré a sus padres, la cual fluye leche y miel; y comerán y se saciarán, y engordarán; y se volverán a dioses ajenos y les servirán, y me enojarán, e invalidarán mi pacto. 
        21 Y cuando les vinieren muchos males y angustias, entonces este cántico responderá en su cara como testigo, pues será recordado por la boca de sus descendientes; porque yo conozco lo que se proponen de antemano, antes que los introduzca en la tierra que juré darles. 
        22 Y Moisés escribió este cántico aquel día, y lo enseñó a los hijos de Israel.
        23 Y dio orden a Josué hijo de Nun, y dijo: Esfuérzate y anímate, pues tú introducirás a los hijos de Israel en la tierra que les juré, y yo estaré contigo.
        Orden de guardar la ley junto al arca
        24 Y cuando acabó Moisés de escribir las palabras de esta ley en un libro hasta concluirse, 
        25 dio órdenes Moisés a los levitas que llevaban el arca del pacto de Jehová, diciendo: 
        26 Tomad este libro de la ley, y ponedlo al lado del arca del pacto de Jehová vuestro Dios, y esté allí por testigo contra ti. 
        27 Porque yo conozco tu rebelión, y tu dura cerviz; he aquí que aun viviendo yo con vosotros hoy, sois rebeldes a Jehová; ¿cuánto más después que yo haya muerto? 
        28 Congregad a mí todos los ancianos de vuestras tribus, y a vuestros oficiales, y hablaré en sus oídos estas palabras, y llamaré por testigos contra ellos a los cielos y a la tierra. 
        29 Porque yo sé que después de mi muerte, ciertamente os corromperéis y os apartaréis del camino que os he mandado; y que os ha de venir mal en los postreros días, por haber hecho mal ante los ojos de Jehová, enojándole con la obra de vuestras manos.
        Cántico de Moisés
        30 Entonces habló Moisés a oídos de toda la congregación de Israel las palabras de este cántico hasta acabarlo.
        Deuteronomio 32
        1 Escuchad, cielos, y hablaré;
        Y oiga la tierra los dichos de mi boca.
        2 Goteará como la lluvia mi enseñanza;
        Destilará como el rocío mi razonamiento;
        Como la llovizna sobre la grama,
        Y como las gotas sobre la hierba;
        3 Porque el nombre de Jehová proclamaré.
        Engrandeced a nuestro Dios.
        4 Él es la Roca, cuya obra es perfecta,
        Porque todos sus caminos son rectitud;
        Dios de verdad, y sin ninguna iniquidad en él;
        Es justo y recto.
        5 La corrupción no es suya; de sus hijos es la mancha,
        Generación torcida y perversa.
        6 ¿Así pagáis a Jehová,
        Pueblo loco e ignorante?
        ¿No es él tu padre que te creó?
        Él te hizo y te estableció.
        7 Acuérdate de los tiempos antiguos,
        Considera los años de muchas generaciones;
        Pregunta a tu padre, y él te declarará;
        A tus ancianos, y ellos te dirán.
        8 Cuando el Altísimo hizo heredar a las naciones,
        Cuando hizo dividir a los hijos de los hombres,
        Estableció los límites de los pueblos
        Según el número de los hijos de Israel.
        9 Porque la porción de Jehová es su pueblo;
        Jacob la heredad que le tocó.
        10 Le halló en tierra de desierto,
        Y en yermo de horrible soledad;
        Lo trajo alrededor, lo instruyó,
        Lo guardó como a la niña de su ojo.
        11 Como el águila que excita su nidada,
        Revolotea sobre sus pollos,
        Extiende sus alas, los toma,
        Los lleva sobre sus plumas,
        12 Jehová solo le guio,
        Y con él no hubo dios extraño.
        13 Lo hizo subir sobre las alturas de la tierra,
        Y comió los frutos del campo,
        E hizo que chupase miel de la peña,
        Y aceite del duro pedernal;
        14 Mantequilla de vacas y leche de ovejas,
        Con grosura de corderos,
        Y carneros de Basán; también machos cabríos,
        Con lo mejor del trigo;
        Y de la sangre de la uva bebiste vino.
        15 Pero engordó Jesurún, y tiró coces
        (Engordaste, te cubriste de grasa);
        Entonces abandonó al Dios que lo hizo,
        Y menospreció la Roca de su salvación.
        16 Le despertaron a celos con los dioses ajenos;
        Lo provocaron a ira con abominaciones.
        17 Sacrificaron a los demonios, y no a Dios;
        A dioses que no habían conocido,
        A nuevos dioses venidos de cerca,
        Que no habían temido vuestros padres.
        18 De la Roca que te creó te olvidaste;
        Te has olvidado de Dios tu creador.
        19 Y lo vio Jehová, y se encendió en ira
        Por el menosprecio de sus hijos y de sus hijas.
        20 Y dijo: Esconderé de ellos mi rostro,
        Veré cuál será su fin;
        Porque son una generación perversa,
        Hijos infieles.
        21 Ellos me movieron a celos con lo que no es Dios;
        Me provocaron a ira con sus ídolos;
        Yo también los moveré a celos con un pueblo que no es pueblo,
        Los provocaré a ira con una nación insensata.
        22 Porque fuego se ha encendido en mi ira,
        Y arderá hasta las profundidades del Seol;
        Devorará la tierra y sus frutos,
        Y abrasará los fundamentos de los montes.
        23 Yo amontonaré males sobre ellos;
        Emplearé en ellos mis saetas.
        24 Consumidos serán de hambre, y devorados de fiebre ardiente
        Y de peste amarga;
        Diente de fieras enviaré también sobre ellos,
        Con veneno de serpientes de la tierra.
        25 Por fuera desolará la espada,
        Y dentro de las cámaras el espanto;
        Así al joven como a la doncella,
        Al niño de pecho como al hombre cano.
        26 Yo había dicho que los esparciría lejos,
        Que haría cesar de entre los hombres la memoria de ellos,
        27 De no haber temido la provocación del enemigo,
        No sea que se envanezcan sus adversarios,
        No sea que digan: Nuestra mano poderosa
        Ha hecho todo esto, y no Jehová.
        28 Porque son nación privada de consejos,
        Y no hay en ellos entendimiento.
        29 ¡Ojalá fueran sabios, que comprendieran esto,
        Y se dieran cuenta del fin que les espera!
        30 ¿Cómo podría perseguir uno a mil,
        Y dos hacer huir a diez mil,
        Si su Roca no los hubiese vendido,
        Y Jehová no los hubiera entregado?
        31 Porque la roca de ellos no es como nuestra Roca,
        Y aun nuestros enemigos son de ello jueces.
        32 Porque de la vid de Sodoma es la vid de ellos,
        Y de los campos de Gomorra;
        Las uvas de ellos son uvas ponzoñosas,
        Racimos muy amargos tienen.
        33 Veneno de serpientes es su vino,
        Y ponzoña cruel de áspides.
        34 ¿No tengo yo esto guardado conmigo,
        Sellado en mis tesoros?
        35 Mía es la venganza y la retribución;
        A su tiempo su pie resbalará,
        Porque el día de su aflicción está cercano,
        Y lo que les está preparado se apresura.
        36 Porque Jehová juzgará a su pueblo,
        Y por amor de sus siervos se arrepentirá,
        Cuando viere que la fuerza pereció,
        Y que no queda ni siervo ni libre.
        37 Y dirá: ¿Dónde están sus dioses,
        La roca en que se refugiaban;
        38 Que comían la grosura de sus sacrificios,
        Y bebían el vino de sus libaciones?
        Levántense, que os ayuden
        Y os defiendan.
        39 Ved ahora que yo, yo soy,
        Y no hay dioses conmigo;
        Yo hago morir, y yo hago vivir;
        Yo hiero, y yo sano;
        Y no hay quien pueda librar de mi mano.
        40 Porque yo alzaré a los cielos mi mano,
        Y diré: Vivo yo para siempre,
        41 Si afilare mi reluciente espada,
        Y echare mano del juicio,
        Yo tomaré venganza de mis enemigos,
        Y daré la retribución a los que me aborrecen.
        42 Embriagaré de sangre mis saetas,
        Y mi espada devorará carne;
        En la sangre de los muertos y de los cautivos,
        En las cabezas de larga cabellera del enemigo.
        43 Alabad, naciones, a su pueblo,
        Porque él vengará la sangre de sus siervos,
        Y tomará venganza de sus enemigos,
        Y hará expiación por la tierra de su pueblo.
        44 Vino Moisés y recitó todas las palabras de este cántico a oídos del pueblo, él y Josué hijo de Nun. 
        45 Y acabó Moisés de recitar todas estas palabras a todo Israel; 
        46 y les dijo: Aplicad vuestro corazón a todas las palabras que yo os testifico hoy, para que las mandéis a vuestros hijos, a fin de que cuiden de cumplir todas las palabras de esta ley. 
        47 Porque no os es cosa vana; es vuestra vida, y por medio de esta ley haréis prolongar vuestros días sobre la tierra adonde vais, pasando el Jordán, para tomar posesión de ella.
        Se le permite a Moisés contemplar la tierra de Canaán
        48 Y habló Jehová a Moisés aquel mismo día, diciendo: 
        49 Sube a este monte de Abarim, al monte Nebo, situado en la tierra de Moab que está frente a Jericó, y mira la tierra de Canaán, que yo doy por heredad a los hijos de Israel; 
        50 y muere en el monte al cual subes, y sé unido a tu pueblo, así como murió Aarón tu hermano en el monte Hor, y fue unido a su pueblo; 
        51 por cuanto pecasteis contra mí en medio de los hijos de Israel en las aguas de Meriba de Cades, en el desierto de Zin; porque no me santificasteis en medio de los hijos de Israel. 
        52 Verás, por tanto, delante de ti la tierra; mas no entrarás allá, a la tierra que doy a los hijos de Israel.
""".trimIndent(),
        "Job 19" to """
        Job confía en que Dios lo justificará
        1 Respondió entonces Job, y dijo:
        2 ¿Hasta cuándo angustiaréis mi alma,
        Y me moleréis con palabras?
        3 Ya me habéis vituperado diez veces;
        ¿No os avergonzáis de injuriarme?
        4 Aun siendo verdad que yo haya errado,
        Sobre mí recaería mi error.
        5 Pero si vosotros os engrandecéis contra mí,
        Y contra mí alegáis mi oprobio,
        6 Sabed ahora que Dios me ha derribado,
        Y me ha envuelto en su red.
        7 He aquí, yo clamaré agravio, y no seré oído;
        Daré voces, y no habrá juicio.
        8 Cercó de vallado mi camino, y no pasaré;
        Y sobre mis veredas puso tinieblas.
        9 Me ha despojado de mi gloria,
        Y quitado la corona de mi cabeza.
        10 Me arruinó por todos lados, y perezco;
        Y ha hecho pasar mi esperanza como árbol arrancado.
        11 Hizo arder contra mí su furor,
        Y me contó para sí entre sus enemigos.
        12 Vinieron sus ejércitos a una, y se atrincheraron en mí,
        Y acamparon en derredor de mi tienda.
        13 Hizo alejar de mí a mis hermanos,
        Y mis conocidos como extraños se apartaron de mí.
        14 Mis parientes se detuvieron,
        Y mis conocidos se olvidaron de mí.
        15 Los moradores de mi casa y mis criadas me tuvieron por extraño;
        Forastero fui yo a sus ojos.
        16 Llamé a mi siervo, y no respondió;
        De mi propia boca le suplicaba.
        17 Mi aliento vino a ser extraño a mi mujer,
        Aunque por los hijos de mis entrañas le rogaba.
        18 Aun los muchachos me menospreciaron;
        Al levantarme, hablaban contra mí.
        19 Todos mis íntimos amigos me aborrecieron,
        Y los que yo amaba se volvieron contra mí.
        20 Mi piel y mi carne se pegaron a mis huesos,
        Y he escapado con solo la piel de mis dientes.
        21 ¡Oh, vosotros mis amigos, tened compasión de mí, tened compasión de mí!
        Porque la mano de Dios me ha tocado.
        22 ¿Por qué me perseguís como Dios,
        Y ni aun de mi carne os saciáis?
        23 ¡Quién diese ahora que mis palabras fuesen escritas!
        ¡Quién diese que se escribiesen en un libro;
        24 Que con cincel de hierro y con plomo
        Fuesen esculpidas en piedra para siempre!
        25 Yo sé que mi Redentor vive,
        Y al fin se levantará sobre el polvo;
        26 Y después de deshecha esta mi piel,
        En mi carne he de ver a Dios;
        27 Al cual veré por mí mismo,
        Y mis ojos lo verán, y no otro,
        Aunque mi corazón desfallece dentro de mí.
        28 Mas debierais decir: ¿Por qué le perseguimos?
        Ya que la raíz del asunto se halla en mí.
        29 Temed vosotros delante de la espada;
        Porque sobreviene el furor de la espada a causa de las injusticias,
        Para que sepáis que hay un juicio.
""".trimIndent(),
        "Hechos 8:1-25" to """
        Saulo persigue a la iglesia
        1 Y Saulo consentía en su muerte. En aquel día hubo una gran persecución contra la iglesia que estaba en Jerusalén; y todos fueron esparcidos por las tierras de Judea y de Samaria, salvo los apóstoles. 
        2 Y hombres piadosos llevaron a enterrar a Esteban, e hicieron gran llanto sobre él. 
        3 Y Saulo asolaba la iglesia, y entrando casa por casa, arrastraba a hombres y a mujeres, y los entregaba en la cárcel.
        Predicación del evangelio en Samaria
        4 Pero los que fueron esparcidos iban por todas partes anunciando el evangelio. 
        5 Entonces Felipe, descendiendo a la ciudad de Samaria, les predicaba a Cristo. 
        6 Y la gente, unánime, escuchaba atentamente las cosas que decía Felipe, oyendo y viendo las señales que hacía. 
        7 Porque de muchos que tenían espíritus inmundos, salían estos dando grandes voces; y muchos paralíticos y cojos eran sanados; 
        8 así que había gran gozo en aquella ciudad.
        9 Pero había un hombre llamado Simón, que antes ejercía la magia en aquella ciudad, y había engañado a la gente de Samaria, haciéndose pasar por algún grande. 
        10 A este oían atentamente todos, desde el más pequeño hasta el más grande, diciendo: Este es el gran poder de Dios. 
        11 Y le estaban atentos, porque con sus artes mágicas les había engañado mucho tiempo. 
        12 Pero cuando creyeron a Felipe, que anunciaba el evangelio del reino de Dios y el nombre de Jesucristo, se bautizaban hombres y mujeres. 
        13 También creyó Simón mismo, y habiéndose bautizado, estaba siempre con Felipe; y viendo las señales y grandes milagros que se hacían, estaba atónito.
        14 Cuando los apóstoles que estaban en Jerusalén oyeron que Samaria había recibido la palabra de Dios, enviaron allá a Pedro y a Juan; 
        15 los cuales, habiendo venido, oraron por ellos para que recibiesen el Espíritu Santo; 
        16 porque aún no había descendido sobre ninguno de ellos, sino que solamente habían sido bautizados en el nombre de Jesús. 
        17 Entonces les imponían las manos, y recibían el Espíritu Santo. 
        18 Cuando vio Simón que por la imposición de las manos de los apóstoles se daba el Espíritu Santo, les ofreció dinero, 
        19 diciendo: Dadme también a mí este poder, para que cualquiera a quien yo impusiere las manos reciba el Espíritu Santo. 
        20 Entonces Pedro le dijo: Tu dinero perezca contigo, porque has pensado que el don de Dios se obtiene con dinero. 
        21 No tienes tú parte ni suerte en este asunto, porque tu corazón no es recto delante de Dios. 
        22 Arrepiéntete, pues, de esta tu maldad, y ruega a Dios, si quizá te sea perdonado el pensamiento de tu corazón; 
        23 porque en hiel de amargura y en prisión de maldad veo que estás. 
        24 Respondiendo entonces Simón, dijo: Rogad vosotros por mí al Señor, para que nada de esto que habéis dicho venga sobre mí.
        25 Y ellos, habiendo testificado y hablado la palabra de Dios, se volvieron a Jerusalén, y en muchas poblaciones de los samaritanos anunciaron el evangelio.
""".trimIndent(),
        "Deuteronomio 33-34" to """
        Deuteronomio 33
        Moisés bendice a las doce tribus de Israel
        1 Esta es la bendición con la cual bendijo Moisés varón de Dios a los hijos de Israel, antes que muriese. 2 Dijo:
        Jehová vino de Sinaí,
        Y de Seir les esclareció;
        Resplandeció desde el monte de Parán,
        Y vino de entre diez millares de santos,
        Con la ley de fuego a su mano derecha.
        3 Aun amó a su pueblo;
        Todos los consagrados a él estaban en su mano;
        Por tanto, ellos siguieron en tus pasos,
        Recibiendo dirección de ti,
        4 Cuando Moisés nos ordenó una ley,
        Como heredad a la congregación de Jacob.
        5 Y fue rey en Jesurún,
        Cuando se congregaron los jefes del pueblo
        Con las tribus de Israel.
        6 Viva Rubén, y no muera;
        Y no sean pocos sus varones.
        7 Y esta bendición profirió para Judá. Dijo así:
        Oye, oh Jehová, la voz de Judá,
        Y llévalo a su pueblo;
        Sus manos le basten,
        Y tú seas su ayuda contra sus enemigos.
        8 A Leví dijo:
        Tu Tumim y tu Urim sean para tu varón piadoso,
        A quien probaste en Masah,
        Con quien contendiste en las aguas de Meriba,
        9 Quien dijo de su padre y de su madre: Nunca los he visto;
        Y no reconoció a sus hermanos,
        Ni a sus hijos conoció;
        Pues ellos guardaron tus palabras,
        Y cumplieron tu pacto.
        10 Ellos enseñarán tus juicios a Jacob,
        Y tu ley a Israel;
        Pondrán el incienso delante de ti,
        Y el holocausto sobre tu altar.
        11 Bendice, oh Jehová, lo que hicieren,
        Y recibe con agrado la obra de sus manos;
        Hiere los lomos de sus enemigos,
        Y de los que lo aborrecieren, para que nunca se levanten.
        12 A Benjamín dijo:
        El amado de Jehová habitará confiado cerca de él;
        Lo cubrirá siempre,
        Y entre sus hombros morará.
        13 A José dijo:
        Bendita de Jehová sea tu tierra,
        Con lo mejor de los cielos, con el rocío,
        Y con el abismo que está abajo.
        14 Con los más escogidos frutos del sol,
        Con el rico producto de la luna,
        15 Con el fruto más fino de los montes antiguos,
        Con la abundancia de los collados eternos,
        16 Y con las mejores dádivas de la tierra y su plenitud;
        Y la gracia del que habitó en la zarza
        Venga sobre la cabeza de José,
        Y sobre la frente de aquel que es príncipe entre sus hermanos.
        17 Como el primogénito de su toro es su gloria,
        Y sus astas como astas de búfalo;
        Con ellas acorneará a los pueblos juntos hasta los fines de la tierra;
        Ellos son los diez millares de Efraín,
        Y ellos son los millares de Manasés.
        18 A Zabulón dijo:
        Alégrate, Zabulón, cuando salieres;
        Y tú, Isacar, en tus tiendas.
        19 Llamarán a los pueblos a su monte;
        Allí sacrificarán sacrificios de justicia,
        Por lo cual chuparán la abundancia de los mares,
        Y los tesoros escondidos de la arena.
        20 A Gad dijo:
        Bendito el que hizo ensanchar a Gad;
        Como león reposa,
        Y arrebata brazo y testa.
        21 Escoge lo mejor de la tierra para sí,
        Porque allí le fue reservada la porción del legislador.
        Y vino en la delantera del pueblo;
        Con Israel ejecutó los mandatos y los justos decretos de Jehová.
        22 A Dan dijo:
        Dan es cachorro de león
        Que salta desde Basán.
        23 A Neftalí dijo:
        Neftalí, saciado de favores,
        Y lleno de la bendición de Jehová,
        Posee el occidente y el sur.
        24 A Aser dijo:
        Bendito sobre los hijos sea Aser;
        Sea el amado de sus hermanos,
        Y moje en aceite su pie.
        25 Hierro y bronce serán tus cerrojos,
        Y como tus días serán tus fuerzas.
        26 No hay como el Dios de Jesurún,
        Quien cabalga sobre los cielos para tu ayuda,
        Y sobre las nubes con su grandeza.
        27 El eterno Dios es tu refugio,
        Y acá abajo los brazos eternos;
        Él echó de delante de ti al enemigo,
        Y dijo: Destruye.
        28 E Israel habitará confiado, la fuente de Jacob habitará sola
        En tierra de grano y de vino;
        También sus cielos destilarán rocío.
        29 Bienaventurado tú, oh Israel.
        ¿Quién como tú,
        Pueblo salvo por Jehová,
        Escudo de tu socorro,
        Y espada de tu triunfo?
        Así que tus enemigos serán humillados,
        Y tú hollarás sobre sus alturas.
        Deuteronomio 34
        Muerte y sepultura de Moisés
        1 Subió Moisés de los campos de Moab al monte Nebo, a la cumbre del Pisga, que está enfrente de Jericó; y le mostró Jehová toda la tierra de Galaad hasta Dan, 
        2 todo Neftalí, y la tierra de Efraín y de Manasés, toda la tierra de Judá hasta el mar occidental; 
        3 el Neguev, y la llanura, la vega de Jericó, ciudad de las palmeras, hasta Zoar. 
        4 Y le dijo Jehová: Esta es la tierra de que juré a Abraham, a Isaac y a Jacob, diciendo: A tu descendencia la daré. Te he permitido verla con tus ojos, mas no pasarás allá. 
        5 Y murió allí Moisés siervo de Jehová, en la tierra de Moab, conforme al dicho de Jehová. 
        6 Y lo enterró en el valle, en la tierra de Moab, enfrente de Bet-peor; y ninguno conoce el lugar de su sepultura hasta hoy. 
        7 Era Moisés de edad de ciento veinte años cuando murió; sus ojos nunca se oscurecieron, ni perdió su vigor. 
        8 Y lloraron los hijos de Israel a Moisés en los campos de Moab treinta días; y así se cumplieron los días del lloro y del luto de Moisés.
        9 Y Josué hijo de Nun fue lleno del espíritu de sabiduría, porque Moisés había puesto sus manos sobre él; y los hijos de Israel le obedecieron, e hicieron como Jehová mandó a Moisés. 
        10 Y nunca más se levantó profeta en Israel como Moisés, a quien haya conocido Jehová cara a cara; 
        11 nadie como él en todas las señales y prodigios que Jehová le envió a hacer en tierra de Egipto, a Faraón y a todos sus siervos y a toda su tierra, 
        12 y en el gran poder y en los hechos grandiosos y terribles que Moisés hizo a la vista de todo Israel.
""".trimIndent(),
        "Job 20" to """
        Zofar describe las calamidades de los malos
        1 Respondió Zofar naamatita, y dijo:
        2 Por cierto mis pensamientos me hacen responder,
        Y por tanto me apresuro.
        3 La reprensión de mi censura he oído,
        Y me hace responder el espíritu de mi inteligencia.
        4 ¿No sabes esto, que así fue siempre,
        Desde el tiempo que fue puesto el hombre sobre la tierra,
        5 Que la alegría de los malos es breve,
        Y el gozo del impío por un momento?
        6 Aunque subiere su altivez hasta el cielo,
        Y su cabeza tocare en las nubes,
        7 Como su estiércol, perecerá para siempre;
        Los que le hubieren visto dirán: ¿Qué hay de él?
        8 Como sueño volará, y no será hallado,
        Y se disipará como visión nocturna.
        9 El ojo que le veía, nunca más le verá,
        Ni su lugar le conocerá más.
        10 Sus hijos solicitarán el favor de los pobres,
        Y sus manos devolverán lo que él robó.
        11 Sus huesos están llenos de su juventud,
        Mas con él en el polvo yacerán.
        12 Si el mal se endulzó en su boca,
        Si lo ocultaba debajo de su lengua,
        13 Si le parecía bien, y no lo dejaba,
        Sino que lo detenía en su paladar;
        14 Su comida se mudará en sus entrañas;
        Hiel de áspides será dentro de él.
        15 Devoró riquezas, pero las vomitará;
        De su vientre las sacará Dios.
        16 Veneno de áspides chupará;
        Lo matará lengua de víbora.
        17 No verá los arroyos, los ríos,
        Los torrentes de miel y de leche.
        18 Restituirá el trabajo conforme a los bienes que tomó,
        Y no los tragará ni gozará.
        19 Por cuanto quebrantó y desamparó a los pobres,
        Robó casas, y no las edificó;
        20 Por tanto, no tendrá sosiego en su vientre,
        Ni salvará nada de lo que codiciaba.
        21 No quedó nada que no comiese;
        Por tanto, su bienestar no será duradero.
        22 En el colmo de su abundancia padecerá estrechez;
        La mano de todos los malvados vendrá sobre él.
        23 Cuando se pusiere a llenar su vientre,
        Dios enviará sobre él el ardor de su ira,
        Y la hará llover sobre él y sobre su comida.
        24 Huirá de las armas de hierro,
        Y el arco de bronce le atravesará.
        25 La saeta le traspasará y saldrá de su cuerpo,
        Y la punta relumbrante saldrá por su hiel;
        Sobre él vendrán terrores.
        26 Todas las tinieblas están reservadas para sus tesoros;
        Fuego no atizado los consumirá;
        Devorará lo que quede en su tienda.
        27 Los cielos descubrirán su iniquidad,
        Y la tierra se levantará contra él.
        28 Los renuevos de su casa serán transportados;
        Serán esparcidos en el día de su furor.
        29 Esta es la porción que Dios prepara al hombre impío,
        Y la heredad que Dios le señala por su palabra.
""".trimIndent(),
        "Hechos 8:26-40" to """
        Felipe y el etíope
        26 Un ángel del Señor habló a Felipe, diciendo: Levántate y ve hacia el sur, por el camino que desciende de Jerusalén a Gaza, el cual es desierto. 
        27 Entonces él se levantó y fue. Y sucedió que un etíope, eunuco, funcionario de Candace reina de los etíopes, el cual estaba sobre todos sus tesoros, y había venido a Jerusalén para adorar, 
        28 volvía sentado en su carro, y leyendo al profeta Isaías. 
        29 Y el Espíritu dijo a Felipe: Acércate y júntate a ese carro. 
        30 Acudiendo Felipe, le oyó que leía al profeta Isaías, y dijo: Pero ¿entiendes lo que lees? 
        31 Él dijo: ¿Y cómo podré, si alguno no me enseñare? Y rogó a Felipe que subiese y se sentara con él. 
        32 El pasaje de la Escritura que leía era este:
        Como oveja a la muerte fue llevado;
        Y como cordero mudo delante del que lo trasquila,
        Así no abrió su boca.
        33 En su humillación no se le hizo justicia;
        Mas su generación, ¿quién la contará?
        Porque fue quitada de la tierra su vida.
        34 Respondiendo el eunuco, dijo a Felipe: Te ruego que me digas: ¿de quién dice el profeta esto; de sí mismo, o de algún otro? 
        35 Entonces Felipe, abriendo su boca, y comenzando desde esta escritura, le anunció el evangelio de Jesús. 
        36 Y yendo por el camino, llegaron a cierta agua, y dijo el eunuco: Aquí hay agua; ¿qué impide que yo sea bautizado? 
        37 Felipe dijo: Si crees de todo corazón, bien puedes. Y respondiendo, dijo: Creo que Jesucristo es el Hijo de Dios. 
        38 Y mandó parar el carro; y descendieron ambos al agua, Felipe y el eunuco, y le bautizó. 
        39 Cuando subieron del agua, el Espíritu del Señor arrebató a Felipe; y el eunuco no le vio más, y siguió gozoso su camino. 
        40 Pero Felipe se encontró en Azoto; y pasando, anunciaba el evangelio en todas las ciudades, hasta que llegó a Cesarea.
""".trimIndent(),
        "Josué 1-2" to """
        Josué 1    
        Preparativos para la conquista
        1 Aconteció después de la muerte de Moisés siervo de Jehová, que Jehová habló a Josué hijo de Nun, servidor de Moisés, diciendo: 
        2 Mi siervo Moisés ha muerto; ahora, pues, levántate y pasa este Jordán, tú y todo este pueblo, a la tierra que yo les doy a los hijos de Israel. 
        3 Yo os he entregado, como lo había dicho a Moisés, todo lugar que pisare la planta de vuestro pie. 
        4 Desde el desierto y el Líbano hasta el gran río Éufrates, toda la tierra de los heteos hasta el gran mar donde se pone el sol, será vuestro territorio. 
        5 Nadie te podrá hacer frente en todos los días de tu vida; como estuve con Moisés, estaré contigo; no te dejaré, ni te desampararé. 
        6 Esfuérzate y sé valiente; porque tú repartirás a este pueblo por heredad la tierra de la cual juré a sus padres que la daría a ellos. 
        7 Solamente esfuérzate y sé muy valiente, para cuidar de hacer conforme a toda la ley que mi siervo Moisés te mandó; no te apartes de ella ni a diestra ni a siniestra, para que seas prosperado en todas las cosas que emprendas. 
        8 Nunca se apartará de tu boca este libro de la ley, sino que de día y de noche meditarás en él, para que guardes y hagas conforme a todo lo que en él está escrito; porque entonces harás prosperar tu camino, y todo te saldrá bien. 
        9 Mira que te mando que te esfuerces y seas valiente; no temas ni desmayes, porque Jehová tu Dios estará contigo en dondequiera que vayas.
        10 Y Josué mandó a los oficiales del pueblo, diciendo: 
        11 Pasad por en medio del campamento y mandad al pueblo, diciendo: Preparaos comida, porque dentro de tres días pasaréis el Jordán para entrar a poseer la tierra que Jehová vuestro Dios os da en posesión.
        12 También habló Josué a los rubenitas y gaditas y a la media tribu de Manasés, diciendo: 
        13 Acordaos de la palabra que Moisés, siervo de Jehová, os mandó diciendo: Jehová vuestro Dios os ha dado reposo, y os ha dado esta tierra. 
        14 Vuestras mujeres, vuestros niños y vuestros ganados quedarán en la tierra que Moisés os ha dado a este lado del Jordán; mas vosotros, todos los valientes y fuertes, pasaréis armados delante de vuestros hermanos, y les ayudaréis, 
        15 hasta tanto que Jehová haya dado reposo a vuestros hermanos como a vosotros, y que ellos también posean la tierra que Jehová vuestro Dios les da; y después volveréis vosotros a la tierra de vuestra herencia, la cual Moisés siervo de Jehová os ha dado, a este lado del Jordán hacia donde nace el sol; y entraréis en posesión de ella. 
        16 Entonces respondieron a Josué, diciendo: Nosotros haremos todas las cosas que nos has mandado, e iremos adondequiera que nos mandes. 
        17 De la manera que obedecimos a Moisés en todas las cosas, así te obedeceremos a ti; solamente que Jehová tu Dios esté contigo, como estuvo con Moisés. 
        18 Cualquiera que fuere rebelde a tu mandamiento, y no obedeciere a tus palabras en todas las cosas que le mandes, que muera; solamente que te esfuerces y seas valiente.
        Josué 2
        Josué envía espías a Jericó
        1 Josué hijo de Nun envió desde Sitim dos espías secretamente, diciéndoles: Andad, reconoced la tierra, y a Jericó. Y ellos fueron, y entraron en casa de una ramera que se llamaba Rahab, y posaron allí. 
        2 Y fue dado aviso al rey de Jericó, diciendo: He aquí que hombres de los hijos de Israel han venido aquí esta noche para espiar la tierra. 
        3 Entonces el rey de Jericó envió a decir a Rahab: Saca a los hombres que han venido a ti, y han entrado a tu casa; porque han venido para espiar toda la tierra. 
        4 Pero la mujer había tomado a los dos hombres y los había escondido; y dijo: Es verdad que unos hombres vinieron a mí, pero no supe de dónde eran. 
        5 Y cuando se iba a cerrar la puerta, siendo ya oscuro, esos hombres se salieron, y no sé a dónde han ido; seguidlos aprisa, y los alcanzaréis. 
        6 Mas ella los había hecho subir al terrado, y los había escondido entre los manojos de lino que tenía puestos en el terrado. 
        7 Y los hombres fueron tras ellos por el camino del Jordán, hasta los vados; y la puerta fue cerrada después que salieron los perseguidores.
        8 Antes que ellos se durmiesen, ella subió al terrado, y les dijo: 
        9 Sé que Jehová os ha dado esta tierra; porque el temor de vosotros ha caído sobre nosotros, y todos los moradores del país ya han desmayado por causa de vosotros. 
        10 Porque hemos oído que Jehová hizo secar las aguas del Mar Rojo delante de vosotros cuando salisteis de Egipto, y lo que habéis hecho a los dos reyes de los amorreos que estaban al otro lado del Jordán, a Sehón y a Og, a los cuales habéis destruido. 
        11 Oyendo esto, ha desmayado nuestro corazón; ni ha quedado más aliento en hombre alguno por causa de vosotros, porque Jehová vuestro Dios es Dios arriba en los cielos y abajo en la tierra. 
        12 Os ruego pues, ahora, que me juréis por Jehová, que como he hecho misericordia con vosotros, así la haréis vosotros con la casa de mi padre, de lo cual me daréis una señal segura; 
        13 y que salvaréis la vida a mi padre y a mi madre, a mis hermanos y hermanas, y a todo lo que es suyo; y que libraréis nuestras vidas de la muerte. 
        14 Ellos le respondieron: Nuestra vida responderá por la vuestra, si no denunciareis este asunto nuestro; y cuando Jehová nos haya dado la tierra, nosotros haremos contigo misericordia y verdad.
        15 Entonces ella los hizo descender con una cuerda por la ventana; porque su casa estaba en el muro de la ciudad, y ella vivía en el muro. 
        16 Y les dijo: Marchaos al monte, para que los que fueron tras vosotros no os encuentren; y estad escondidos allí tres días, hasta que los que os siguen hayan vuelto; y después os iréis por vuestro camino. 
        17 Y ellos le dijeron: Nosotros quedaremos libres de este juramento con que nos has juramentado. 
        18 He aquí, cuando nosotros entremos en la tierra, tú atarás este cordón de grana a la ventana por la cual nos descolgaste; y reunirás en tu casa a tu padre y a tu madre, a tus hermanos y a toda la familia de tu padre. 
        19 Cualquiera que saliere fuera de las puertas de tu casa, su sangre será sobre su cabeza, y nosotros sin culpa. Mas cualquiera que se estuviere en casa contigo, su sangre será sobre nuestra cabeza, si mano le tocare. 
        20 Y si tú denunciares este nuestro asunto, nosotros quedaremos libres de este tu juramento con que nos has juramentado. 
        21 Ella respondió: Sea así como habéis dicho. Luego los despidió, y se fueron; y ella ató el cordón de grana a la ventana.
        22 Y caminando ellos, llegaron al monte y estuvieron allí tres días, hasta que volvieron los que los perseguían; y los que los persiguieron buscaron por todo el camino, pero no los hallaron. 
        23 Entonces volvieron los dos hombres; descendieron del monte, y pasaron, y vinieron a Josué hijo de Nun, y le contaron todas las cosas que les habían acontecido. 
        24 Y dijeron a Josué: Jehová ha entregado toda la tierra en nuestras manos; y también todos los moradores del país desmayan delante de nosotros.
""".trimIndent(),
        "Job 21" to """
        Job afirma que los malos prosperan
        1 Entonces respondió Job, y dijo:
        2 Oíd atentamente mi palabra,
        Y sea esto el consuelo que me deis.
        3 Toleradme, y yo hablaré;
        Y después que haya hablado, escarneced.
        4 ¿Acaso me quejo yo de algún hombre?
        ¿Y por qué no se ha de angustiar mi espíritu?
        5 Miradme, y espantaos,
        Y poned la mano sobre la boca.
        6 Aun yo mismo, cuando me acuerdo, me asombro,
        Y el temblor estremece mi carne.
        7 ¿Por qué viven los impíos,
        Y se envejecen, y aun crecen en riquezas?
        8 Su descendencia se robustece a su vista,
        Y sus renuevos están delante de sus ojos.
        9 Sus casas están a salvo de temor,
        Ni viene azote de Dios sobre ellos.
        10 Sus toros engendran, y no fallan;
        Paren sus vacas, y no malogran su cría.
        11 Salen sus pequeñuelos como manada,
        Y sus hijos andan saltando.
        12 Al son de tamboril y de cítara saltan,
        Y se regocijan al son de la flauta.
        13 Pasan sus días en prosperidad,
        Y en paz descienden al Seol.
        14 Dicen, pues, a Dios: Apártate de nosotros,
        Porque no queremos el conocimiento de tus caminos.
        15 ¿Quién es el Todopoderoso, para que le sirvamos?
        ¿Y de qué nos aprovechará que oremos a él?
        16 He aquí que su bien no está en mano de ellos;
        El consejo de los impíos lejos esté de mí.
        17 ¡Oh, cuántas veces la lámpara de los impíos es apagada,
        Y viene sobre ellos su quebranto,
        Y Dios en su ira les reparte dolores!
        18 Serán como la paja delante del viento,
        Y como el tamo que arrebata el torbellino.
        19 Dios guardará para los hijos de ellos su violencia;
        Le dará su pago, para que conozca.
        20 Verán sus ojos su quebranto,
        Y beberá de la ira del Todopoderoso.
        21 Porque ¿qué deleite tendrá él de su casa después de sí,
        Siendo cortado el número de sus meses?
        22 ¿Enseñará alguien a Dios sabiduría,
        Juzgando él a los que están elevados?
        23 Este morirá en el vigor de su hermosura, todo quieto y pacífico;
        24 Sus vasijas estarán llenas de leche,
        Y sus huesos serán regados de tuétano.
        25 Y este otro morirá en amargura de ánimo,
        Y sin haber comido jamás con gusto.
        26 Igualmente yacerán ellos en el polvo,
        Y gusanos los cubrirán.
        27 He aquí, yo conozco vuestros pensamientos,
        Y las imaginaciones que contra mí forjáis.
        28 Porque decís: ¿Qué hay de la casa del príncipe,
        Y qué de la tienda de las moradas de los impíos?
        29 ¿No habéis preguntado a los que pasan por los caminos,
        Y no habéis conocido su respuesta,
        30 Que el malo es preservado en el día de la destrucción?
        Guardado será en el día de la ira.
        31 ¿Quién le denunciará en su cara su camino?
        Y de lo que él hizo, ¿quién le dará el pago?
        32 Porque llevado será a los sepulcros,
        Y sobre su túmulo estarán velando.
        33 Los terrones del valle le serán dulces;
        Tras de él será llevado todo hombre,
        Y antes de él han ido innumerables.
        34 ¿Cómo, pues, me consoláis en vano,
        Viniendo a parar vuestras respuestas en falacia?
""".trimIndent(),
        "Hechos 9:1-25" to """
        Conversión de Saulo
        1 Saulo, respirando aún amenazas y muerte contra los discípulos del Señor, vino al sumo sacerdote, 
        2 y le pidió cartas para las sinagogas de Damasco, a fin de que si hallase algunos hombres o mujeres de este Camino, los trajese presos a Jerusalén. 
        3 Mas yendo por el camino, aconteció que al llegar cerca de Damasco, repentinamente le rodeó un resplandor de luz del cielo; 
        4 y cayendo en tierra, oyó una voz que le decía: Saulo, Saulo, ¿por qué me persigues? 
        5 Él dijo: ¿Quién eres, Señor? Y le dijo: Yo soy Jesús, a quien tú persigues; dura cosa te es dar coces contra el aguijón. 
        6 Él, temblando y temeroso, dijo: Señor, ¿qué quieres que yo haga? Y el Señor le dijo: Levántate y entra en la ciudad, y se te dirá lo que debes hacer. 
        7 Y los hombres que iban con Saulo se pararon atónitos, oyendo a la verdad la voz, mas sin ver a nadie. 
        8 Entonces Saulo se levantó de tierra, y abriendo los ojos, no veía a nadie; así que, llevándole por la mano, le metieron en Damasco, 
        9 donde estuvo tres días sin ver, y no comió ni bebió.
        10 Había entonces en Damasco un discípulo llamado Ananías, a quien el Señor dijo en visión: Ananías. Y él respondió: Heme aquí, Señor. 
        11 Y el Señor le dijo: Levántate, y ve a la calle que se llama Derecha, y busca en casa de Judas a uno llamado Saulo, de Tarso; porque he aquí, él ora, 
        12 y ha visto en visión a un varón llamado Ananías, que entra y le pone las manos encima para que recobre la vista. 
        13 Entonces Ananías respondió: Señor, he oído de muchos acerca de este hombre, cuántos males ha hecho a tus santos en Jerusalén; 
        14 y aun aquí tiene autoridad de los principales sacerdotes para prender a todos los que invocan tu nombre. 
        15 El Señor le dijo: Ve, porque instrumento escogido me es este, para llevar mi nombre en presencia de los gentiles, y de reyes, y de los hijos de Israel; 
        16 porque yo le mostraré cuánto le es necesario padecer por mi nombre. 
        17 Fue entonces Ananías y entró en la casa, y poniendo sobre él las manos, dijo: Hermano Saulo, el Señor Jesús, que se te apareció en el camino por donde venías, me ha enviado para que recibas la vista y seas lleno del Espíritu Santo. 
        18 Y al momento le cayeron de los ojos como escamas, y recibió al instante la vista; y levantándose, fue bautizado. 
        19 Y habiendo tomado alimento, recobró fuerzas. Y estuvo Saulo por algunos días con los discípulos que estaban en Damasco.
        Saulo predica en Damasco
        20 En seguida predicaba a Cristo en las sinagogas, diciendo que este era el Hijo de Dios. 
        21 Y todos los que le oían estaban atónitos, y decían: ¿No es este el que asolaba en Jerusalén a los que invocaban este nombre, y a eso vino acá, para llevarlos presos ante los principales sacerdotes? 
        22 Pero Saulo mucho más se esforzaba, y confundía a los judíos que moraban en Damasco, demostrando que Jesús era el Cristo.
        Saulo escapa de los judíos
        23 Pasados muchos días, los judíos resolvieron en consejo matarle; 
        24 pero sus asechanzas llegaron a conocimiento de Saulo. Y ellos guardaban las puertas de día y de noche para matarle. 
        25 Entonces los discípulos, tomándole de noche, le bajaron por el muro, descolgándole en una canasta.
""".trimIndent(),
        "Josué 3:1-5:1" to """
        El paso del Jordán
        1 Josué se levantó de mañana, y él y todos los hijos de Israel partieron de Sitim y vinieron hasta el Jordán, y reposaron allí antes de pasarlo. 
        2 Y después de tres días, los oficiales recorrieron el campamento, 
        3 y mandaron al pueblo, diciendo: Cuando veáis el arca del pacto de Jehová vuestro Dios, y los levitas sacerdotes que la llevan, vosotros saldréis de vuestro lugar y marcharéis en pos de ella, 
        4 a fin de que sepáis el camino por donde habéis de ir; por cuanto vosotros no habéis pasado antes de ahora por este camino. Pero entre vosotros y ella haya distancia como de dos mil codos; no os acercaréis a ella. 
        5 Y Josué dijo al pueblo: Santificaos, porque Jehová hará mañana maravillas entre vosotros. 
        6 Y habló Josué a los sacerdotes, diciendo: Tomad el arca del pacto, y pasad delante del pueblo. Y ellos tomaron el arca del pacto y fueron delante del pueblo.
        7 Entonces Jehová dijo a Josué: Desde este día comenzaré a engrandecerte delante de los ojos de todo Israel, para que entiendan que como estuve con Moisés, así estaré contigo. 
        8 Tú, pues, mandarás a los sacerdotes que llevan el arca del pacto, diciendo: Cuando hayáis entrado hasta el borde del agua del Jordán, pararéis en el Jordán. 
        9 Y Josué dijo a los hijos de Israel: Acercaos, y escuchad las palabras de Jehová vuestro Dios. 
        10 Y añadió Josué: En esto conoceréis que el Dios viviente está en medio de vosotros, y que él echará de delante de vosotros al cananeo, al heteo, al heveo, al ferezeo, al gergeseo, al amorreo y al jebuseo. 
        11 He aquí, el arca del pacto del Señor de toda la tierra pasará delante de vosotros en medio del Jordán. 
        12 Tomad, pues, ahora doce hombres de las tribus de Israel, uno de cada tribu. 
        13 Y cuando las plantas de los pies de los sacerdotes que llevan el arca de Jehová, Señor de toda la tierra, se asienten en las aguas del Jordán, las aguas del Jordán se dividirán; porque las aguas que vienen de arriba se detendrán en un montón.
        14 Y aconteció cuando partió el pueblo de sus tiendas para pasar el Jordán, con los sacerdotes delante del pueblo llevando el arca del pacto, 
        15 cuando los que llevaban el arca entraron en el Jordán, y los pies de los sacerdotes que llevaban el arca fueron mojados a la orilla del agua (porque el Jordán suele desbordarse por todas sus orillas todo el tiempo de la siega), 
        16 las aguas que venían de arriba se detuvieron como en un montón bien lejos de la ciudad de Adam, que está al lado de Saretán, y las que descendían al mar del Arabá, al Mar Salado, se acabaron, y fueron divididas; y el pueblo pasó en dirección de Jericó. 
        17 Mas los sacerdotes que llevaban el arca del pacto de Jehová, estuvieron en seco, firmes en medio del Jordán, hasta que todo el pueblo hubo acabado de pasar el Jordán; y todo Israel pasó en seco.
        Josué 4
        Las doce piedras tomadas del Jordán
        1 Cuando toda la gente hubo acabado de pasar el Jordán, Jehová habló a Josué, diciendo: 
        2 Tomad del pueblo doce hombres, uno de cada tribu, 
        3 y mandadles, diciendo: Tomad de aquí de en medio del Jordán, del lugar donde están firmes los pies de los sacerdotes, doce piedras, las cuales pasaréis con vosotros, y levantadlas en el lugar donde habéis de pasar la noche. 
        4 Entonces Josué llamó a los doce hombres a los cuales él había designado de entre los hijos de Israel, uno de cada tribu. 
        5 Y les dijo Josué: Pasad delante del arca de Jehová vuestro Dios a la mitad del Jordán, y cada uno de vosotros tome una piedra sobre su hombro, conforme al número de las tribus de los hijos de Israel, 
        6 para que esto sea señal entre vosotros; y cuando vuestros hijos preguntaren a sus padres mañana, diciendo: ¿Qué significan estas piedras? 
        7 les responderéis: Que las aguas del Jordán fueron divididas delante del arca del pacto de Jehová; cuando ella pasó el Jordán, las aguas del Jordán se dividieron; y estas piedras servirán de monumento conmemorativo a los hijos de Israel para siempre.
        8 Y los hijos de Israel lo hicieron así como Josué les mandó: tomaron doce piedras de en medio del Jordán, como Jehová lo había dicho a Josué, conforme al número de las tribus de los hijos de Israel, y las pasaron al lugar donde acamparon, y las levantaron allí.
        9 Josué también levantó doce piedras en medio del Jordán, en el lugar donde estuvieron los pies de los sacerdotes que llevaban el arca del pacto; y han estado allí hasta hoy. 
        10 Y los sacerdotes que llevaban el arca se pararon en medio del Jordán hasta que se hizo todo lo que Jehová había mandado a Josué que dijese al pueblo, conforme a todas las cosas que Moisés había mandado a Josué; y el pueblo se dio prisa y pasó.
        11 Y cuando todo el pueblo acabó de pasar, también pasó el arca de Jehová, y los sacerdotes, en presencia del pueblo. 
        12 También los hijos de Rubén y los hijos de Gad y la media tribu de Manasés pasaron armados delante de los hijos de Israel, según Moisés les había dicho; 
        13 como cuarenta mil hombres armados, listos para la guerra, pasaron hacia la llanura de Jericó delante de Jehová. 
        14 En aquel día Jehová engrandeció a Josué a los ojos de todo Israel; y le temieron, como habían temido a Moisés, todos los días de su vida.
        15 Luego Jehová habló a Josué, diciendo: 
        16 Manda a los sacerdotes que llevan el arca del testimonio, que suban del Jordán. 
        17 Y Josué mandó a los sacerdotes, diciendo: Subid del Jordán. 
        18 Y aconteció que cuando los sacerdotes que llevaban el arca del pacto de Jehová subieron de en medio del Jordán, y las plantas de los pies de los sacerdotes estuvieron en lugar seco, las aguas del Jordán se volvieron a su lugar, corriendo como antes sobre todos sus bordes.
        19 Y el pueblo subió del Jordán el día diez del mes primero, y acamparon en Gilgal, al lado oriental de Jericó. 
        20 Y Josué erigió en Gilgal las doce piedras que habían traído del Jordán. 
        21 Y habló a los hijos de Israel, diciendo: Cuando mañana preguntaren vuestros hijos a sus padres, y dijeren: ¿Qué significan estas piedras? 
        22 declararéis a vuestros hijos, diciendo: Israel pasó en seco por este Jordán. 
        23 Porque Jehová vuestro Dios secó las aguas del Jordán delante de vosotros, hasta que habíais pasado, a la manera que Jehová vuestro Dios lo había hecho en el Mar Rojo, el cual secó delante de nosotros hasta que pasamos; 
        24 para que todos los pueblos de la tierra conozcan que la mano de Jehová es poderosa; para que temáis a Jehová vuestro Dios todos los días.
        Josué 5
        5 Cuando todos los reyes de los amorreos que estaban al otro lado del Jordán al occidente, y todos los reyes de los cananeos que estaban cerca del mar, oyeron cómo Jehová había secado las aguas del Jordán delante de los hijos de Israel hasta que hubieron pasado, desfalleció su corazón, y no hubo más aliento en ellos delante de los hijos de Israel.
""".trimIndent(),
        "Job 22" to """
        Elifaz acusa a Job de gran maldad
        1 Respondió Elifaz temanita, y dijo:
        2 ¿Traerá el hombre provecho a Dios?
        Al contrario, para sí mismo es provechoso el hombre sabio.
        3 ¿Tiene contentamiento el Omnipotente en que tú seas justificado,
        O provecho de que tú hagas perfectos tus caminos?
        4 ¿Acaso te castiga,
        O viene a juicio contigo, a causa de tu piedad?
        5 Por cierto tu malicia es grande,
        Y tus maldades no tienen fin.
        6 Porque sacaste prenda a tus hermanos sin causa,
        Y despojaste de sus ropas a los desnudos.
        7 No diste de beber agua al cansado,
        Y detuviste el pan al hambriento.
        8 Pero el hombre pudiente tuvo la tierra,
        Y habitó en ella el distinguido.
        9 A las viudas enviaste vacías,
        Y los brazos de los huérfanos fueron quebrados.
        10 Por tanto, hay lazos alrededor de ti,
        Y te turba espanto repentino;
        11 O tinieblas, para que no veas,
        Y abundancia de agua te cubre.
        12 ¿No está Dios en la altura de los cielos?
        Mira lo encumbrado de las estrellas, cuán elevadas están.
        13 ¿Y dirás tú: Qué sabe Dios?
        ¿Cómo juzgará a través de la oscuridad?
        14 Las nubes le rodearon, y no ve;
        Y por el circuito del cielo se pasea.
        15 ¿Quieres tú seguir la senda antigua
        Que pisaron los hombres perversos,
        16 Los cuales fueron cortados antes de tiempo,
        Cuyo fundamento fue como un río derramado?
        17 Decían a Dios: Apártate de nosotros.
        ¿Y qué les había hecho el Omnipotente?
        18 Les había colmado de bienes sus casas.
        Pero sea el consejo de ellos lejos de mí.
        19 Verán los justos y se gozarán;
        Y el inocente los escarnecerá, diciendo:
        20 Fueron destruidos nuestros adversarios,
        Y el fuego consumió lo que de ellos quedó.
        21 Vuelve ahora en amistad con él, y tendrás paz;
        Y por ello te vendrá bien.
        22 Toma ahora la ley de su boca,
        Y pon sus palabras en tu corazón.
        23 Si te volvieres al Omnipotente, serás edificado;
        Alejarás de tu tienda la aflicción;
        24 Tendrás más oro que tierra,
        Y como piedras de arroyos oro de Ofir;
        25 El Todopoderoso será tu defensa,
        Y tendrás plata en abundancia.
        26 Porque entonces te deleitarás en el Omnipotente,
        Y alzarás a Dios tu rostro.
        27 Orarás a él, y él te oirá;
        Y tú pagarás tus votos.
        28 Determinarás asimismo una cosa, y te será firme,
        Y sobre tus caminos resplandecerá luz.
        29 Cuando fueren abatidos, dirás tú: Enaltecimiento habrá;
        Y Dios salvará al humilde de ojos.
        30 Él libertará al inocente,
        Y por la limpieza de tus manos este será librado.
""".trimIndent(),
        "Hechos 9:26-43" to """
        Saulo en Jerusalén
        26 Cuando llegó a Jerusalén, trataba de juntarse con los discípulos; pero todos le tenían miedo, no creyendo que fuese discípulo. 
        27 Entonces Bernabé, tomándole, lo trajo a los apóstoles, y les contó cómo Saulo había visto en el camino al Señor, el cual le había hablado, y cómo en Damasco había hablado valerosamente en el nombre de Jesús. 
        28 Y estaba con ellos en Jerusalén; y entraba y salía, 29 y hablaba denodadamente en el nombre del Señor, y disputaba con los griegos; pero estos procuraban matarle. 
        30 Cuando supieron esto los hermanos, le llevaron hasta Cesarea, y le enviaron a Tarso.
        31 Entonces las iglesias tenían paz por toda Judea, Galilea y Samaria; y eran edificadas, andando en el temor del Señor, y se acrecentaban fortalecidas por el Espíritu Santo.
        Curación de Eneas
        32 Aconteció que Pedro, visitando a todos, vino también a los santos que habitaban en Lida. 
        33 Y halló allí a uno que se llamaba Eneas, que hacía ocho años que estaba en cama, pues era paralítico. 
        34 Y le dijo Pedro: Eneas, Jesucristo te sana; levántate, y haz tu cama. Y en seguida se levantó. 
        35 Y le vieron todos los que habitaban en Lida y en Sarón, los cuales se convirtieron al Señor.
        Dorcas es resucitada
        36 Había entonces en Jope una discípula llamada Tabita, que traducido quiere decir, Dorcas. Esta abundaba en buenas obras y en limosnas que hacía. 
        37 Y aconteció que en aquellos días enfermó y murió. Después de lavada, la pusieron en una sala. 
        38 Y como Lida estaba cerca de Jope, los discípulos, oyendo que Pedro estaba allí, le enviaron dos hombres, a rogarle: No tardes en venir a nosotros. 
        39 Levantándose entonces Pedro, fue con ellos; y cuando llegó, le llevaron a la sala, donde le rodearon todas las viudas, llorando y mostrando las túnicas y los vestidos que Dorcas hacía cuando estaba con ellas. 
        40 Entonces, sacando a todos, Pedro se puso de rodillas y oró; y volviéndose al cuerpo, dijo: Tabita, levántate. Y ella abrió los ojos, y al ver a Pedro, se incorporó. 
        41 Y él, dándole la mano, la levantó; entonces, llamando a los santos y a las viudas, la presentó viva. 
        42 Esto fue notorio en toda Jope, y muchos creyeron en el Señor. 43 Y aconteció que se quedó muchos días en Jope en casa de un cierto Simón, curtidor.
""".trimIndent(),
        "Josué 5:2-6:27" to """
        2 En aquel tiempo Jehová dijo a Josué: Hazte cuchillos afilados, y vuelve a circuncidar la segunda vez a los hijos de Israel. 
        3 Y Josué se hizo cuchillos afilados, y circuncidó a los hijos de Israel en el collado de Aralot.[a] 
        4 Esta es la causa por la cual Josué los circuncidó: Todo el pueblo que había salido de Egipto, los varones, todos los hombres de guerra, habían muerto en el desierto, por el camino, después que salieron de Egipto. 
        5 Pues todos los del pueblo que habían salido, estaban circuncidados; mas todo el pueblo que había nacido en el desierto, por el camino, después que hubieron salido de Egipto, no estaba circuncidado. 
        6 Porque los hijos de Israel anduvieron por el desierto cuarenta años, hasta que todos los hombres de guerra que habían salido de Egipto fueron consumidos, por cuanto no obedecieron a la voz de Jehová; por lo cual Jehová les juró que no les dejaría ver la tierra de la cual Jehová había jurado a sus padres que nos la daría, tierra que fluye leche y miel. 
        7 A los hijos de ellos, que él había hecho suceder en su lugar, Josué los circuncidó; pues eran incircuncisos, porque no habían sido circuncidados por el camino.
        8 Y cuando acabaron de circuncidar a toda la gente, se quedaron en el mismo lugar en el campamento, hasta que sanaron. 
        9 Y Jehová dijo a Josué: Hoy he quitado de vosotros el oprobio de Egipto; por lo cual el nombre de aquel lugar fue llamado Gilgal,[b] hasta hoy.
        10 Y los hijos de Israel acamparon en Gilgal, y celebraron la pascua a los catorce días del mes, por la tarde, en los llanos de Jericó. 
        11 Al otro día de la pascua comieron del fruto de la tierra, los panes sin levadura, y en el mismo día espigas nuevas tostadas. 
        12 Y el maná cesó el día siguiente, desde que comenzaron a comer del fruto de la tierra; y los hijos de Israel nunca más tuvieron maná, sino que comieron de los frutos de la tierra de Canaán aquel año.
        Josué y el varón con la espada desenvainada
        13 Estando Josué cerca de Jericó, alzó sus ojos y vio un varón que estaba delante de él, el cual tenía una espada desenvainada en su mano. Y Josué, yendo hacia él, le dijo: ¿Eres de los nuestros, o de nuestros enemigos? 
        14 Él respondió: No; mas como Príncipe del ejército de Jehová he venido ahora. Entonces Josué, postrándose sobre su rostro en tierra, le adoró; y le dijo: ¿Qué dice mi Señor a su siervo? 
        15 Y el Príncipe del ejército de Jehová respondió a Josué: Quita el calzado de tus pies, porque el lugar donde estás es santo. Y Josué así lo hizo.
        Josué 6
        La toma de Jericó
        1 Ahora, Jericó estaba cerrada, bien cerrada, a causa de los hijos de Israel; nadie entraba ni salía. 
        2 Mas Jehová dijo a Josué: Mira, yo he entregado en tu mano a Jericó y a su rey, con sus varones de guerra. 
        3 Rodearéis, pues, la ciudad todos los hombres de guerra, yendo alrededor de la ciudad una vez; y esto haréis durante seis días. 
        4 Y siete sacerdotes llevarán siete bocinas de cuernos de carnero delante del arca; y al séptimo día daréis siete vueltas a la ciudad, y los sacerdotes tocarán las bocinas. 
        5 Y cuando toquen prolongadamente el cuerno de carnero, así que oigáis el sonido de la bocina, todo el pueblo gritará a gran voz, y el muro de la ciudad caerá; entonces subirá el pueblo, cada uno derecho hacia adelante. 
        6 Llamando, pues, Josué hijo de Nun a los sacerdotes, les dijo: Llevad el arca del pacto, y siete sacerdotes lleven bocinas de cuerno de carnero delante del arca de Jehová. 
        7 Y dijo al pueblo: Pasad, y rodead la ciudad; y los que están armados pasarán delante del arca de Jehová.
        8 Y así que Josué hubo hablado al pueblo, los siete sacerdotes, llevando las siete bocinas de cuerno de carnero, pasaron delante del arca de Jehová, y tocaron las bocinas; y el arca del pacto de Jehová los seguía. 
        9 Y los hombres armados iban delante de los sacerdotes que tocaban las bocinas, y la retaguardia iba tras el arca, mientras las bocinas sonaban continuamente. 
        10 Y Josué mandó al pueblo, diciendo: Vosotros no gritaréis, ni se oirá vuestra voz, ni saldrá palabra de vuestra boca, hasta el día que yo os diga: Gritad; entonces gritaréis. 
        11 Así que él hizo que el arca de Jehová diera una vuelta alrededor de la ciudad, y volvieron luego al campamento, y allí pasaron la noche.
        12 Y Josué se levantó de mañana, y los sacerdotes tomaron el arca de Jehová. 
        13 Y los siete sacerdotes, llevando las siete bocinas de cuerno de carnero, fueron delante del arca de Jehová, andando siempre y tocando las bocinas; y los hombres armados iban delante de ellos, y la retaguardia iba tras el arca de Jehová, mientras las bocinas tocaban continuamente. 
        14 Así dieron otra vuelta a la ciudad el segundo día, y volvieron al campamento; y de esta manera hicieron durante seis días.
        15 Al séptimo día se levantaron al despuntar el alba, y dieron vuelta a la ciudad de la misma manera siete veces; solamente este día dieron vuelta alrededor de ella siete veces. 
        16 Y cuando los sacerdotes tocaron las bocinas la séptima vez, Josué dijo al pueblo: Gritad, porque Jehová os ha entregado la ciudad. 
        17 Y será la ciudad anatema a Jehová, con todas las cosas que están en ella; solamente Rahab la ramera vivirá, con todos los que estén en casa con ella, por cuanto escondió a los mensajeros que enviamos. 
        18 Pero vosotros guardaos del anatema; ni toquéis, ni toméis alguna cosa del anatema, no sea que hagáis anatema el campamento de Israel, y lo turbéis. 
        19 Mas toda la plata y el oro, y los utensilios de bronce y de hierro, sean consagrados a Jehová, y entren en el tesoro de Jehová. 
        20 Entonces el pueblo gritó, y los sacerdotes tocaron las bocinas; y aconteció que cuando el pueblo hubo oído el sonido de la bocina, gritó con gran vocerío, y el muro se derrumbó. El pueblo subió luego a la ciudad, cada uno derecho hacia adelante, y la tomaron. 
        21 Y destruyeron a filo de espada todo lo que en la ciudad había; hombres y mujeres, jóvenes y viejos, hasta los bueyes, las ovejas, y los asnos.
        22 Mas Josué dijo a los dos hombres que habían reconocido la tierra: Entrad en casa de la mujer ramera, y haced salir de allí a la mujer y a todo lo que fuere suyo, como lo jurasteis. 
        23 Y los espías entraron y sacaron a Rahab, a su padre, a su madre, a sus hermanos y todo lo que era suyo; y también sacaron a toda su parentela, y los pusieron fuera del campamento de Israel. 
        24 Y consumieron con fuego la ciudad, y todo lo que en ella había; solamente pusieron en el tesoro de la casa de Jehová la plata y el oro, y los utensilios de bronce y de hierro.
        25 Mas Josué salvó la vida a Rahab la ramera, y a la casa de su padre, y a todo lo que ella tenía; y habitó ella entre los israelitas hasta hoy, por cuanto escondió a los mensajeros que Josué había enviado a reconocer a Jericó.
        26 En aquel tiempo hizo Josué un juramento, diciendo: Maldito delante de Jehová el hombre que se levantare y reedificare esta ciudad de Jericó. Sobre su primogénito eche los cimientos de ella, y sobre su hijo menor asiente sus puertas.
        27 Estaba, pues, Jehová con Josué, y su nombre se divulgó por toda la tierra.
""".trimIndent(),
        "Job 23" to """
        Job desea abogar su causa delante de Dios
        1 Respondió Job, y dijo:
        2 Hoy también hablaré con amargura;
        Porque es más grave mi llaga que mi gemido.
        3 ¡Quién me diera el saber dónde hallar a Dios!
        Yo iría hasta su silla.
        4 Expondría mi causa delante de él,
        Y llenaría mi boca de argumentos.
        5 Yo sabría lo que él me respondiese,
        Y entendería lo que me dijera.
        6 ¿Contendería conmigo con grandeza de fuerza?
        No; antes él me atendería.
        7 Allí el justo razonaría con él;
        Y yo escaparía para siempre de mi juez.
        8 He aquí yo iré al oriente, y no lo hallaré;
        Y al occidente, y no lo percibiré;
        9 Si muestra su poder al norte, yo no lo veré;
        Al sur se esconderá, y no lo veré.
        10 Mas él conoce mi camino;
        Me probará, y saldré como oro.
        11 Mis pies han seguido sus pisadas;
        Guardé su camino, y no me aparté.
        12 Del mandamiento de sus labios nunca me separé;
        Guardé las palabras de su boca más que mi comida.
        13 Pero si él determina una cosa, ¿quién lo hará cambiar?
        Su alma deseó, e hizo.
        14 Él, pues, acabará lo que ha determinado de mí;
        Y muchas cosas como estas hay en él.
        15 Por lo cual yo me espanto en su presencia;
        Cuando lo considero, tiemblo a causa de él.
        16 Dios ha enervado mi corazón,
        Y me ha turbado el Omnipotente.
        17 ¿Por qué no fui yo cortado delante de las tinieblas,
        Ni fue cubierto con oscuridad mi rostro?
""".trimIndent(),
        "Hechos 10:1-33" to """
        Pedro y Cornelio
        1 Había en Cesarea un hombre llamado Cornelio, centurión de la compañía llamada la Italiana, 
        2 piadoso y temeroso de Dios con toda su casa, y que hacía muchas limosnas al pueblo, y oraba a Dios siempre. 
        3 Este vio claramente en una visión, como a la hora novena del día, que un ángel de Dios entraba donde él estaba, y le decía: Cornelio. 
        4 Él, mirándole fijamente, y atemorizado, dijo: ¿Qué es, Señor? Y le dijo: Tus oraciones y tus limosnas han subido para memoria delante de Dios. 
        5 Envía, pues, ahora hombres a Jope, y haz venir a Simón, el que tiene por sobrenombre Pedro. 
        6 Este posa en casa de cierto Simón curtidor, que tiene su casa junto al mar; él te dirá lo que es necesario que hagas. 
        7 Ido el ángel que hablaba con Cornelio, este llamó a dos de sus criados, y a un devoto soldado de los que le asistían; 
        8 a los cuales envió a Jope, después de haberles contado todo.
        9 Al día siguiente, mientras ellos iban por el camino y se acercaban a la ciudad, Pedro subió a la azotea para orar, cerca de la hora sexta. 
        10 Y tuvo gran hambre, y quiso comer; pero mientras le preparaban algo, le sobrevino un éxtasis; 
        11 y vio el cielo abierto, y que descendía algo semejante a un gran lienzo, que atado de las cuatro puntas era bajado a la tierra; 
        12 en el cual había de todos los cuadrúpedos terrestres y reptiles y aves del cielo. 
        13 Y le vino una voz: Levántate, Pedro, mata y come. 
        14 Entonces Pedro dijo: Señor, no; porque ninguna cosa común o inmunda he comido jamás. 
        15 Volvió la voz a él la segunda vez: Lo que Dios limpió, no lo llames tú común. 
        16 Esto se hizo tres veces; y aquel lienzo volvió a ser recogido en el cielo.
        17 Y mientras Pedro estaba perplejo dentro de sí sobre lo que significaría la visión que había visto, he aquí los hombres que habían sido enviados por Cornelio, los cuales, preguntando por la casa de Simón, llegaron a la puerta. 
        18 Y llamando, preguntaron si moraba allí un Simón que tenía por sobrenombre Pedro. 
        19 Y mientras Pedro pensaba en la visión, le dijo el Espíritu: He aquí, tres hombres te buscan. 
        20 Levántate, pues, y desciende y no dudes de ir con ellos, porque yo los he enviado. 
        21 Entonces Pedro, descendiendo a donde estaban los hombres que fueron enviados por Cornelio, les dijo: He aquí, yo soy el que buscáis; ¿cuál es la causa por la que habéis venido? 
        22 Ellos dijeron: Cornelio el centurión, varón justo y temeroso de Dios, y que tiene buen testimonio en toda la nación de los judíos, ha recibido instrucciones de un santo ángel, de hacerte venir a su casa para oír tus palabras. 
        23 Entonces, haciéndoles entrar, los hospedó. Y al día siguiente, levantándose, se fue con ellos; y le acompañaron algunos de los hermanos de Jope.
        24 Al otro día entraron en Cesarea. Y Cornelio los estaba esperando, habiendo convocado a sus parientes y amigos más íntimos. 
        25 Cuando Pedro entró, salió Cornelio a recibirle, y postrándose a sus pies, adoró. 
        26 Mas Pedro le levantó, diciendo: Levántate, pues yo mismo también soy hombre. 
        27 Y hablando con él, entró, y halló a muchos que se habían reunido. 
        28 Y les dijo: Vosotros sabéis cuán abominable es para un varón judío juntarse o acercarse a un extranjero; pero a mí me ha mostrado Dios que a ningún hombre llame común o inmundo; 
        29 por lo cual, al ser llamado, vine sin replicar. Así que pregunto: ¿Por qué causa me habéis hecho venir?
        30 Entonces Cornelio dijo: Hace cuatro días que a esta hora yo estaba en ayunas; y a la hora novena, mientras oraba en mi casa, vi que se puso delante de mí un varón con vestido resplandeciente, 
        31 y dijo: Cornelio, tu oración ha sido oída, y tus limosnas han sido recordadas delante de Dios.
        32 Envía, pues, a Jope, y haz venir a Simón el que tiene por sobrenombre Pedro, el cual mora en casa de Simón, un curtidor, junto al mar; y cuando llegue, él te hablará. 
        33 Así que luego envié por ti; y tú has hecho bien en venir. Ahora, pues, todos nosotros estamos aquí en la presencia de Dios, para oír todo lo que Dios te ha mandado.
""".trimIndent(),
        "Josué 7-8" to """
        Josué 7
        El pecado de Acán
        1 Pero los hijos de Israel cometieron una prevaricación en cuanto al anatema; porque Acán hijo de Carmi, hijo de Zabdi, hijo de Zera, de la tribu de Judá, tomó del anatema; y la ira de Jehová se encendió contra los hijos de Israel.
        2 Después Josué envió hombres desde Jericó a Hai, que estaba junto a Bet-avén hacia el oriente de Bet-el; y les habló diciendo: Subid y reconoced la tierra. Y ellos subieron y reconocieron a Hai. 
        3 Y volviendo a Josué, le dijeron: No suba todo el pueblo, sino suban como dos mil o tres mil hombres, y tomarán a Hai; no fatigues a todo el pueblo yendo allí, porque son pocos. 
        4 Y subieron allá del pueblo como tres mil hombres, los cuales huyeron delante de los de Hai. 
        5 Y los de Hai mataron de ellos a unos treinta y seis hombres, y los siguieron desde la puerta hasta Sebarim, y los derrotaron en la bajada; por lo cual el corazón del pueblo desfalleció y vino a ser como agua.
        6 Entonces Josué rompió sus vestidos, y se postró en tierra sobre su rostro delante del arca de Jehová hasta caer la tarde, él y los ancianos de Israel; y echaron polvo sobre sus cabezas. 
        7 Y Josué dijo: ¡Ah, Señor Jehová! ¿Por qué hiciste pasar a este pueblo el Jordán, para entregarnos en las manos de los amorreos, para que nos destruyan? ¡Ojalá nos hubiéramos quedado al otro lado del Jordán! 
        8 ¡Ay, Señor! ¿qué diré, ya que Israel ha vuelto la espalda delante de sus enemigos? 
        9 Porque los cananeos y todos los moradores de la tierra oirán, y nos rodearán, y borrarán nuestro nombre de sobre la tierra; y entonces, ¿qué harás tú a tu grande nombre?
        10 Y Jehová dijo a Josué: Levántate; ¿por qué te postras así sobre tu rostro? 
        11 Israel ha pecado, y aun han quebrantado mi pacto que yo les mandé; y también han tomado del anatema, y hasta han hurtado, han mentido, y aun lo han guardado entre sus enseres. 
        12 Por esto los hijos de Israel no podrán hacer frente a sus enemigos, sino que delante de sus enemigos volverán la espalda, por cuanto han venido a ser anatema; ni estaré más con vosotros, si no destruyereis el anatema de en medio de vosotros. 
        13 Levántate, santifica al pueblo, y di: Santificaos para mañana; porque Jehová el Dios de Israel dice así: Anatema hay en medio de ti, Israel; no podrás hacer frente a tus enemigos, hasta que hayáis quitado el anatema de en medio de vosotros. 
        14 Os acercaréis, pues, mañana por vuestras tribus; y la tribu que Jehová tomare, se acercará por sus familias; y la familia que Jehová tomare, se acercará por sus casas; y la casa que Jehová tomare, se acercará por los varones; 
        15 y el que fuere sorprendido en el anatema, será quemado, él y todo lo que tiene, por cuanto ha quebrantado el pacto de Jehová, y ha cometido maldad en Israel.
        16 Josué, pues, levantándose de mañana, hizo acercar a Israel por sus tribus; y fue tomada la tribu de Judá. 
        17 Y haciendo acercar a la tribu de Judá, fue tomada la familia de los de Zera; y haciendo luego acercar a la familia de los de Zera por los varones, fue tomado Zabdi. 
        18 Hizo acercar su casa por los varones, y fue tomado Acán hijo de Carmi, hijo de Zabdi, hijo de Zera, de la tribu de Judá. 
        19 Entonces Josué dijo a Acán: Hijo mío, da gloria a Jehová el Dios de Israel, y dale alabanza, y declárame ahora lo que has hecho; no me lo encubras. 
        20 Y Acán respondió a Josué diciendo: Verdaderamente yo he pecado contra Jehová el Dios de Israel, y así y así he hecho. 
        21 Pues vi entre los despojos un manto babilónico muy bueno, y doscientos siclos de plata, y un lingote de oro de peso de cincuenta siclos, lo cual codicié y tomé; y he aquí que está escondido bajo tierra en medio de mi tienda, y el dinero debajo de ello.
        22 Josué entonces envió mensajeros, los cuales fueron corriendo a la tienda; y he aquí estaba escondido en su tienda, y el dinero debajo de ello. 
        23 Y tomándolo de en medio de la tienda, lo trajeron a Josué y a todos los hijos de Israel, y lo pusieron delante de Jehová. 
        24 Entonces Josué, y todo Israel con él, tomaron a Acán hijo de Zera, el dinero, el manto, el lingote de oro, sus hijos, sus hijas, sus bueyes, sus asnos, sus ovejas, su tienda y todo cuanto tenía, y lo llevaron todo al valle de Acor. 
        25 Y le dijo Josué: ¿Por qué nos has turbado? Túrbete Jehová en este día. Y todos los israelitas los apedrearon, y los quemaron después de apedrearlos. 
        26 Y levantaron sobre él un gran montón de piedras, que permanece hasta hoy. Y Jehová se volvió del ardor de su ira. Y por esto aquel lugar se llama el Valle de Acor,[a] hasta hoy.
        Josué 8
        Toma y destrucción de Hai
        1 Jehová dijo a Josué: No temas ni desmayes; toma contigo toda la gente de guerra, y levántate y sube a Hai. Mira, yo he entregado en tu mano al rey de Hai, a su pueblo, a su ciudad y a su tierra. 
        2 Y harás a Hai y a su rey como hiciste a Jericó y a su rey; solo que sus despojos y sus bestias tomaréis para vosotros. Pondrás, pues, emboscadas a la ciudad detrás de ella.
        3 Entonces se levantaron Josué y toda la gente de guerra, para subir contra Hai; y escogió Josué treinta mil hombres fuertes, los cuales envió de noche. 
        4 Y les mandó, diciendo: Atended, pondréis emboscada a la ciudad detrás de ella; no os alejaréis mucho de la ciudad, y estaréis todos dispuestos. 
        5 Y yo y todo el pueblo que está conmigo nos acercaremos a la ciudad; y cuando salgan ellos contra nosotros, como hicieron antes, huiremos delante de ellos. 
        6 Y ellos saldrán tras nosotros, hasta que los alejemos de la ciudad; porque dirán: Huyen de nosotros como la primera vez. Huiremos, pues, delante de ellos. 
        7 Entonces vosotros os levantaréis de la emboscada y tomaréis la ciudad; pues Jehová vuestro Dios la entregará en vuestras manos. 
        8 Y cuando la hayáis tomado, le prenderéis fuego. Haréis conforme a la palabra de Jehová; mirad que os lo he mandado. 
        9 Entonces Josué los envió; y ellos se fueron a la emboscada, y se pusieron entre Bet-el y Hai, al occidente de Hai; y Josué se quedó aquella noche en medio del pueblo.
        10 Levantándose Josué muy de mañana, pasó revista al pueblo, y subió él, con los ancianos de Israel, delante del pueblo contra Hai. 
        11 Y toda la gente de guerra que con él estaba, subió y se acercó, y llegaron delante de la ciudad, y acamparon al norte de Hai; y el valle estaba entre él y Hai. 
        12 Y tomó como cinco mil hombres, y los puso en emboscada entre Bet-el y Hai, al occidente de la ciudad. 
        13 Así dispusieron al pueblo: todo el campamento al norte de la ciudad, y su emboscada al occidente de la ciudad, y Josué avanzó aquella noche hasta la mitad del valle. 
        14 Y aconteció que viéndolo el rey de Hai, él y su pueblo se apresuraron y madrugaron; y al tiempo señalado, los hombres de la ciudad salieron al encuentro de Israel para combatir, frente al Arabá, no sabiendo que estaba puesta emboscada a espaldas de la ciudad. 
        15 Entonces Josué y todo Israel se fingieron vencidos y huyeron delante de ellos por el camino del desierto. 
        16 Y todo el pueblo que estaba en Hai se juntó para seguirles; y siguieron a Josué, siendo así alejados de la ciudad. 
        17 Y no quedó hombre en Hai ni en Bet-el, que no saliera tras de Israel; y por seguir a Israel dejaron la ciudad abierta.
        18 Entonces Jehová dijo a Josué: Extiende la lanza que tienes en tu mano hacia Hai, porque yo la entregaré en tu mano. Y Josué extendió hacia la ciudad la lanza que en su mano tenía. 
        19 Y levantándose prontamente de su lugar los que estaban en la emboscada, corrieron luego que él alzó su mano, y vinieron a la ciudad, y la tomaron, y se apresuraron a prenderle fuego. 
        20 Y los hombres de Hai volvieron el rostro, y al mirar, he aquí que el humo de la ciudad subía al cielo, y no pudieron huir ni a una parte ni a otra, porque el pueblo que iba huyendo hacia el desierto se volvió contra los que les seguían. 
        21 Josué y todo Israel, viendo que los de la emboscada habían tomado la ciudad, y que el humo de la ciudad subía, se volvieron y atacaron a los de Hai. 
        22 Y los otros salieron de la ciudad a su encuentro, y así fueron encerrados en medio de Israel, los unos por un lado, y los otros por el otro. Y los hirieron hasta que no quedó ninguno de ellos que escapase. 
        23 Pero tomaron vivo al rey de Hai, y lo trajeron a Josué.
        24 Y cuando los israelitas acabaron de matar a todos los moradores de Hai en el campo y en el desierto a donde los habían perseguido, y todos habían caído a filo de espada hasta ser consumidos, todos los israelitas volvieron a Hai, y también la hirieron a filo de espada. 
        25 Y el número de los que cayeron aquel día, hombres y mujeres, fue de doce mil, todos los de Hai. 
        26 Porque Josué no retiró su mano que había extendido con la lanza, hasta que hubo destruido por completo a todos los moradores de Hai. 
        27 Pero los israelitas tomaron para sí las bestias y los despojos de la ciudad, conforme a la palabra de Jehová que le había mandado a Josué. 
        28 Y Josué quemó a Hai y la redujo a un montón de escombros, asolada para siempre hasta hoy. 
        29 Y al rey de Hai lo colgó de un madero hasta caer la noche; y cuando el sol se puso, mandó Josué que quitasen del madero su cuerpo, y lo echasen a la puerta de la ciudad; y levantaron sobre él un gran montón de piedras, que permanece hasta hoy.
        Lectura de la ley en el monte Ebal
        30 Entonces Josué edificó un altar a Jehová Dios de Israel en el monte Ebal, 
        31 como Moisés siervo de Jehová lo había mandado a los hijos de Israel, como está escrito en el libro de la ley de Moisés, un altar de piedras enteras sobre las cuales nadie alzó hierro; y ofrecieron sobre él holocaustos a Jehová, y sacrificaron ofrendas de paz. 
        32 También escribió allí sobre las piedras una copia de la ley de Moisés, la cual escribió delante de los hijos de Israel. 
        33 Y todo Israel, con sus ancianos, oficiales y jueces, estaba de pie a uno y otro lado del arca, en presencia de los sacerdotes levitas que llevaban el arca del pacto de Jehová, así los extranjeros como los naturales. La mitad de ellos estaba hacia el monte Gerizim, y la otra mitad hacia el monte Ebal, de la manera que Moisés, siervo de Jehová, lo había mandado antes, para que bendijesen primeramente al pueblo de Israel. 
        34 Después de esto, leyó todas las palabras de la ley, las bendiciones y las maldiciones, conforme a todo lo que está escrito en el libro de la ley. 
        35 No hubo palabra alguna de todo cuanto mandó Moisés, que Josué no hiciese leer delante de toda la congregación de Israel, y de las mujeres, de los niños, y de los extranjeros que moraban entre ellos.
""".trimIndent(),
        "Job 24" to """
        Job se queja de que Dios es indiferente ante la maldad
        1 Puesto que no son ocultos los tiempos al Todopoderoso,
        ¿Por qué los que le conocen no ven sus días?
        2 Traspasan los linderos,
        Roban los ganados, y los apacientan.
        3 Se llevan el asno de los huérfanos,
        Y toman en prenda el buey de la viuda.
        4 Hacen apartar del camino a los menesterosos,
        Y todos los pobres de la tierra se esconden.
        5 He aquí, como asnos monteses en el desierto,
        Salen a su obra madrugando para robar;
        El desierto es mantenimiento de sus hijos.
        6 En el campo siegan su pasto,
        Y los impíos vendimian la viña ajena.
        7 Al desnudo hacen dormir sin ropa,
        Sin tener cobertura contra el frío.
        8 Con las lluvias de los montes se mojan,
        Y abrazan las peñas por falta de abrigo.
        9 Quitan el pecho a los huérfanos,
        Y de sobre el pobre toman la prenda.
        10 Al desnudo hacen andar sin vestido,
        Y a los hambrientos quitan las gavillas.
        11 Dentro de sus paredes exprimen el aceite,
        Pisan los lagares, y mueren de sed.
        12 Desde la ciudad gimen los moribundos,
        Y claman las almas de los heridos de muerte,
        Pero Dios no atiende su oración.
        13 Ellos son los que, rebeldes a la luz,
        Nunca conocieron sus caminos,
        Ni estuvieron en sus veredas.
        14 A la luz se levanta el matador; mata al pobre y al necesitado,
        Y de noche es como ladrón.
        15 El ojo del adúltero está aguardando la noche,
        Diciendo: No me verá nadie;
        Y esconde su rostro.
        16 En las tinieblas minan las casas
        Que de día para sí señalaron;
        No conocen la luz.
        17 Porque la mañana es para todos ellos como sombra de muerte;
        Si son conocidos, terrores de sombra de muerte los toman.
        18 Huyen ligeros como corriente de aguas;
        Su porción es maldita en la tierra;
        No andarán por el camino de las viñas.
        19 La sequía y el calor arrebatan las aguas de la nieve;
        Así también el Seol a los pecadores.
        20 Los olvidará el seno materno; de ellos sentirán los gusanos dulzura;
        Nunca más habrá de ellos memoria,
        Y como un árbol los impíos serán quebrantados.
        21 A la mujer estéril, que no concebía, afligió,
        Y a la viuda nunca hizo bien.
        22 Pero a los fuertes adelantó con su poder;
        Una vez que se levante, ninguno está seguro de la vida.
        23 Él les da seguridad y confianza;
        Sus ojos están sobre los caminos de ellos.
        24 Fueron exaltados un poco, mas desaparecen,
        Y son abatidos como todos los demás;
        Serán encerrados, y cortados como cabezas de espigas.
        25 Y si no, ¿quién me desmentirá ahora,
        O reducirá a nada mis palabras?
""".trimIndent(),
        "Hechos 10:34-48" to """
        34 Entonces Pedro, abriendo la boca, dijo: En verdad comprendo que Dios no hace acepción de personas, 
        35 sino que en toda nación se agrada del que le teme y hace justicia. 
        36 Dios envió mensaje a los hijos de Israel, anunciando el evangelio de la paz por medio de Jesucristo; este es Señor de todos. 
        37 Vosotros sabéis lo que se divulgó por toda Judea, comenzando desde Galilea, después del bautismo que predicó Juan: 
        38 cómo Dios ungió con el Espíritu Santo y con poder a Jesús de Nazaret, y cómo este anduvo haciendo bienes y sanando a todos los oprimidos por el diablo, porque Dios estaba con él. 
        39 Y nosotros somos testigos de todas las cosas que Jesús hizo en la tierra de Judea y en Jerusalén; a quien mataron colgándole en un madero. 
        40 A este levantó Dios al tercer día, e hizo que se manifestase; 
        41 no a todo el pueblo, sino a los testigos que Dios había ordenado de antemano, a nosotros que comimos y bebimos con él después que resucitó de los muertos. 
        42 Y nos mandó que predicásemos al pueblo, y testificásemos que él es el que Dios ha puesto por Juez de vivos y muertos. 
        43 De este dan testimonio todos los profetas, que todos los que en él creyeren, recibirán perdón de pecados por su nombre.
        44 Mientras aún hablaba Pedro estas palabras, el Espíritu Santo cayó sobre todos los que oían el discurso. 
        45 Y los fieles de la circuncisión que habían venido con Pedro se quedaron atónitos de que también sobre los gentiles se derramase el don del Espíritu Santo. 
        46 Porque los oían que hablaban en lenguas, y que magnificaban a Dios. 
        47 Entonces respondió Pedro: ¿Puede acaso alguno impedir el agua, para que no sean bautizados estos que han recibido el Espíritu Santo también como nosotros? 
        48 Y mandó bautizarles en el nombre del Señor Jesús. Entonces le rogaron que se quedase por algunos días.
""".trimIndent(),
        "Josué 9-10" to """
        Josué 9
        Astucia de los gabaonitas
        1 Cuando oyeron estas cosas todos los reyes que estaban a este lado del Jordán, así en las montañas como en los llanos, y en toda la costa del Mar Grande delante del Líbano, los heteos, amorreos, cananeos, ferezeos, heveos y jebuseos, 
        2 se concertaron para pelear contra Josué e Israel.
        3 Mas los moradores de Gabaón, cuando oyeron lo que Josué había hecho a Jericó y a Hai, 
        4 usaron de astucia; pues fueron y se fingieron embajadores, y tomaron sacos viejos sobre sus asnos, y cueros viejos de vino, rotos y remendados, 
        5 y zapatos viejos y recosidos en sus pies, con vestidos viejos sobre sí; y todo el pan que traían para el camino era seco y mohoso. 
        6 Y vinieron a Josué al campamento en Gilgal, y le dijeron a él y a los de Israel: Nosotros venimos de tierra muy lejana; haced, pues, ahora alianza con nosotros. 
        7 Y los de Israel respondieron a los heveos: Quizá habitáis en medio de nosotros. ¿Cómo, pues, podremos hacer alianza con vosotros? 
        8 Ellos respondieron a Josué: Nosotros somos tus siervos. Y Josué les dijo: ¿Quiénes sois vosotros, y de dónde venís? 
        9 Y ellos respondieron: Tus siervos han venido de tierra muy lejana, por causa del nombre de Jehová tu Dios; porque hemos oído su fama, y todo lo que hizo en Egipto, 
        10 y todo lo que hizo a los dos reyes de los amorreos que estaban al otro lado del Jordán: a Sehón rey de Hesbón, y a Og rey de Basán, que estaba en Astarot. 
        11 Por lo cual nuestros ancianos y todos los moradores de nuestra tierra nos dijeron: Tomad en vuestras manos provisión para el camino, e id al encuentro de ellos, y decidles: Nosotros somos vuestros siervos; haced ahora alianza con nosotros. 
        12 Este nuestro pan lo tomamos caliente de nuestras casas para el camino el día que salimos para venir a vosotros; y helo aquí ahora ya seco y mohoso. 
        13 Estos cueros de vino también los llenamos nuevos; helos aquí ya rotos; también estos nuestros vestidos y nuestros zapatos están ya viejos a causa de lo muy largo del camino. 
        14 Y los hombres de Israel tomaron de las provisiones de ellos, y no consultaron a Jehová. 
        15 Y Josué hizo paz con ellos, y celebró con ellos alianza concediéndoles la vida; y también lo juraron los príncipes de la congregación.
        16 Pasados tres días después que hicieron alianza con ellos, oyeron que eran sus vecinos, y que habitaban en medio de ellos. 
        17 Y salieron los hijos de Israel, y al tercer día llegaron a las ciudades de ellos; y sus ciudades eran Gabaón, Cafira, Beerot y Quiriat-jearim. 
        18 Y no los mataron los hijos de Israel, por cuanto los príncipes de la congregación les habían jurado por Jehová el Dios de Israel. Y toda la congregación murmuraba contra los príncipes. 
        19 Mas todos los príncipes respondieron a toda la congregación: Nosotros les hemos jurado por Jehová Dios de Israel; por tanto, ahora no les podemos tocar. 
        20 Esto haremos con ellos: les dejaremos vivir, para que no venga ira sobre nosotros por causa del juramento que les hemos hecho. 
        21 Dijeron, pues, de ellos los príncipes: Dejadlos vivir; y fueron constituidos leñadores y aguadores para toda la congregación, concediéndoles la vida, según les habían prometido los príncipes.
        22 Y llamándolos Josué, les habló diciendo: ¿Por qué nos habéis engañado, diciendo: Habitamos muy lejos de vosotros, siendo así que moráis en medio de nosotros? 
        23 Ahora, pues, malditos sois, y no dejará de haber de entre vosotros siervos, y quien corte la leña y saque el agua para la casa de mi Dios. 
        24 Y ellos respondieron a Josué y dijeron: Como fue dado a entender a tus siervos que Jehová tu Dios había mandado a Moisés su siervo que os había de dar toda la tierra, y que había de destruir a todos los moradores de la tierra delante de vosotros, por esto temimos en gran manera por nuestras vidas a causa de vosotros, e hicimos esto. 
        25 Ahora, pues, henos aquí en tu mano; lo que te pareciere bueno y recto hacer de nosotros, hazlo. 
        26 Y él lo hizo así con ellos; pues los libró de la mano de los hijos de Israel, y no los mataron. 
        27 Y Josué los destinó aquel día a ser leñadores y aguadores para la congregación, y para el altar de Jehová en el lugar que Jehová eligiese, lo que son hasta hoy.
        Josué 10
        Derrota de los amorreos
        1 Cuando Adonisedec rey de Jerusalén oyó que Josué había tomado a Hai, y que la había asolado (como había hecho a Jericó y a su rey, así hizo a Hai y a su rey), y que los moradores de Gabaón habían hecho paz con los israelitas, y que estaban entre ellos, 
        2 tuvo gran temor; porque Gabaón era una gran ciudad, como una de las ciudades reales, y mayor que Hai, y todos sus hombres eran fuertes. 
        3 Por lo cual Adonisedec rey de Jerusalén envió a Hoham rey de Hebrón, a Piream rey de Jarmut, a Jafía rey de Laquis y a Debir rey de Eglón, diciendo: 
        4 Subid a mí y ayudadme, y combatamos a Gabaón; porque ha hecho paz con Josué y con los hijos de Israel. 
        5 Y cinco reyes de los amorreos, el rey de Jerusalén, el rey de Hebrón, el rey de Jarmut, el rey de Laquis y el rey de Eglón, se juntaron y subieron, ellos con todos sus ejércitos, y acamparon cerca de Gabaón, y pelearon contra ella.
        6 Entonces los moradores de Gabaón enviaron a decir a Josué al campamento en Gilgal: No niegues ayuda a tus siervos; sube prontamente a nosotros para defendernos y ayudarnos; porque todos los reyes de los amorreos que habitan en las montañas se han unido contra nosotros. 
        7 Y subió Josué de Gilgal, él y todo el pueblo de guerra con él, y todos los hombres valientes. 
        8 Y Jehová dijo a Josué: No tengas temor de ellos; porque yo los he entregado en tu mano, y ninguno de ellos prevalecerá delante de ti. 
        9 Y Josué vino a ellos de repente, habiendo subido toda la noche desde Gilgal. 
        10 Y Jehová los llenó de consternación delante de Israel, y los hirió con gran mortandad en Gabaón; y los siguió por el camino que sube a Bet-horón, y los hirió hasta Azeca y Maceda. 
        11 Y mientras iban huyendo de los israelitas, a la bajada de Bet-horón, Jehová arrojó desde el cielo grandes piedras sobre ellos hasta Azeca, y murieron; y fueron más los que murieron por las piedras del granizo, que los que los hijos de Israel mataron a espada.
        12 Entonces Josué habló a Jehová el día en que Jehová entregó al amorreo delante de los hijos de Israel, y dijo en presencia de los israelitas:
        Sol, detente en Gabaón;
        Y tú, luna, en el valle de Ajalón.
        13 Y el sol se detuvo y la luna se paró,
        Hasta que la gente se hubo vengado de sus enemigos.
        ¿No está escrito esto en el libro de Jaser? Y el sol se paró en medio del cielo, y no se apresuró a ponerse casi un día entero. 
        14 Y no hubo día como aquel, ni antes ni después de él, habiendo atendido Jehová a la voz de un hombre; porque Jehová peleaba por Israel.
        15 Y Josué, y todo Israel con él, volvió al campamento en Gilgal.
        16 Y los cinco reyes huyeron, y se escondieron en una cueva en Maceda. 
        17 Y fue dado aviso a Josué que los cinco reyes habían sido hallados escondidos en una cueva en Maceda. 
        18 Entonces Josué dijo: Rodad grandes piedras a la entrada de la cueva, y poned hombres junto a ella para que los guarden; 
        19 y vosotros no os detengáis, sino seguid a vuestros enemigos, y heridles la retaguardia, sin dejarles entrar en sus ciudades; porque Jehová vuestro Dios los ha entregado en vuestra mano. 
        20 Y aconteció que cuando Josué y los hijos de Israel acabaron de herirlos con gran mortandad hasta destruirlos, los que quedaron de ellos se metieron en las ciudades fortificadas. 
        21 Todo el pueblo volvió sano y salvo a Josué, al campamento en Maceda; no hubo quien moviese su lengua contra ninguno de los hijos de Israel.
        22 Entonces dijo Josué: Abrid la entrada de la cueva, y sacad de ella a esos cinco reyes. 
        23 Y lo hicieron así, y sacaron de la cueva a aquellos cinco reyes: al rey de Jerusalén, al rey de Hebrón, al rey de Jarmut, al rey de Laquis y al rey de Eglón. 
        24 Y cuando los hubieron llevado a Josué, llamó Josué a todos los varones de Israel, y dijo a los principales de la gente de guerra que habían venido con él: Acercaos, y poned vuestros pies sobre los cuellos de estos reyes. Y ellos se acercaron y pusieron sus pies sobre los cuellos de ellos. 
        25 Y Josué les dijo: No temáis, ni os atemoricéis; sed fuertes y valientes, porque así hará Jehová a todos vuestros enemigos contra los cuales peleáis. 
        26 Y después de esto Josué los hirió y los mató, y los hizo colgar en cinco maderos; y quedaron colgados en los maderos hasta caer la noche. 
        27 Y cuando el sol se iba a poner, mandó Josué que los quitasen de los maderos, y los echasen en la cueva donde se habían escondido; y pusieron grandes piedras a la entrada de la cueva, las cuales permanecen hasta hoy.
        28 En aquel mismo día tomó Josué a Maceda, y la hirió a filo de espada, y mató a su rey; por completo los destruyó, con todo lo que en ella tenía vida, sin dejar nada; e hizo al rey de Maceda como había hecho al rey de Jericó.
        29 Y de Maceda pasó Josué, y todo Israel con él, a Libna; y peleó contra Libna; 
        30 y Jehová la entregó también a ella y a su rey en manos de Israel; y la hirió a filo de espada, con todo lo que en ella tenía vida, sin dejar nada; e hizo a su rey de la manera como había hecho al rey de Jericó.
        31 Y Josué, y todo Israel con él, pasó de Libna a Laquis, y acampó cerca de ella, y la combatió; 
        32 y Jehová entregó a Laquis en mano de Israel, y la tomó al día siguiente, y la hirió a filo de espada, con todo lo que en ella tenía vida, así como había hecho en Libna.
        33 Entonces Horam rey de Gezer subió en ayuda de Laquis; mas a él y a su pueblo destruyó Josué, hasta no dejar a ninguno de ellos.
        34 De Laquis pasó Josué, y todo Israel con él, a Eglón; y acamparon cerca de ella, y la combatieron; 
        35 y la tomaron el mismo día, y la hirieron a filo de espada; y aquel día mató a todo lo que en ella tenía vida, como había hecho en Laquis.
        36 Subió luego Josué, y todo Israel con él, de Eglón a Hebrón, y la combatieron. 
        37 Y tomándola, la hirieron a filo de espada, a su rey y a todas sus ciudades, con todo lo que en ella tenía vida, sin dejar nada; como había hecho a Eglón, así la destruyeron con todo lo que en ella tenía vida.
        38 Después volvió Josué, y todo Israel con él, sobre Debir, y combatió contra ella; 
        39 y la tomó, y a su rey, y a todas sus ciudades; y las hirieron a filo de espada, y destruyeron todo lo que allí dentro tenía vida, sin dejar nada; como había hecho a Hebrón, y como había hecho a Libna y a su rey, así hizo a Debir y a su rey.
        40 Hirió, pues, Josué toda la región de las montañas, del Neguev, de los llanos y de las laderas, y a todos sus reyes, sin dejar nada; todo lo que tenía vida lo mató, como Jehová Dios de Israel se lo había mandado. 
        41 Y los hirió Josué desde Cades-barnea hasta Gaza, y toda la tierra de Gosén hasta Gabaón. 
        42 Todos estos reyes y sus tierras los tomó Josué de una vez; porque Jehová el Dios de Israel peleaba por Israel. 
        43 Y volvió Josué, y todo Israel con él, al campamento en Gilgal.
""".trimIndent(),
        "Job 25" to """
        Bildad niega que el hombre pueda ser justificado delante de Dios
        1 Respondió Bildad suhita, y dijo:
        2 El señorío y el temor están con él;
        Él hace paz en sus alturas.
        3 ¿Tienen sus ejércitos número?
        ¿Sobre quién no está su luz?
        4 ¿Cómo, pues, se justificará el hombre para con Dios?
        ¿Y cómo será limpio el que nace de mujer?
        5 He aquí que ni aun la misma luna será resplandeciente,
        Ni las estrellas son limpias delante de sus ojos;
        6 ¿Cuánto menos el hombre, que es un gusano,
        Y el hijo de hombre, también gusano?
""".trimIndent(),
        "Hechos 11:1-18" to """
        Informe de Pedro a la iglesia de Jerusalén
        1 Oyeron los apóstoles y los hermanos que estaban en Judea, que también los gentiles habían recibido la palabra de Dios. 
        2 Y cuando Pedro subió a Jerusalén, disputaban con él los que eran de la circuncisión, 
        3 diciendo: ¿Por qué has entrado en casa de hombres incircuncisos, y has comido con ellos? 
        4 Entonces comenzó Pedro a contarles por orden lo sucedido, diciendo: 
        5 Estaba yo en la ciudad de Jope orando, y vi en éxtasis una visión; algo semejante a un gran lienzo que descendía, que por las cuatro puntas era bajado del cielo y venía hasta mí. 
        6 Cuando fijé en él los ojos, consideré y vi cuadrúpedos terrestres, y fieras, y reptiles, y aves del cielo. 
        7 Y oí una voz que me decía: Levántate, Pedro, mata y come. 
        8 Y dije: Señor, no; porque ninguna cosa común o inmunda entró jamás en mi boca. 
        9 Entonces la voz me respondió del cielo por segunda vez: Lo que Dios limpió, no lo llames tú común. 
        10 Y esto se hizo tres veces, y volvió todo a ser llevado arriba al cielo. 
        11 Y he aquí, luego llegaron tres hombres a la casa donde yo estaba, enviados a mí desde Cesarea. 
        12 Y el Espíritu me dijo que fuese con ellos sin dudar. Fueron también conmigo estos seis hermanos, y entramos en casa de un varón, 
        13 quien nos contó cómo había visto en su casa un ángel, que se puso en pie y le dijo: Envía hombres a Jope, y haz venir a Simón, el que tiene por sobrenombre Pedro; 
        14 él te hablará palabras por las cuales serás salvo tú, y toda tu casa. 
        15 Y cuando comencé a hablar, cayó el Espíritu Santo sobre ellos también, como sobre nosotros al principio. 
        16 Entonces me acordé de lo dicho por el Señor, cuando dijo: Juan ciertamente bautizó en agua, mas vosotros seréis bautizados con el Espíritu Santo. 
        17 Si Dios, pues, les concedió también el mismo don que a nosotros que hemos creído en el Señor Jesucristo, ¿quién era yo que pudiese estorbar a Dios? 
        18 Entonces, oídas estas cosas, callaron, y glorificaron a Dios, diciendo: ¡De manera que también a los gentiles ha dado Dios arrepentimiento para vida!
""".trimIndent(),
        "Josué 11-12" to """
        Josué 11
        Derrota de la alianza de Jabín
        1 Cuando oyó esto Jabín rey de Hazor, envió mensaje a Jobab rey de Madón, al rey de Simrón, al rey de Acsaf, 
        2 y a los reyes que estaban en la región del norte en las montañas, y en el Arabá al sur de Cineret, en los llanos, y en las regiones de Dor al occidente; 
        3 y al cananeo que estaba al oriente y al occidente, al amorreo, al heteo, al ferezeo, al jebuseo en las montañas, y al heveo al pie de Hermón en tierra de Mizpa. 
        4 Estos salieron, y con ellos todos sus ejércitos, mucha gente, como la arena que está a la orilla del mar en multitud, con muchísimos caballos y carros de guerra. 
        5 Todos estos reyes se unieron, y vinieron y acamparon unidos junto a las aguas de Merom, para pelear contra Israel.
        6 Mas Jehová dijo a Josué: No tengas temor de ellos, porque mañana a esta hora yo entregaré a todos ellos muertos delante de Israel; desjarretarás sus caballos, y sus carros quemarás a fuego. 
        7 Y Josué, y toda la gente de guerra con él, vino de repente contra ellos junto a las aguas de Merom. 
        8 Y los entregó Jehová en manos de Israel, y los hirieron y los siguieron hasta Sidón la grande y hasta Misrefotmaim, y hasta el llano de Mizpa al oriente, hiriéndolos hasta que no les dejaron ninguno. 
        9 Y Josué hizo con ellos como Jehová le había mandado: desjarretó sus caballos, y sus carros quemó a fuego.
        10 Y volviendo Josué, tomó en el mismo tiempo a Hazor, y mató a espada a su rey; pues Hazor había sido antes cabeza de todos estos reinos. 
        11 Y mataron a espada todo cuanto en ella tenía vida, destruyéndolo por completo, sin quedar nada que respirase; y a Hazor pusieron fuego. 
        12 Asimismo tomó Josué todas las ciudades de aquellos reyes, y a todos los reyes de ellas, y los hirió a filo de espada, y los destruyó, como Moisés siervo de Jehová lo había mandado. 
        13 Pero a todas las ciudades que estaban sobre colinas, no las quemó Israel; únicamente a Hazor quemó Josué. 
        14 Y los hijos de Israel tomaron para sí todo el botín y las bestias de aquellas ciudades; mas a todos los hombres hirieron a filo de espada hasta destruirlos, sin dejar alguno con vida. 
        15 De la manera que Jehová lo había mandado a Moisés su siervo, así Moisés lo mandó a Josué; y así Josué lo hizo, sin quitar palabra de todo lo que Jehová había mandado a Moisés.
        Josué se apodera de toda la tierra
        16 Tomó, pues, Josué toda aquella tierra, las montañas, todo el Neguev, toda la tierra de Gosén, los llanos, el Arabá, las montañas de Israel y sus valles. 
        17 Desde el monte Halac, que sube hacia Seir, hasta Baal-gad en la llanura del Líbano, a la falda del monte Hermón; tomó asimismo a todos sus reyes, y los hirió y mató. 
        18 Por mucho tiempo tuvo guerra Josué con estos reyes. 
        19 No hubo ciudad que hiciese paz con los hijos de Israel, salvo los heveos que moraban en Gabaón; todo lo tomaron en guerra. 
        20 Porque esto vino de Jehová, que endurecía el corazón de ellos para que resistiesen con guerra a Israel, para destruirlos, y que no les fuese hecha misericordia, sino que fuesen desarraigados, como Jehová lo había mandado a Moisés.
        21 También en aquel tiempo vino Josué y destruyó a los anaceos de los montes de Hebrón, de Debir, de Anab, de todos los montes de Judá y de todos los montes de Israel; Josué los destruyó a ellos y a sus ciudades. 
        22 Ninguno de los anaceos quedó en la tierra de los hijos de Israel; solamente quedaron en Gaza, en Gat y en Asdod. 
        23 Tomó, pues, Josué toda la tierra, conforme a todo lo que Jehová había dicho a Moisés; y la entregó Josué a los israelitas por herencia conforme a su distribución según sus tribus; y la tierra descansó de la guerra.
        Josué 12
        Reyes derrotados por Moisés
        1 Estos son los reyes de la tierra que los hijos de Israel derrotaron y cuya tierra poseyeron al otro lado del Jordán hacia donde nace el sol, desde el arroyo de Arnón hasta el monte Hermón, y todo el Arabá al oriente: 
        2 Sehón rey de los amorreos, que habitaba en Hesbón, y señoreaba desde Aroer, que está a la ribera del arroyo de Arnón, y desde en medio del valle, y la mitad de Galaad, hasta el arroyo de Jaboc, término de los hijos de Amón; 
        3 y el Arabá hasta el mar de Cineret, al oriente; y hasta el mar del Arabá, el Mar Salado, al oriente, por el camino de Bet-jesimot, y desde el sur al pie de las laderas del Pisga. 
        4 Y el territorio de Og rey de Basán, que había quedado de los refaítas, el cual habitaba en Astarot y en Edrei, 
        5 y dominaba en el monte Hermón, en Salca, en todo Basán hasta los límites de Gesur y de Maaca, y la mitad de Galaad, territorio de Sehón rey de Hesbón. 
        6 A estos derrotaron Moisés siervo de Jehová y los hijos de Israel; y Moisés siervo de Jehová dio aquella tierra en posesión a los rubenitas, a los gaditas y a la media tribu de Manasés.
        Reyes derrotados por Josué
        7 Y estos son los reyes de la tierra que derrotaron Josué y los hijos de Israel, a este lado del Jordán hacia el occidente, desde Baal-gad en el llano del Líbano hasta el monte de Halac que sube hacia Seir; y Josué dio la tierra en posesión a las tribus de Israel, conforme a su distribución; 
        8 en las montañas, en los valles, en el Arabá, en las laderas, en el desierto y en el Neguev; el heteo, el amorreo, el cananeo, el ferezeo, el heveo y el jebuseo. 
        9 El rey de Jericó, uno; el rey de Hai, que está al lado de Bet-el, otro; 
        10 el rey de Jerusalén, otro; el rey de Hebrón, otro; 
        11 el rey de Jarmut, otro; el rey de Laquis, otro; 
        12 el rey de Eglón, otro; el rey de Gezer, otro; 13 el rey de Debir, otro; el rey de Geder, otro; 
        14 el rey de Horma, otro; el rey de Arad, otro; 15 el rey de Libna, otro; el rey de Adulam, otro; 
        16 el rey de Maceda, otro; el rey de Bet-el, otro; 
        17 el rey de Tapúa, otro; el rey de Hefer, otro; 
        18 el rey de Afec, otro; el rey de Sarón, otro; 19 el rey de Madón, otro; el rey de Hazor, otro; 
        20 el rey de Simron-merón, otro; el rey de Acsaf, otro; 21 el rey de Taanac, otro; el rey de Meguido, otro; 
        22 el rey de Cedes, otro; el rey de Jocneam del Carmelo, otro; 
        23 el rey de Dor, de la provincia de Dor, otro; el rey de Goim en Gilgal, otro; 
        24 el rey de Tirsa, otro; treinta y un reyes por todos.
""".trimIndent(),
        "Job 26" to """
        Job proclama la soberanía de Dios
        1 Respondió Job, y dijo:
        2 ¿En qué ayudaste al que no tiene poder?
        ¿Cómo has amparado al brazo sin fuerza?
        3 ¿En qué aconsejaste al que no tiene ciencia,
        Y qué plenitud de inteligencia has dado a conocer?
        4 ¿A quién has anunciado palabras,
        Y de quién es el espíritu que de ti procede?
        5 Las sombras tiemblan en lo profundo,
        Los mares y cuanto en ellos mora.
        6 El Seol está descubierto delante de él, y el Abadón no tiene cobertura.
        7 Él extiende el norte sobre vacío,
        Cuelga la tierra sobre nada.
        8 Ata las aguas en sus nubes,
        Y las nubes no se rompen debajo de ellas.
        9 Él encubre la faz de su trono,
        Y sobre él extiende su nube.
        10 Puso límite a la superficie de las aguas,
        Hasta el fin de la luz y las tinieblas.
        11 Las columnas del cielo tiemblan,
        Y se espantan a su reprensión.
        12 Él agita el mar con su poder,
        Y con su entendimiento hiere la arrogancia suya.
        13 Su espíritu adornó los cielos;
        Su mano creó la serpiente tortuosa.
        14 He aquí, estas cosas son solo los bordes de sus caminos;
        ¡Y cuán leve es el susurro que hemos oído de él!
        Pero el trueno de su poder, ¿quién lo puede comprender?
""".trimIndent(),
        "Hechos 11:19-30" to """
        La iglesia en Antioquía
        19 Ahora bien, los que habían sido esparcidos a causa de la persecución que hubo con motivo de Esteban, pasaron hasta Fenicia, Chipre y Antioquía, no hablando a nadie la palabra, sino solo a los judíos. 
        20 Pero había entre ellos unos varones de Chipre y de Cirene, los cuales, cuando entraron en Antioquía, hablaron también a los griegos, anunciando el evangelio del Señor Jesús. 
        21 Y la mano del Señor estaba con ellos, y gran número creyó y se convirtió al Señor. 
        22 Llegó la noticia de estas cosas a oídos de la iglesia que estaba en Jerusalén; y enviaron a Bernabé que fuese hasta Antioquía. 
        23 Este, cuando llegó, y vio la gracia de Dios, se regocijó, y exhortó a todos a que con propósito de corazón permaneciesen fieles al Señor. 
        24 Porque era varón bueno, y lleno del Espíritu Santo y de fe. Y una gran multitud fue agregada al Señor. 
        25 Después fue Bernabé a Tarso para buscar a Saulo; y hallándole, le trajo a Antioquía. 
        26 Y se congregaron allí todo un año con la iglesia, y enseñaron a mucha gente; y a los discípulos se les llamó cristianos por primera vez en Antioquía.
        27 En aquellos días unos profetas descendieron de Jerusalén a Antioquía. 
        28 Y levantándose uno de ellos, llamado Agabo, daba a entender por el Espíritu, que vendría una gran hambre en toda la tierra habitada; la cual sucedió en tiempo de Claudio. 
        29 Entonces los discípulos, cada uno conforme a lo que tenía, determinaron enviar socorro a los hermanos que habitaban en Judea; 
        30 lo cual en efecto hicieron, enviándolo a los ancianos por mano de Bernabé y de Saulo.
""".trimIndent(),
        "Josué 13-14" to """
        Josué 13
        Tierra aún sin conquistar
        1 Siendo Josué ya viejo, entrado en años, Jehová le dijo: Tú eres ya viejo, de edad avanzada, y queda aún mucha tierra por poseer. 
        2 Esta es la tierra que queda: todos los territorios de los filisteos, y todos los de los gesureos; 
        3 desde Sihor, que está al oriente de Egipto, hasta el límite de Ecrón al norte, que se considera de los cananeos; de los cinco príncipes de los filisteos, el gazeo, el asdodeo, el ascaloneo, el geteo y el ecroneo; también los aveos; 
        4 al sur toda la tierra de los cananeos, y Mehara, que es de los sidonios, hasta Afec, hasta los límites del amorreo; 
        5 la tierra de los giblitas, y todo el Líbano hacia donde sale el sol, desde Baal-gad al pie del monte Hermón, hasta la entrada de Hamat; 
        6 todos los que habitan en las montañas desde el Líbano hasta Misrefotmaim, todos los sidonios; yo los exterminaré delante de los hijos de Israel; solamente repartirás tú por suerte el país a los israelitas por heredad, como te he mandado. 
        7 Reparte, pues, ahora esta tierra en heredad a las nueve tribus, y a la media tribu de Manasés.
        8 Porque los rubenitas y gaditas y la otra mitad de Manasés recibieron ya su heredad, la cual les dio Moisés al otro lado del Jordán al oriente, según se la dio Moisés siervo de Jehová; 
        9 desde Aroer, que está a la orilla del arroyo de Arnón, y la ciudad que está en medio del valle, y toda la llanura de Medeba, hasta Dibón; 
        10 todas las ciudades de Sehón rey de los amorreos, el cual reinó en Hesbón, hasta los límites de los hijos de Amón; 
        11 y Galaad, y los territorios de los gesureos y de los maacateos, y todo el monte Hermón, y toda la tierra de Basán hasta Salca; 
        12 todo el reino de Og en Basán, el cual reinó en Astarot y en Edrei, el cual había quedado del resto de los refaítas; pues Moisés los derrotó, y los echó. 
        13 Mas a los gesureos y a los maacateos no los echaron los hijos de Israel, sino que Gesur y Maaca habitaron entre los israelitas hasta hoy.
        El territorio que distribuyó Moisés
        14 Pero a la tribu de Leví no dio heredad; los sacrificios de Jehová Dios de Israel son su heredad, como él les había dicho.
        15 Dio, pues, Moisés a la tribu de los hijos de Rubén conforme a sus familias. 
        16 Y fue el territorio de ellos desde Aroer, que está a la orilla del arroyo de Arnón, y la ciudad que está en medio del valle, y toda la llanura hasta Medeba; 
        17 Hesbón, con todas sus ciudades que están en la llanura; Dibón, Bamot-baal, Bet-baal-meón, 
        18 Jahaza, Cademot, Mefaat, 
        19 Quiriataim, Sibma, Zaret-sahar en el monte del valle, 
        20 Bet-peor, las laderas de Pisga, Bet-jesimot, 
        21 todas las ciudades de la llanura, y todo el reino de Sehón rey de los amorreos, que reinó en Hesbón, al cual derrotó Moisés, y a los príncipes de Madián, Evi, Requem, Zur, Hur y Reba, príncipes de Sehón que habitaban en aquella tierra. 
        22 También mataron a espada los hijos de Israel a Balaam el adivino, hijo de Beor, entre los demás que mataron. 
        23 Y el Jordán fue el límite del territorio de los hijos de Rubén. Esta fue la heredad de los hijos de Rubén conforme a sus familias, estas ciudades con sus aldeas.
        24 Dio asimismo Moisés a la tribu de Gad, a los hijos de Gad, conforme a sus familias. 
        25 El territorio de ellos fue Jazer, y todas las ciudades de Galaad, y la mitad de la tierra de los hijos de Amón hasta Aroer, que está enfrente de Rabá. 
        26 Y desde Hesbón hasta Ramat-mizpa, y Betonim; y desde Mahanaim hasta el límite de Debir; 
        27 y en el valle, Bet-aram, Bet-nimra, Sucot y Zafón, resto del reino de Sehón rey de Hesbón; el Jordán y su límite hasta el extremo del mar de Cineret al otro lado del Jordán, al oriente. 
        28 Esta es la heredad de los hijos de Gad por sus familias, estas ciudades con sus aldeas.
        29 También dio Moisés heredad a la media tribu de Manasés; y fue para la media tribu de los hijos de Manasés, conforme a sus familias. 
        30 El territorio de ellos fue desde Mahanaim, todo Basán, todo el reino de Og rey de Basán, y todas las aldeas de Jair que están en Basán, sesenta poblaciones, 
        31 y la mitad de Galaad, y Astarot y Edrei, ciudades del reino de Og en Basán, para los hijos de Maquir hijo de Manasés, para la mitad de los hijos de Maquir conforme a sus familias.
        32 Esto es lo que Moisés repartió en heredad en los llanos de Moab, al otro lado del Jordán de Jericó, al oriente. 
        33 Mas a la tribu de Leví no dio Moisés heredad; Jehová Dios de Israel es la heredad de ellos, como él les había dicho.
        Josué 14
        Canaán repartida por suerte
        1 Esto, pues, es lo que los hijos de Israel tomaron por heredad en la tierra de Canaán, lo cual les repartieron el sacerdote Eleazar, Josué hijo de Nun, y los cabezas de los padres de las tribus de los hijos de Israel. 
        2 Por suerte se les dio su heredad, como Jehová había mandado a Moisés que se diera a las nueve tribus y a la media tribu. 
        3 Porque a las dos tribus y a la media tribu les había dado Moisés heredad al otro lado del Jordán; mas a los levitas no les dio heredad entre ellos. 
        4 Porque los hijos de José fueron dos tribus, Manasés y Efraín; y no dieron parte a los levitas en la tierra sino ciudades en que morasen, con los ejidos de ellas para sus ganados y rebaños. 
        5 De la manera que Jehová lo había mandado a Moisés, así lo hicieron los hijos de Israel en el repartimiento de la tierra.
        Caleb recibe Hebrón
        6 Y los hijos de Judá vinieron a Josué en Gilgal; y Caleb, hijo de Jefone cenezeo, le dijo: Tú sabes lo que Jehová dijo a Moisés, varón de Dios, en Cades-barnea, tocante a mí y a ti. 
        7 Yo era de edad de cuarenta años cuando Moisés siervo de Jehová me envió de Cades-barnea a reconocer la tierra; y yo le traje noticias como lo sentía en mi corazón. 
        8 Y mis hermanos, los que habían subido conmigo, hicieron desfallecer el corazón del pueblo; pero yo cumplí siguiendo a Jehová mi Dios. 
        9 Entonces Moisés juró diciendo: Ciertamente la tierra que holló tu pie será para ti, y para tus hijos en herencia perpetua, por cuanto cumpliste siguiendo a Jehová mi Dios. 
        10 Ahora bien, Jehová me ha hecho vivir, como él dijo, estos cuarenta y cinco años, desde el tiempo que Jehová habló estas palabras a Moisés, cuando Israel andaba por el desierto; y ahora, he aquí, hoy soy de edad de ochenta y cinco años. 
        11 Todavía estoy tan fuerte como el día que Moisés me envió; cual era mi fuerza entonces, tal es ahora mi fuerza para la guerra, y para salir y para entrar. 
        12 Dame, pues, ahora este monte, del cual habló Jehová aquel día; porque tú oíste en aquel día que los anaceos están allí, y que hay ciudades grandes y fortificadas. Quizá Jehová estará conmigo, y los echaré, como Jehová ha dicho.
        13 Josué entonces le bendijo, y dio a Caleb hijo de Jefone a Hebrón por heredad. 
        14 Por tanto, Hebrón vino a ser heredad de Caleb hijo de Jefone cenezeo, hasta hoy, por cuanto había seguido cumplidamente a Jehová Dios de Israel. 
        15 Mas el nombre de Hebrón fue antes Quiriat-arba;[a] porque Arba fue un hombre grande entre los anaceos. Y la tierra descansó de la guerra.
""".trimIndent(),
        "Job 27" to """
        Job describe el castigo de los malos
        1 Reasumió Job su discurso, y dijo:
        2 Vive Dios, que ha quitado mi derecho,
        Y el Omnipotente, que amargó el alma mía,
        3 Que todo el tiempo que mi alma esté en mí,
        Y haya hálito de Dios en mis narices,
        4 Mis labios no hablarán iniquidad,
        Ni mi lengua pronunciará engaño.
        5 Nunca tal acontezca que yo os justifique;
        Hasta que muera, no quitaré de mí mi integridad.
        6 Mi justicia tengo asida, y no la cederé;
        No me reprochará mi corazón en todos mis días.
        7 Sea como el impío mi enemigo,
        Y como el inicuo mi adversario.
        8 Porque ¿cuál es la esperanza del impío, por mucho que hubiere robado,
        Cuando Dios le quitare la vida?
        9 ¿Oirá Dios su clamor
        Cuando la tribulación viniere sobre él?
        10 ¿Se deleitará en el Omnipotente?
        ¿Invocará a Dios en todo tiempo?
        11 Yo os enseñaré en cuanto a la mano de Dios;
        No esconderé lo que hay para con el Omnipotente.
        12 He aquí que todos vosotros lo habéis visto;
        ¿Por qué, pues, os habéis hecho tan enteramente vanos?
        13 Esta es para con Dios la porción del hombre impío,
        Y la herencia que los violentos han de recibir del Omnipotente:
        14 Si sus hijos fueren multiplicados, serán para la espada;
        Y sus pequeños no se saciarán de pan.
        15 Los que de él quedaren, en muerte serán sepultados,
        Y no los llorarán sus viudas.
        16 Aunque amontone plata como polvo,
        Y prepare ropa como lodo;
        17 La habrá preparado él, mas el justo se vestirá,
        Y el inocente repartirá la plata.
        18 Edificó su casa como la polilla,
        Y como enramada que hizo el guarda.
        19 Rico se acuesta, pero por última vez;
        Abrirá sus ojos, y nada tendrá.
        20 Se apoderarán de él terrores como aguas;
        Torbellino lo arrebatará de noche.
        21 Le eleva el solano, y se va;
        Y tempestad lo arrebatará de su lugar.
        22 Dios, pues, descargará sobre él, y no perdonará;
        Hará él por huir de su mano.
        23 Batirán las manos sobre él,
        Y desde su lugar le silbarán.
""".trimIndent(),
        "Hechos 12" to """
        Jacobo, muerto; Pedro, encarcelado
        12 En aquel mismo tiempo el rey Herodes echó mano a algunos de la iglesia para maltratarles. 
        2 Y mató a espada a Jacobo, hermano de Juan. 
        3 Y viendo que esto había agradado a los judíos, procedió a prender también a Pedro. Eran entonces los días de los panes sin levadura. 
        4 Y habiéndole tomado preso, le puso en la cárcel, entregándole a cuatro grupos de cuatro soldados cada uno, para que le custodiasen; y se proponía sacarle al pueblo después de la pascua. 
        5 Así que Pedro estaba custodiado en la cárcel; pero la iglesia hacía sin cesar oración a Dios por él.
        Pedro es librado de la cárcel
        6 Y cuando Herodes le iba a sacar, aquella misma noche estaba Pedro durmiendo entre dos soldados, sujeto con dos cadenas, y los guardas delante de la puerta custodiaban la cárcel. 
        7 Y he aquí que se presentó un ángel del Señor, y una luz resplandeció en la cárcel; y tocando a Pedro en el costado, le despertó, diciendo: Levántate pronto. Y las cadenas se le cayeron de las manos. 
        8 Le dijo el ángel: Cíñete, y átate las sandalias. Y lo hizo así. Y le dijo: Envuélvete en tu manto, y sígueme.
        9 Y saliendo, le seguía; pero no sabía que era verdad lo que hacía el ángel, sino que pensaba que veía una visión. 
        10 Habiendo pasado la primera y la segunda guardia, llegaron a la puerta de hierro que daba a la ciudad, la cual se les abrió por sí misma; y salidos, pasaron una calle, y luego el ángel se apartó de él. 
        11 Entonces Pedro, volviendo en sí, dijo: Ahora entiendo verdaderamente que el Señor ha enviado su ángel, y me ha librado de la mano de Herodes, y de todo lo que el pueblo de los judíos esperaba.
        12 Y habiendo considerado esto, llegó a casa de María la madre de Juan, el que tenía por sobrenombre Marcos, donde muchos estaban reunidos orando. 
        13 Cuando llamó Pedro a la puerta del patio, salió a escuchar una muchacha llamada Rode, 
        14 la cual, cuando reconoció la voz de Pedro, de gozo no abrió la puerta, sino que corriendo adentro, dio la nueva de que Pedro estaba a la puerta. 
        15 Y ellos le dijeron: Estás loca. Pero ella aseguraba que así era. Entonces ellos decían: ¡Es su ángel! 
        16 Mas Pedro persistía en llamar; y cuando abrieron y le vieron, se quedaron atónitos. 
        17 Pero él, haciéndoles con la mano señal de que callasen, les contó cómo el Señor le había sacado de la cárcel. Y dijo: Haced saber esto a Jacobo y a los hermanos. Y salió, y se fue a otro lugar.
        18 Luego que fue de día, hubo no poco alboroto entre los soldados sobre qué había sido de Pedro. 
        19 Mas Herodes, habiéndole buscado sin hallarle, después de interrogar a los guardas, ordenó llevarlos a la muerte. Después descendió de Judea a Cesarea y se quedó allí.
        Muerte de Herodes
        20 Y Herodes estaba enojado contra los de Tiro y de Sidón; pero ellos vinieron de acuerdo ante él, y sobornado Blasto, que era camarero mayor del rey, pedían paz, porque su territorio era abastecido por el del rey. 
        21 Y un día señalado, Herodes, vestido de ropas reales, se sentó en el tribunal y les arengó. 
        22 Y el pueblo aclamaba gritando: ¡Voz de Dios, y no de hombre! 
        23 Al momento un ángel del Señor le hirió, por cuanto no dio la gloria a Dios; y expiró comido de gusanos.
        24 Pero la palabra del Señor crecía y se multiplicaba.
        25 Y Bernabé y Saulo, cumplido su servicio, volvieron de Jerusalén, llevando también consigo a Juan, el que tenía por sobrenombre Marcos.
""".trimIndent(),
        "Josué 15-17" to """
        Josué 15
        El territorio de Judá
        1 La parte que tocó en suerte a la tribu de los hijos de Judá, conforme a sus familias, llegaba hasta la frontera de Edom, teniendo el desierto de Zin al sur como extremo meridional. 
        2 Y su límite por el lado del sur fue desde la costa del Mar Salado, desde la bahía que mira hacia el sur; 
        3 y salía hacia el sur de la subida de Acrabim, pasando hasta Zin; y subiendo por el sur hasta Cades-barnea, pasaba a Hezrón, y subiendo por Adar daba vuelta a Carca. 
        4 De allí pasaba a Asmón, y salía al arroyo de Egipto, y terminaba en el mar. Este, pues, os será el límite del sur. 
        5 El límite oriental es el Mar Salado hasta la desembocadura del Jordán. Y el límite del lado del norte, desde la bahía del mar en la desembocadura del Jordán; 
        6 y sube este límite por Bet-hogla, y pasa al norte de Bet-arabá, y de aquí sube a la piedra de Bohán hijo de Rubén. 
        7 Luego sube a Debir desde el valle de Acor; y al norte mira sobre Gilgal, que está enfrente de la subida de Adumín, que está al sur del arroyo; y pasa hasta las aguas de En-semes, y sale a la fuente de Rogel. 
        8 Y sube este límite por el valle del hijo de Hinom al lado sur del jebuseo, que es Jerusalén. Luego sube por la cumbre del monte que está enfrente del valle de Hinom hacia el occidente, el cual está al extremo del valle de Refaim, por el lado del norte. 
        9 Y rodea este límite desde la cumbre del monte hasta la fuente de las aguas de Neftoa, y sale a las ciudades del monte de Efrón, rodeando luego a Baala, que es Quiriat-jearim. 
        10 Después gira este límite desde Baala hacia el occidente al monte de Seir; y pasa al lado del monte de Jearim hacia el norte, el cual es Quesalón, y desciende a Bet-semes, y pasa a Timna. 
        11 Sale luego al lado de Ecrón hacia el norte; y rodea a Sicrón, y pasa por el monte de Baala, y sale a Jabneel y termina en el mar. 
        12 El límite del occidente es el Mar Grande. Este fue el límite de los hijos de Judá, por todo el contorno, conforme a sus familias.
        Caleb conquista Hebrón y Debir
        13 Mas a Caleb hijo de Jefone dio su parte entre los hijos de Judá, conforme al mandamiento de Jehová a Josué; la ciudad de Quiriat-arba padre de Anac, que es Hebrón. 
        14 Y Caleb echó de allí a los tres hijos de Anac, a Sesai, Ahimán y Talmai, hijos de Anac. 
        15 De aquí subió contra los que moraban en Debir; y el nombre de Debir era antes Quiriat-sefer. 
        16 Y dijo Caleb: Al que atacare a Quiriat-sefer, y la tomare, yo le daré a mi hija Acsa por mujer. 
        17 Y la tomó Otoniel, hijo de Cenaz hermano de Caleb; y él le dio a su hija Acsa por mujer. 
        18 Y aconteció que cuando la llevaba, él la persuadió que pidiese a su padre tierras para labrar. Ella entonces se bajó del asno. Y Caleb le dijo: ¿Qué tienes? 
        19 Y ella respondió: Concédeme un don; puesto que me has dado tierra del Neguev, dame también fuentes de aguas. Él entonces le dio las fuentes de arriba, y las de abajo.
        Las ciudades de Judá
        20 Esta, pues, es la heredad de la tribu de los hijos de Judá por sus familias. 
        21 Y fueron las ciudades de la tribu de los hijos de Judá en el extremo sur, hacia la frontera de Edom: Cabseel, Edar, Jagur, 
        22 Cina, Dimona, Adada, 
        23 Cedes, Hazor, Itnán, 
        24 Zif, Telem, Bealot, 
        25 Hazor-hadata, Queriot, Hezrón (que es Hazor), 
        26 Amam, Sema, Molada, 27 Hazar-gada, Hesmón, Bet-pelet, 
        28 Hazar-sual, Beerseba, Bizotia, 29 Baala, Iim, Esem, 
        30 Eltolad, Quesil, Horma, 31 Siclag, Madmana, Sansana, 
        32 Lebaot, Silhim, Aín y Rimón; por todas veintinueve ciudades con sus aldeas.
        33 En las llanuras, Estaol, Zora, Asena, 
        34 Zanoa, En-ganim, Tapúa, Enam, 
        35 Jarmut, Adulam, Soco, Azeca, 
        36 Saaraim, Aditaim, Gedera y Gederotaim; catorce ciudades con sus aldeas.
        37 Zenán, Hadasa, Migdal-gad, 
        38 Dileán, Mizpa, Jocteel, 
        39 Laquis, Boscat, Eglón, 
        40 Cabón, Lahmam, Quitlis, 
        41 Gederot, Bet-dagón, Naama y Maceda; dieciséis ciudades con sus aldeas.
        42 Libna, Eter, Asán, 
        43 Jifta, Asena, Nezib, 
        44 Keila, Aczib y Maresa; nueve ciudades con sus aldeas.
        45 Ecrón con sus villas y sus aldeas. 
        46 Desde Ecrón hasta el mar, todas las que están cerca de Asdod con sus aldeas.
        47 Asdod con sus villas y sus aldeas; Gaza con sus villas y sus aldeas hasta el río de Egipto, y el Mar Grande con sus costas.
        48 Y en las montañas, Samir, Jatir, Soco, 
        49 Dana, Quiriat-sana (que es Debir); 
        50 Anab, Estemoa, Anim, 
        51 Gosén, Holón y Gilo; once ciudades con sus aldeas.
        52 Arab, Duma, Esán, 
        53 Janum, Bet-tapúa, Afeca, 
        54 Humta, Quiriat-arba (la cual es Hebrón) y Sior; nueve ciudades con sus aldeas.
        55 Maón, Carmel, Zif, Juta, 
        56 Jezreel, Jocdeam, Zanoa, 
        57 Caín, Gabaa y Timna; diez ciudades con sus aldeas.
        58 Halhul, Bet-sur, Gedor, 
        59 Maarat, Bet-anot y Eltecón; seis ciudades con sus aldeas.
        60 Quiriat-baal (que es Quiriat-jearim) y Rabá; dos ciudades con sus aldeas.
        61 En el desierto, Bet-arabá, Midín, Secaca, 
        62 Nibsán, la Ciudad de la Sal y En-gadi; seis ciudades con sus aldeas.
        63 Mas a los jebuseos que habitaban en Jerusalén, los hijos de Judá no pudieron arrojarlos; y ha quedado el jebuseo en Jerusalén con los hijos de Judá hasta hoy.
        Josué 16
        Territorio de Efraín y de Manasés
        1 Tocó en suerte a los hijos de José desde el Jordán de Jericó hasta las aguas de Jericó hacia el oriente, hacia el desierto que sube de Jericó por las montañas de Bet-el. 
        2 Y de Bet-el sale a Luz, y pasa a lo largo del territorio de los arquitas hasta Atarot, 
        3 y baja hacia el occidente al territorio de los jafletitas, hasta el límite de Bet-horón la de abajo, y hasta Gezer; y sale al mar.
        4 Recibieron, pues, su heredad los hijos de José, Manasés y Efraín.
        5 Y en cuanto al territorio de los hijos de Efraín por sus familias, el límite de su heredad al lado del oriente fue desde Atarot-adar hasta Bet-horón la de arriba. 
        6 Continúa el límite hasta el mar, y hasta Micmetat al norte, y da vuelta hacia el oriente hasta Taanat-silo, y de aquí pasa a Janoa. 
        7 De Janoa desciende a Atarot y a Naarat, y toca Jericó y sale al Jordán. 
        8 Y de Tapúa se vuelve hacia el mar, al arroyo de Caná, y sale al mar. Esta es la heredad de la tribu de los hijos de Efraín por sus familias. 
        9 Hubo también ciudades que se apartaron para los hijos de Efraín en medio de la heredad de los hijos de Manasés, todas ciudades con sus aldeas. 10 Pero no arrojaron al cananeo que habitaba en Gezer; antes quedó el cananeo en medio de Efraín, hasta hoy, y fue tributario.
        Josué 17
        1 Se echaron también suertes para la tribu de Manasés, porque fue primogénito de José. Maquir, primogénito de Manasés y padre de Galaad, el cual fue hombre de guerra, tuvo Galaad y Basán. 
        2 Se echaron también suertes para los otros hijos de Manasés conforme a sus familias: los hijos de Abiezer, los hijos de Helec, los hijos de Asriel, los hijos de Siquem, los hijos de Hefer y los hijos de Semida; estos fueron los hijos varones de Manasés hijo de José, por sus familias.
        3 Pero Zelofehad hijo de Hefer, hijo de Galaad, hijo de Maquir, hijo de Manasés, no tuvo hijos sino hijas, los nombres de las cuales son estos: Maala, Noa, Hogla, Milca y Tirsa. 
        4 Estas vinieron delante del sacerdote Eleazar y de Josué hijo de Nun, y de los príncipes, y dijeron: Jehová mandó a Moisés que nos diese heredad entre nuestros hermanos. Y él les dio heredad entre los hermanos del padre de ellas, conforme al dicho de Jehová. 
        5 Y le tocaron a Manasés diez partes además de la tierra de Galaad y de Basán que está al otro lado del Jordán, 
        6 porque las hijas de Manasés tuvieron heredad entre sus hijos; y la tierra de Galaad fue de los otros hijos de Manasés.
        7 Y fue el territorio de Manasés desde Aser hasta Micmetat, que está enfrente de Siquem; y va al sur, hasta los que habitan en Tapúa. 
        8 La tierra de Tapúa fue de Manasés; pero Tapúa misma, que está junto al límite de Manasés, es de los hijos de Efraín. 
        9 Desciende este límite al arroyo de Caná, hacia el sur del arroyo. Estas ciudades de Efraín están entre las ciudades de Manasés; y el límite de Manasés es desde el norte del mismo arroyo, y sus salidas son al mar. 
        10 Efraín al sur, y Manasés al norte, y el mar es su límite; y se encuentra con Aser al norte, y con Isacar al oriente. 
        11 Tuvo también Manasés en Isacar y en Aser a Bet-seán y sus aldeas, a Ibleam y sus aldeas, a los moradores de Dor y sus aldeas, a los moradores de Endor y sus aldeas, a los moradores de Taanac y sus aldeas, y a los moradores de Meguido y sus aldeas; tres provincias. 
        12 Mas los hijos de Manasés no pudieron arrojar a los de aquellas ciudades; y el cananeo persistió en habitar en aquella tierra. 
        13 Pero cuando los hijos de Israel fueron lo suficientemente fuertes, hicieron tributario al cananeo, mas no lo arrojaron.
        14 Y los hijos de José hablaron a Josué, diciendo: ¿Por qué nos has dado por heredad una sola suerte y una sola parte, siendo nosotros un pueblo tan grande, y que Jehová nos ha bendecido hasta ahora? 
        15 Y Josué les respondió: Si sois pueblo tan grande, subid al bosque, y haceos desmontes allí en la tierra de los ferezeos y de los refaítas, ya que el monte de Efraín es estrecho para vosotros. 
        16 Y los hijos de José dijeron: No nos bastará a nosotros este monte; y todos los cananeos que habitan la tierra de la llanura, tienen carros herrados; los que están en Bet-seán y en sus aldeas, y los que están en el valle de Jezreel. 
        17 Entonces Josué respondió a la casa de José, a Efraín y a Manasés, diciendo: Tú eres gran pueblo, y tienes grande poder; no tendrás una sola parte, 
        18 sino que aquel monte será tuyo; pues aunque es bosque, tú lo desmontarás y lo poseerás hasta sus límites más lejanos; porque tú arrojarás al cananeo, aunque tenga carros herrados, y aunque sea fuerte.
""".trimIndent(),
        "Job 28" to """
        El hombre en busca de la sabiduría
        1 Ciertamente la plata tiene sus veneros,
        Y el oro lugar donde se refina.
        2 El hierro se saca del polvo,
        Y de la piedra se funde el cobre.
        3 A las tinieblas ponen término,
        Y examinan todo a la perfección,
        Las piedras que hay en oscuridad y en sombra de muerte.
        4 Abren minas lejos de lo habitado,
        En lugares olvidados, donde el pie no pasa.
        Son suspendidos y balanceados, lejos de los demás hombres.
        5 De la tierra nace el pan,
        Y debajo de ella está como convertida en fuego.
        6 Lugar hay cuyas piedras son zafiro,
        Y sus polvos de oro.
        7 Senda que nunca la conoció ave,
        Ni ojo de buitre la vio;
        8 Nunca la pisaron animales fieros,
        Ni león pasó por ella.
        9 En el pedernal puso su mano,
        Y trastornó de raíz los montes.
        10 De los peñascos cortó ríos,
        Y sus ojos vieron todo lo preciado.
        11 Detuvo los ríos en su nacimiento,
        E hizo salir a luz lo escondido.
        12 Mas ¿dónde se hallará la sabiduría?
        ¿Dónde está el lugar de la inteligencia?
        13 No conoce su valor el hombre,
        Ni se halla en la tierra de los vivientes.
        14 El abismo dice: No está en mí;
        Y el mar dijo: Ni conmigo.
        15 No se dará por oro,
        Ni su precio será a peso de plata.
        16 No puede ser apreciada con oro de Ofir,
        Ni con ónice precioso, ni con zafiro.
        17 El oro no se le igualará, ni el diamante,
        Ni se cambiará por alhajas de oro fino.
        18 No se hará mención de coral ni de perlas;
        La sabiduría es mejor que las piedras preciosas.
        19 No se igualará con ella topacio de Etiopía;
        No se podrá apreciar con oro fino.
        20 ¿De dónde, pues, vendrá la sabiduría?
        ¿Y dónde está el lugar de la inteligencia?
        21 Porque encubierta está a los ojos de todo viviente,
        Y a toda ave del cielo es oculta.
        22 El Abadón y la muerte dijeron:
        Su fama hemos oído con nuestros oídos.
        23 Dios entiende el camino de ella,
        Y conoce su lugar.
        24 Porque él mira hasta los fines de la tierra,
        Y ve cuanto hay bajo los cielos.
        25 Al dar peso al viento,
        Y poner las aguas por medida;
        26 Cuando él dio ley a la lluvia,
        Y camino al relámpago de los truenos,
        27 Entonces la veía él, y la manifestaba;
        La preparó y la descubrió también.
        28 Y dijo al hombre:
        He aquí que el temor del Señor es la sabiduría,
        Y el apartarse del mal, la inteligencia.
""".trimIndent(),
        "Hechos 13:1-25" to """
        Bernabé y Saulo comienzan su primer viaje misionero
        1 Había entonces en la iglesia que estaba en Antioquía, profetas y maestros: Bernabé, Simón el que se llamaba Niger, Lucio de Cirene, Manaén el que se había criado junto con Herodes el tetrarca, y Saulo. 
        2 Ministrando estos al Señor, y ayunando, dijo el Espíritu Santo: Apartadme a Bernabé y a Saulo para la obra a que los he llamado. 
        3 Entonces, habiendo ayunado y orado, les impusieron las manos y los despidieron.
        Los apóstoles predican en Chipre
        4 Ellos, entonces, enviados por el Espíritu Santo, descendieron a Seleucia, y de allí navegaron a Chipre. 
        5 Y llegados a Salamina, anunciaban la palabra de Dios en las sinagogas de los judíos. Tenían también a Juan de ayudante. 
        6 Y habiendo atravesado toda la isla hasta Pafos, hallaron a cierto mago, falso profeta, judío, llamado Barjesús, 
        7 que estaba con el procónsul Sergio Paulo, varón prudente. Este, llamando a Bernabé y a Saulo, deseaba oír la palabra de Dios. 
        8 Pero les resistía Elimas, el mago (pues así se traduce su nombre), procurando apartar de la fe al procónsul. 
        9 Entonces Saulo, que también es Pablo, lleno del Espíritu Santo, fijando en él los ojos, 
        10 dijo: ¡Oh, lleno de todo engaño y de toda maldad, hijo del diablo, enemigo de toda justicia! ¿No cesarás de trastornar los caminos rectos del Señor? 
        11 Ahora, pues, he aquí la mano del Señor está contra ti, y serás ciego, y no verás el sol por algún tiempo. E inmediatamente cayeron sobre él oscuridad y tinieblas; y andando alrededor, buscaba quien le condujese de la mano. 
        12 Entonces el procónsul, viendo lo que había sucedido, creyó, maravillado de la doctrina del Señor.
        Pablo y Bernabé en Antioquía de Pisidia
        13 Habiendo zarpado de Pafos, Pablo y sus compañeros arribaron a Perge de Panfilia; pero Juan, apartándose de ellos, volvió a Jerusalén. 
        14 Ellos, pasando de Perge, llegaron a Antioquía de Pisidia; y entraron en la sinagoga un día de reposo[a] y se sentaron. 
        15 Y después de la lectura de la ley y de los profetas, los principales de la sinagoga mandaron a decirles: Varones hermanos, si tenéis alguna palabra de exhortación para el pueblo, hablad. 
        16 Entonces Pablo, levantándose, hecha señal de silencio con la mano, dijo:
        Varones israelitas, y los que teméis a Dios, oíd: 
        17 El Dios de este pueblo de Israel escogió a nuestros padres, y enalteció al pueblo, siendo ellos extranjeros en tierra de Egipto, y con brazo levantado los sacó de ella. 
        18 Y por un tiempo como de cuarenta años los soportó en el desierto; 
        19 y habiendo destruido siete naciones en la tierra de Canaán, les dio en herencia su territorio. 
        20 Después, como por cuatrocientos cincuenta años, les dio jueces hasta el profeta Samuel. 
        21 Luego pidieron rey, y Dios les dio a Saúl hijo de Cis, varón de la tribu de Benjamín, por cuarenta años. 
        22 Quitado este, les levantó por rey a David, de quien dio también testimonio diciendo: He hallado a David hijo de Isaí, varón conforme a mi corazón, quien hará todo lo que yo quiero. 
        23 De la descendencia de este, y conforme a la promesa, Dios levantó a Jesús por Salvador a Israel. 
        24 Antes de su venida, predicó Juan el bautismo de arrepentimiento a todo el pueblo de Israel. 
        25 Mas cuando Juan terminaba su carrera, dijo: ¿Quién pensáis que soy? No soy yo él; mas he aquí viene tras mí uno de quien no soy digno de desatar el calzado de los pies.
""".trimIndent(),
        "Josué 18-19" to """
        Josué 18
        Territorios de las demás tribus
        1 Toda la congregación de los hijos de Israel se reunió en Silo, y erigieron allí el tabernáculo de reunión, después que la tierra les fue sometida.
        2 Pero habían quedado de los hijos de Israel siete tribus a las cuales aún no habían repartido su posesión. 
        3 Y Josué dijo a los hijos de Israel: ¿Hasta cuándo seréis negligentes para venir a poseer la tierra que os ha dado Jehová el Dios de vuestros padres? 
        4 Señalad tres varones de cada tribu, para que yo los envíe, y que ellos se levanten y recorran la tierra, y la describan conforme a sus heredades, y vuelvan a mí. 
        5 Y la dividirán en siete partes; y Judá quedará en su territorio al sur, y los de la casa de José en el suyo al norte. 
        6 Vosotros, pues, delinearéis la tierra en siete partes, y me traeréis la descripción aquí, y yo os echaré suertes aquí delante de Jehová nuestro Dios. 
        7 Pero los levitas ninguna parte tienen entre vosotros, porque el sacerdocio de Jehová es la heredad de ellos; Gad también y Rubén, y la media tribu de Manasés, ya han recibido su heredad al otro lado del Jordán al oriente, la cual les dio Moisés siervo de Jehová.
        8 Levantándose, pues, aquellos varones, fueron; y mandó Josué a los que iban para delinear la tierra, diciéndoles: Id, recorred la tierra y delineadla, y volved a mí, para que yo os eche suertes aquí delante de Jehová en Silo. 
        9 Fueron, pues, aquellos varones y recorrieron la tierra, delineándola por ciudades en siete partes en un libro, y volvieron a Josué al campamento en Silo. 
        10 Y Josué les echó suertes delante de Jehová en Silo; y allí repartió Josué la tierra a los hijos de Israel por sus porciones.
        11 Y se sacó la suerte de la tribu de los hijos de Benjamín conforme a sus familias; y el territorio adjudicado a ella quedó entre los hijos de Judá y los hijos de José. 
        12 Fue el límite de ellos al lado del norte desde el Jordán, y sube hacia el lado de Jericó al norte; sube después al monte hacia el occidente, y viene a salir al desierto de Bet-avén. 
        13 De allí pasa en dirección de Luz, al lado sur de Luz (que es Bet-el), y desciende de Atarot-adar al monte que está al sur de Bet-horón la de abajo. 
        14 Y tuerce hacia el oeste por el lado sur del monte que está delante de Bet-horón al sur; y viene a salir a Quiriat-baal (que es Quiriat-jearim), ciudad de los hijos de Judá. Este es el lado del occidente. 
        15 El lado del sur es desde el extremo de Quiriat-jearim, y sale al occidente, a la fuente de las aguas de Neftoa; 
        16 y desciende este límite al extremo del monte que está delante del valle del hijo de Hinom, que está al norte en el valle de Refaim; desciende luego al valle de Hinom, al lado sur del jebuseo, y de allí desciende a la fuente de Rogel. 
        17 Luego se inclina hacia el norte y sale a En-semes, y de allí a Gelilot, que está delante de la subida de Adumín, y desciende a la piedra de Bohán hijo de Rubén, 
        18 y pasa al lado que está enfrente del Arabá, y desciende al Arabá. 
        19 Y pasa el límite al lado norte de Bet-hogla, y termina en la bahía norte del Mar Salado, a la extremidad sur del Jordán; este es el límite sur. 
        20 Y el Jordán era el límite al lado del oriente. Esta es la heredad de los hijos de Benjamín por sus límites alrededor, conforme a sus familias.
        21 Las ciudades de la tribu de los hijos de Benjamín, por sus familias, fueron Jericó, Bet-hogla, el valle de Casis, 
        22 Bet-arabá, Zemaraim, Bet-el, 
        23 Avim, Pará, Ofra, 
        24 Quefar-haamoni, Ofni y Geba; doce ciudades con sus aldeas; 
        25 Gabaón, Ramá, Beerot, 26 Mizpa, Cafira, Mozah, 
        27 Requem, Irpeel, Tarala, 
        28 Zela, Elef, Jebús (que es Jerusalén), Gabaa y Quiriat; catorce ciudades con sus aldeas. Esta es la heredad de los hijos de Benjamín conforme a sus familias.
        Josué 19
        1 La segunda suerte tocó a Simeón, para la tribu de los hijos de Simeón conforme a sus familias; y su heredad fue en medio de la heredad de los hijos de Judá. 
        2 Y tuvieron en su heredad a Beerseba, Seba, Molada, 3 Hazar-sual, Bala, Ezem, 
        4 Eltolad, Betul, Horma, 
        5 Siclag, Bet-marcabot, Hazar-susa, 
        6 Bet-lebaot y Saruhén; trece ciudades con sus aldeas; 
        7 Aín, Rimón, Eter y Asán; cuatro ciudades con sus aldeas; 
        8 y todas las aldeas que estaban alrededor de estas ciudades hasta Baalat-beer, que es Ramat del Neguev. Esta es la heredad de la tribu de los hijos de Simeón conforme a sus familias. 
        9 De la suerte de los hijos de Judá fue sacada la heredad de los hijos de Simeón, por cuanto la parte de los hijos de Judá era excesiva para ellos; así que los hijos de Simeón tuvieron su heredad en medio de la de Judá.
        10 La tercera suerte tocó a los hijos de Zabulón conforme a sus familias; y el territorio de su heredad fue hasta Sarid. 
        11 Y su límite sube hacia el occidente a Marala, y llega hasta Dabeset, y de allí hasta el arroyo que está delante de Jocneam; 
        12 y gira de Sarid hacia el oriente, hacia donde nace el sol, hasta el límite de Quislot-tabor, sale a Daberat, y sube a Jafía. 
        13 Pasando de allí hacia el lado oriental a Gat-hefer y a Ita-cazín, sale a Rimón rodeando a Nea. 
        14 Luego, al norte, el límite gira hacia Hanatón, viniendo a salir al valle de Jefte-el; 
        15 y abarca Catat, Naalal, Simrón, Idala y Belén; doce ciudades con sus aldeas. 
        16 Esta es la heredad de los hijos de Zabulón conforme a sus familias; estas ciudades con sus aldeas.
        17 La cuarta suerte correspondió a Isacar, a los hijos de Isacar conforme a sus familias. 
        18 Y fue su territorio Jezreel, Quesulot, Sunem, 
        19 Hafaraim, Sihón, Anaharat, 
        20 Rabit, Quisión, Abez, 
        21 Remet, En-ganim, En-hada y Bet-pases. 
        22 Y llega este límite hasta Tabor, Sahazima y Bet-semes, y termina en el Jordán; dieciséis ciudades con sus aldeas. 
        23 Esta es la heredad de la tribu de los hijos de Isacar conforme a sus familias; estas ciudades con sus aldeas.
        24 La quinta suerte correspondió a la tribu de los hijos de Aser conforme a sus familias. 
        25 Y su territorio abarcó Helcat, Halí, Betén, Acsaf, 
        26 Alamelec, Amad y Miseal; y llega hasta Carmelo al occidente, y a Sihorlibnat. 
        27 Después da vuelta hacia el oriente a Bet-dagón y llega a Zabulón, al valle de Jefte-el al norte, a Bet-emec y a Neiel, y sale a Cabul al norte. 
        28 Y abarca a Hebrón, Rehob, Hamón y Caná, hasta la gran Sidón. 
        29 De allí este límite tuerce hacia Ramá, y hasta la ciudad fortificada de Tiro, y gira hacia Hosa, y sale al mar desde el territorio de Aczib. 
        30 Abarca también Uma, Afec y Rehob; veintidós ciudades con sus aldeas. 
        31 Esta es la heredad de la tribu de los hijos de Aser conforme a sus familias; estas ciudades con sus aldeas.
        32 La sexta suerte correspondió a los hijos de Neftalí conforme a sus familias. 
        33 Y abarcó su territorio desde Helef, Alón-saananim, Adami-neceb y Jabneel, hasta Lacum, y sale al Jordán. 
        34 Y giraba el límite hacia el occidente a Aznot-tabor, y de allí pasaba a Hucoc, y llegaba hasta Zabulón al sur, y al occidente confinaba con Aser, y con Judá por el Jordán hacia donde nace el sol. 
        35 Y las ciudades fortificadas son Sidim, Zer, Hamat, Racat, Cineret, 
        36 Adama, Ramá, Hazor, 
        37 Cedes, Edrei, En-hazor, 
        38 Irón, Migdal-el, Horem, Bet-anat y Bet-semes; diecinueve ciudades con sus aldeas. 
        39 Esta es la heredad de la tribu de los hijos de Neftalí conforme a sus familias; estas ciudades con sus aldeas.
        40 La séptima suerte correspondió a la tribu de los hijos de Dan conforme a sus familias. 
        41 Y fue el territorio de su heredad, Zora, Estaol, Ir-semes, 
        42 Saalabín, Ajalón, Jetla, 
        43 Elón, Timnat, Ecrón, 
        44 Elteque, Gibetón, Baalat, 
        45 Jehúd, Bene-berac, Gat-rimón, 
        46 Mejarcón y Racón, con el territorio que está delante de Jope. 
        47 Y les faltó territorio a los hijos de Dan; y subieron los hijos de Dan y combatieron a Lesem, y tomándola la hirieron a filo de espada, y tomaron posesión de ella y habitaron en ella; y llamaron a Lesem, Dan, del nombre de Dan su padre. 
        48 Esta es la heredad de la tribu de los hijos de Dan conforme a sus familias; estas ciudades con sus aldeas.
        49 Y después que acabaron de repartir la tierra en heredad por sus territorios, dieron los hijos de Israel heredad a Josué hijo de Nun en medio de ellos; 
        50 según la palabra de Jehová, le dieron la ciudad que él pidió, Timnat-sera, en el monte de Efraín; y él reedificó la ciudad y habitó en ella.
        51 Estas son las heredades que el sacerdote Eleazar, y Josué hijo de Nun, y los cabezas de los padres, entregaron por suerte en posesión a las tribus de los hijos de Israel en Silo, delante de Jehová, a la entrada del tabernáculo de reunión; y acabaron de repartir la tierra.
""".trimIndent(),
        "Job 29" to """
        Job recuerda su felicidad anterior
        1 Volvió Job a reanudar su discurso, y dijo:
        2 ¡Quién me volviese como en los meses pasados,
        Como en los días en que Dios me guardaba,
        3 Cuando hacía resplandecer sobre mi cabeza su lámpara,
        A cuya luz yo caminaba en la oscuridad;
        4 Como fui en los días de mi juventud,
        Cuando el favor de Dios velaba sobre mi tienda;
        5 Cuando aún estaba conmigo el Omnipotente,
        Y mis hijos alrededor de mí;
        6 Cuando lavaba yo mis pasos con leche,
        Y la piedra me derramaba ríos de aceite!
        7 Cuando yo salía a la puerta a juicio,
        Y en la plaza hacía preparar mi asiento,
        8 Los jóvenes me veían, y se escondían;
        Y los ancianos se levantaban, y estaban de pie.
        9 Los príncipes detenían sus palabras;
        Ponían la mano sobre su boca.
        10 La voz de los principales se apagaba,
        Y su lengua se pegaba a su paladar.
        11 Los oídos que me oían me llamaban bienaventurado,
        Y los ojos que me veían me daban testimonio,
        12 Porque yo libraba al pobre que clamaba,
        Y al huérfano que carecía de ayudador.
        13 La bendición del que se iba a perder venía sobre mí,
        Y al corazón de la viuda yo daba alegría.
        14 Me vestía de justicia, y ella me cubría;
        Como manto y diadema era mi rectitud.
        15 Yo era ojos al ciego,
        Y pies al cojo.
        16 A los menesterosos era padre,
        Y de la causa que no entendía, me informaba con diligencia;
        17 Y quebrantaba los colmillos del inicuo,
        Y de sus dientes hacía soltar la presa.
        18 Decía yo: En mi nido moriré,
        Y como arena multiplicaré mis días.
        19 Mi raíz estaba abierta junto a las aguas,
        Y en mis ramas permanecía el rocío.
        20 Mi honra se renovaba en mí,
        Y mi arco se fortalecía en mi mano.
        21 Me oían, y esperaban,
        Y callaban a mi consejo.
        22 Tras mi palabra no replicaban,
        Y mi razón destilaba sobre ellos.
        23 Me esperaban como a la lluvia,
        Y abrían su boca como a la lluvia tardía.
        24 Si me reía con ellos, no lo creían;
        Y no abatían la luz de mi rostro.
        25 Calificaba yo el camino de ellos, y me sentaba entre ellos como el jefe;
        Y moraba como rey en el ejército,
        Como el que consuela a los que lloran.
""".trimIndent(),
        "Hechos 13:26-52" to """
        26 Varones hermanos, hijos del linaje de Abraham, y los que entre vosotros teméis a Dios, a vosotros es enviada la palabra de esta salvación. 
        27 Porque los habitantes de Jerusalén y sus gobernantes, no conociendo a Jesús, ni las palabras de los profetas que se leen todos los días de reposo,[a] las cumplieron al condenarle. 
        28 Y sin hallar en él causa digna de muerte, pidieron a Pilato que se le matase. 
        29 Y habiendo cumplido todas las cosas que de él estaban escritas, quitándolo del madero, lo pusieron en el sepulcro. 
        30 Mas Dios le levantó de los muertos. 
        31 Y él se apareció durante muchos días a los que habían subido juntamente con él de Galilea a Jerusalén, los cuales ahora son sus testigos ante el pueblo. 
        32 Y nosotros también os anunciamos el evangelio de aquella promesa hecha a nuestros padres, 
        33 la cual Dios ha cumplido a los hijos de ellos, a nosotros, resucitando a Jesús; como está escrito también en el salmo segundo: Mi hijo eres tú, yo te he engendrado hoy. 
        34 Y en cuanto a que le levantó de los muertos para nunca más volver a corrupción, lo dijo así: Os daré las misericordias fieles de David.
        35 Por eso dice también en otro salmo: No permitirás que tu Santo vea corrupción. 
        36 Porque a la verdad David, habiendo servido a su propia generación según la voluntad de Dios, durmió, y fue reunido con sus padres, y vio corrupción. 
        37 Mas aquel a quien Dios levantó, no vio corrupción. 
        38 Sabed, pues, esto, varones hermanos: que por medio de él se os anuncia perdón de pecados, 
        39 y que de todo aquello de que por la ley de Moisés no pudisteis ser justificados, en él es justificado todo aquel que cree. 
        40 Mirad, pues, que no venga sobre vosotros lo que está dicho en los profetas:
        41 Mirad, oh menospreciadores, y asombraos, y desapareced;
        Porque yo hago una obra en vuestros días,
        Obra que no creeréis, si alguien os la contare.
        42 Cuando salieron ellos de la sinagoga de los judíos, los gentiles les rogaron que el siguiente día de reposo[b] les hablasen de estas cosas. 
        43 Y despedida la congregación, muchos de los judíos y de los prosélitos piadosos siguieron a Pablo y a Bernabé, quienes hablándoles, les persuadían a que perseverasen en la gracia de Dios.
        44 El siguiente día de reposo[c] se juntó casi toda la ciudad para oír la palabra de Dios. 
        45 Pero viendo los judíos la muchedumbre, se llenaron de celos, y rebatían lo que Pablo decía, contradiciendo y blasfemando. 
        46 Entonces Pablo y Bernabé, hablando con denuedo, dijeron: A vosotros a la verdad era necesario que se os hablase primero la palabra de Dios; mas puesto que la desecháis, y no os juzgáis dignos de la vida eterna, he aquí, nos volvemos a los gentiles. 
        47 Porque así nos ha mandado el Señor, diciendo:
        Te he puesto para luz de los gentiles,
        A fin de que seas para salvación hasta lo último de la tierra.
        48 Los gentiles, oyendo esto, se regocijaban y glorificaban la palabra del Señor, y creyeron todos los que estaban ordenados para vida eterna. 
        49 Y la palabra del Señor se difundía por toda aquella provincia. 
        50 Pero los judíos instigaron a mujeres piadosas y distinguidas, y a los principales de la ciudad, y levantaron persecución contra Pablo y Bernabé, y los expulsaron de sus límites. 
        51 Ellos entonces, sacudiendo contra ellos el polvo de sus pies, llegaron a Iconio. 
        52 Y los discípulos estaban llenos de gozo y del Espíritu Santo.
""".trimIndent(),
        "Josué 20-21" to """
        Josué señala ciudades de refugio
        1 Habló Jehová a Josué, diciendo: 
        2 Habla a los hijos de Israel y diles: Señalaos las ciudades de refugio, de las cuales yo os hablé por medio de Moisés, 
        3 para que se acoja allí el homicida que matare a alguno por accidente y no a sabiendas; y os servirán de refugio contra el vengador de la sangre. 
        4 Y el que se acogiere a alguna de aquellas ciudades, se presentará a la puerta de la ciudad, y expondrá sus razones en oídos de los ancianos de aquella ciudad; y ellos le recibirán consigo dentro de la ciudad, y le darán lugar para que habite con ellos. 
        5 Si el vengador de la sangre le siguiere, no entregarán en su mano al homicida, por cuanto hirió a su prójimo por accidente, y no tuvo con él ninguna enemistad antes. 
        6 Y quedará en aquella ciudad hasta que comparezca en juicio delante de la congregación, y hasta la muerte del que fuere sumo sacerdote en aquel tiempo; entonces el homicida podrá volver a su ciudad y a su casa y a la ciudad de donde huyó.
        7 Entonces señalaron a Cedes en Galilea, en el monte de Neftalí, Siquem en el monte de Efraín, y Quiriat-arba (que es Hebrón) en el monte de Judá. 
        8 Y al otro lado del Jordán al oriente de Jericó, señalaron a Beser en el desierto, en la llanura de la tribu de Rubén, Ramot en Galaad de la tribu de Gad, y Golán en Basán de la tribu de Manasés. 
        9 Estas fueron las ciudades señaladas para todos los hijos de Israel, y para el extranjero que morase entre ellos, para que se acogiese a ellas cualquiera que hiriese a alguno por accidente, a fin de que no muriese por mano del vengador de la sangre, hasta que compareciese delante de la congregación.
        Josué 21
        Ciudades de los levitas
        1 Los jefes de los padres de los levitas vinieron al sacerdote Eleazar, a Josué hijo de Nun y a los cabezas de los padres de las tribus de los hijos de Israel, 
        2 y les hablaron en Silo en la tierra de Canaán, diciendo: Jehová mandó por medio de Moisés que nos fuesen dadas ciudades donde habitar, con sus ejidos para nuestros ganados. 
        3 Entonces los hijos de Israel dieron de su propia herencia a los levitas, conforme al mandato de Jehová, estas ciudades con sus ejidos.
        4 Y la suerte cayó sobre las familias de los coatitas; y los hijos de Aarón el sacerdote, que eran de los levitas, obtuvieron por suerte de la tribu de Judá, de la tribu de Simeón y de la tribu de Benjamín, trece ciudades.
        5 Y los otros hijos de Coat obtuvieron por suerte diez ciudades de las familias de la tribu de Efraín, de la tribu de Dan y de la media tribu de Manasés.
        6 Los hijos de Gersón obtuvieron por suerte, de las familias de la tribu de Isacar, de la tribu de Aser, de la tribu de Neftalí y de la media tribu de Manasés en Basán, trece ciudades.
        7 Los hijos de Merari según sus familias obtuvieron de la tribu de Rubén, de la tribu de Gad y de la tribu de Zabulón, doce ciudades.
        8 Dieron, pues, los hijos de Israel a los levitas estas ciudades con sus ejidos, por suertes, como había mandado Jehová por conducto de Moisés.
        9 De la tribu de los hijos de Judá, y de la tribu de los hijos de Simeón, dieron estas ciudades que fueron nombradas, 
        10 las cuales obtuvieron los hijos de Aarón de las familias de Coat, de los hijos de Leví; porque para ellos fue la suerte en primer lugar. 
        11 Les dieron Quiriat-arba del padre de Anac, la cual es Hebrón, en el monte de Judá, con sus ejidos en sus contornos. 
        12 Mas el campo de la ciudad y sus aldeas dieron a Caleb hijo de Jefone, por posesión suya.
        13 Y a los hijos del sacerdote Aarón dieron Hebrón con sus ejidos como ciudad de refugio para los homicidas; además, Libna con sus ejidos, 
        14 Jatir con sus ejidos, Estemoa con sus ejidos, 
        15 Holón con sus ejidos, Debir con sus ejidos, 
        16 Aín con sus ejidos, Juta con sus ejidos y Bet-semes con sus ejidos; nueve ciudades de estas dos tribus; 
        17 y de la tribu de Benjamín, Gabaón con sus ejidos, Geba con sus ejidos, 
        18 Anatot con sus ejidos, Almón con sus ejidos; cuatro ciudades. 
        19 Todas las ciudades de los sacerdotes hijos de Aarón son trece con sus ejidos.
        20 Mas las familias de los hijos de Coat, levitas, los que quedaban de los hijos de Coat, recibieron por suerte ciudades de la tribu de Efraín. 
        21 Les dieron Siquem con sus ejidos, en el monte de Efraín, como ciudad de refugio para los homicidas; además, Gezer con sus ejidos, 
        22 Kibsaim con sus ejidos y Bet-horón con sus ejidos; cuatro ciudades. 
        23 De la tribu de Dan, Elteque con sus ejidos, Gibetón con sus ejidos, 
        24 Ajalón con sus ejidos y Gat-rimón con sus ejidos; cuatro ciudades. 
        25 Y de la media tribu de Manasés, Taanac con sus ejidos y Gat-rimón con sus ejidos; dos ciudades. 
        26 Todas las ciudades para el resto de las familias de los hijos de Coat fueron diez con sus ejidos.
        27 A los hijos de Gersón de las familias de los levitas, dieron de la media tribu de Manasés a Golán en Basán con sus ejidos como ciudad de refugio para los homicidas, y además, Beestera con sus ejidos; dos ciudades. 
        28 De la tribu de Isacar, Cisón con sus ejidos, Daberat con sus ejidos, 
        29 Jarmut con sus ejidos y En-ganim con sus ejidos; cuatro ciudades. 
        30 De la tribu de Aser, Miseal con sus ejidos, Abdón con sus ejidos, 
        31 Helcat con sus ejidos y Rehob con sus ejidos; cuatro ciudades. 
        32 Y de la tribu de Neftalí, Cedes en Galilea con sus ejidos como ciudad de refugio para los homicidas, y además, Hamot-dor con sus ejidos y Cartán con sus ejidos; tres ciudades. 
        33 Todas las ciudades de los gersonitas por sus familias fueron trece ciudades con sus ejidos.
        34 Y a las familias de los hijos de Merari, levitas que quedaban, se les dio de la tribu de Zabulón, Jocneam con sus ejidos, Carta con sus ejidos, 
        35 Dimna con sus ejidos y Naalal con sus ejidos; cuatro ciudades. 
        36 Y de la tribu de Rubén, Beser con sus ejidos, Jahaza con sus ejidos, 
        37 Cademot con sus ejidos y Mefaat con sus ejidos; cuatro ciudades. 
        38 De la tribu de Gad, Ramot de Galaad con sus ejidos como ciudad de refugio para los homicidas; además, Mahanaim con sus ejidos, 
        39 Hesbón con sus ejidos y Jazer con sus ejidos; cuatro ciudades. 
        40 Todas las ciudades de los hijos de Merari por sus familias, que restaban de las familias de los levitas, fueron por sus suertes doce ciudades.
        41 Y todas las ciudades de los levitas en medio de la posesión de los hijos de Israel, fueron cuarenta y ocho ciudades con sus ejidos. 
        42 Y estas ciudades estaban apartadas la una de la otra, cada cual con sus ejidos alrededor de ella; así fue con todas estas ciudades.
        Israel ocupa la tierra
        43 De esta manera dio Jehová a Israel toda la tierra que había jurado dar a sus padres, y la poseyeron y habitaron en ella. 
        44 Y Jehová les dio reposo alrededor, conforme a todo lo que había jurado a sus padres; y ninguno de todos sus enemigos pudo hacerles frente, porque Jehová entregó en sus manos a todos sus enemigos. 
        45 No faltó palabra de todas las buenas promesas que Jehová había hecho a la casa de Israel; todo se cumplió.
""".trimIndent(),
        "Job 30" to """
        Job lamenta su desdicha actual
        1 Pero ahora se ríen de mí los más jóvenes que yo,
        A cuyos padres yo desdeñara poner con los perros de mi ganado.
        2 ¿Y de qué me serviría ni aun la fuerza de sus manos?
        No tienen fuerza alguna.
        3 Por causa de la pobreza y del hambre andaban solos;
        Huían a la soledad, a lugar tenebroso, asolado y desierto.
        4 Recogían malvas entre los arbustos,
        Y raíces de enebro para calentarse.
        5 Eran arrojados de entre las gentes,
        Y todos les daban grita como tras el ladrón.
        6 Habitaban en las barrancas de los arroyos,
        En las cavernas de la tierra, y en las rocas.
        7 Bramaban entre las matas,
        Y se reunían debajo de los espinos.
        8 Hijos de viles, y hombres sin nombre,
        Más bajos que la misma tierra.
        9 Y ahora yo soy objeto de su burla,
        Y les sirvo de refrán.
        10 Me abominan, se alejan de mí,
        Y aun de mi rostro no detuvieron su saliva.
        11 Porque Dios desató su cuerda, y me afligió,
        Por eso se desenfrenaron delante de mi rostro.
        12 A la mano derecha se levantó el populacho;
        Empujaron mis pies,
        Y prepararon contra mí caminos de perdición.
        13 Mi senda desbarataron,
        Se aprovecharon de mi quebrantamiento,
        Y contra ellos no hubo ayudador.
        14 Vinieron como por portillo ancho,
        Se revolvieron sobre mi calamidad.
        15 Se han revuelto turbaciones sobre mí;
        Combatieron como viento mi honor,
        Y mi prosperidad pasó como nube.
        16 Y ahora mi alma está derramada en mí;
        Días de aflicción se apoderan de mí.
        17 La noche taladra mis huesos,
        Y los dolores que me roen no reposan.
        18 La violencia deforma mi vestidura; me ciñe como el cuello de mi túnica.
        19 Él me derribó en el lodo,
        Y soy semejante al polvo y a la ceniza.
        20 Clamo a ti, y no me oyes;
        Me presento, y no me atiendes.
        21 Te has vuelto cruel para mí;
        Con el poder de tu mano me persigues.
        22 Me alzaste sobre el viento, me hiciste cabalgar en él,
        Y disolviste mi sustancia.
        23 Porque yo sé que me conduces a la muerte,
        Y a la casa determinada a todo viviente.
        24 Mas él no extenderá la mano contra el sepulcro;
        ¿Clamarán los sepultados cuando él los quebrantare?
        25 ¿No lloré yo al afligido?
        Y mi alma, ¿no se entristeció sobre el menesteroso?
        26 Cuando esperaba yo el bien, entonces vino el mal;
        Y cuando esperaba luz, vino la oscuridad.
        27 Mis entrañas se agitan, y no reposan;
        Días de aflicción me han sobrecogido.
        28 Ando ennegrecido, y no por el sol;
        Me he levantado en la congregación, y clamado.
        29 He venido a ser hermano de chacales,
        Y compañero de avestruces.
        30 Mi piel se ha ennegrecido y se me cae,
        Y mis huesos arden de calor.
        31 Se ha cambiado mi arpa en luto,
        Y mi flauta en voz de lamentadores.
""".trimIndent(),
    )

    val textoMostrar = textosMap[referencia] ?: "Texto no disponible para $referencia"
    val listState = rememberLazyListState()
    var lecturaMarcada by remember { mutableStateOf(false) }
    val lineas = textoMostrar.split("\n")

    // Restaurar scroll guardado
    LaunchedEffect(Unit) {
        val index = sharedPreferences.getInt("scroll_index_$referencia", 0)
        val offset = sharedPreferences.getInt("scroll_offset_$referencia", 0)
        listState.scrollToItem(index, offset)
    }

    // Guardar scroll en cada cambio
    LaunchedEffect(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset) {
        sharedPreferences.edit()
            .putInt("scroll_index_$referencia", listState.firstVisibleItemIndex)
            .putInt("scroll_offset_$referencia", listState.firstVisibleItemScrollOffset)
            .apply()
    }

    // Detectar si se llegó al final
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .map { it.lastOrNull()?.index }
            .distinctUntilChanged()
            .collectLatest { lastVisible ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisible == totalItems - 1 && !lecturaMarcada) {
                    lecturaMarcada = true
                    onCompletarLectura()
                }
            }
    }

    BackHandler(onBack = onVolver)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = referencia, style = MaterialTheme.typography.titleLarge)
                },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(lineas.size) { index ->
            Text(
                text = lineas[index],
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 22.sp,
                    lineHeight = 60.sp
                ),
                modifier = Modifier.padding(bottom = 75.dp)
            )
        }
    }

    }
}
