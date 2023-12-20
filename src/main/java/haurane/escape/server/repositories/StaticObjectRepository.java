package haurane.escape.server.repositories;

import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.models.StaticObject;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaticObjectRepository extends
        PagingAndSortingRepository<StaticObject, String>, CrudRepository<StaticObject, String> {
    List<StaticObject> findByName(@Param("name") String name);

    StaticObjectDTO findByUuid(String uuid);

    @Query("MATCH (R:Room{uuid:'1ce16bb7-a63a-4ca9-9f17-407635782e4e'}) -[contained:contains]-> (s:StaticObject)\n" +
            "Optional Match (s) -[attached *0..]-> (i:Item) \n" +
            "return s, collect(attached), collect(i)")
    List<StaticObjectDTO> findByContainingRoom(@Param("roomId") String roomId);


    @Query("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cum id quoque, ut cupiebat, audivisset, evelli iussit eam, qua erat transfixus, hastam. Sed potestne rerum maior esse dissensio? Duo Reges: constructio interrete. Sed haec omittamus; Similiter sensus, cum accessit ad naturam, tuetur illam quidem, sed etiam se tuetur; Cum autem in quo sapienter dicimus, id a primo rectissime dicitur. </p>\n" +
            "\n" +
            "<p>Potius inflammat, ut coercendi magis quam dedocendi esse videantur. Quis non odit sordidos, vanos, leves, futtiles? Sin laboramus, quis est, qui alienae modum statuat industriae? Quis suae urbis conservatorem Codrum, quis Erechthei filias non maxime laudat? Suo genere perveniant ad extremum; </p>\n" +
            "\n" +
            "<p>Non quaeritur autem quid naturae tuae consentaneum sit, sed quid disciplinae. An est aliquid, quod te sua sponte delectet? Illa videamus, quae a te de amicitia dicta sunt. Quando enim Socrates, qui parens philosophiae iure dici potest, quicquam tale fecit? Restincta enim sitis stabilitatem voluptatis habet, inquit, illa autem voluptas ipsius restinctionis in motu est. Qualem igitur hominem natura inchoavit? Hoc enim constituto in philosophia constituta sunt omnia. Indicant pueri, in quibus ut in speculis natura cernitur. </p>\n" +
            "\n" +
            "<p>An tu me de L. Hic ambiguo ludimur. Vitae autem degendae ratio maxime quidem illis placuit quieta. Virtutis, magnitudinis animi, patientiae, fortitudinis fomentis dolor mitigari solet. Quod eo liquidius faciet, si perspexerit rerum inter eas verborumne sit controversia. Quis non odit sordidos, vanos, leves, futtiles? </p>\n" +
            "\n" +
            "<p>Fortasse id optimum, sed ubi illud: Plus semper voluptatis? Quid enim me prohiberet Epicureum esse, si probarem, quae ille diceret? Qui non moveatur et offensione turpitudinis et comprobatione honestatis? </p>\n" +
            "\n" +
            "<p>Summus dolor plures dies manere non potest? Nemo igitur esse beatus potest. Sed ad haec, nisi molestum est, habeo quae velim. Illa sunt similia: hebes acies est cuipiam oculorum, corpore alius senescit; Causa autem fuit huc veniendi ut quosdam hinc libros promerem. Animum autem reliquis rebus ita perfecit, ut corpus; An quod ita callida est, ut optime possit architectari voluptates? Hic quoque suus est de summoque bono dissentiens dici vere Peripateticus non potest. </p>\n" +
            "\n" +
            "<p>Cur id non ita fit? Quae cum magnifice primo dici viderentur, considerata minus probabantur. Cum praesertim illa perdiscere ludus esset. Cum salvum esse flentes sui respondissent, rogavit essentne fusi hostes. Non est enim vitium in oratione solum, sed etiam in moribus. Ad corpus diceres pertinere-, sed ea, quae dixi, ad corpusne refers? </p>\n" +
            "\n" +
            "<p>Illud mihi a te nimium festinanter dictum videtur, sapientis omnis esse semper beatos; Ut aliquid scire se gaudeant? Fortasse id optimum, sed ubi illud: Plus semper voluptatis? Quamquam te quidem video minime esse deterritum. </p>\n" +
            "\n" +
            "<p>His singulis copiose responderi solet, sed quae perspicua sunt longa esse non debent. At modo dixeras nihil in istis rebus esse, quod interesset. In eo autem voluptas omnium Latine loquentium more ponitur, cum percipitur ea, quae sensum aliquem moveat, iucunditas. Gracchum patrem non beatiorem fuisse quam fillum, cum alter stabilire rem publicam studuerit, alter evertere. Si verbum sequimur, primum longius verbum praepositum quam bonum. Etsi qui potest intellegi aut cogitari esse aliquod animal, quod se oderit? Plane idem, inquit, et maxima quidem, qua fieri nulla maior potest. Hinc ceteri particulas arripere conati suam quisque videro voluit afferre sententiam. Potius ergo illa dicantur: turpe esse, viri non esse debilitari dolore, frangi, succumbere. </p>\n" +
            "\n" +
            "<p>Odium autem et invidiam facile vitabis. Quid nunc honeste dicit? Nam his libris eum malo quam reliquo ornatu villae delectari. Quo modo autem optimum, si bonum praeterea nullum est? Itaque nostrum est-quod nostrum dico, artis est-ad ea principia, quae accepimus. Vide ne ista sint Manliana vestra aut maiora etiam, si imperes quod facere non possim. Nec vero alia sunt quaerenda contra Carneadeam illam sententiam. Ego vero volo in virtute vim esse quam maximam; </p>\n" +
            "\n" +
            "<p>Quamquam in hac divisione rem ipsam prorsus probo, elegantiam desidero. Aliter homines, aliter philosophos loqui putas oportere? Hoc dixerit potius Ennius: Nimium boni est, cui nihil est mali. Negat enim summo bono afferre incrementum diem. Iam in altera philosophiae parte. Tubulo putas dicere? Ille enim occurrentia nescio quae comminiscebatur; Octavio fuit, cum illam severitatem in eo filio adhibuit, quem in adoptionem D. </p>\n" +
            "\n" +
            "<p>Primum cur ista res digna odio est, nisi quod est turpis? Illis videtur, qui illud non dubitant bonum dicere -; Animi enim quoque dolores percipiet omnibus partibus maiores quam corporis. Erat enim res aperta. Nondum autem explanatum satis, erat, quid maxime natura vellet. Summus dolor plures dies manere non potest? </p>\n" +
            "\n" +
            "<p>Cupiditates non Epicuri divisione finiebat, sed sua satietate. Istam voluptatem perpetuam quis potest praestare sapienti? Aliter enim explicari, quod quaeritur, non potest. Color egregius, integra valitudo, summa gratia, vita denique conferta voluptatum omnium varietate. Re mihi non aeque satisfacit, et quidem locis pluribus. Qui enim existimabit posse se miserum esse beatus non erit. </p>\n" +
            "\n" +
            "<p>Cur tantas regiones barbarorum pedibus obiit, tot maria transmisit? Hoc est vim afferre, Torquate, sensibus, extorquere ex animis cognitiones verborum, quibus inbuti sumus. Quasi ego id curem, quid ille aiat aut neget. Egone non intellego, quid sit don Graece, Latine voluptas? Quid enim de amicitia statueris utilitatis causa expetenda vides. Non enim, si omnia non sequebatur, idcirco non erat ortus illinc. Nec tamen ullo modo summum pecudis bonum et hominis idem mihi videri potest. Quae cum praeponunt, ut sit aliqua rerum selectio, naturam videntur sequi; Sin te auctoritas commovebat, nobisne omnibus et Platoni ipsi nescio quem illum anteponebas? Nemo nostrum istius generis asotos iucunde putat vivere. Rapior illuc, revocat autem Antiochus, nec est praeterea, quem audiamus. Eaedem enim utilitates poterunt eas labefactare atque pervertere. </p>\n" +
            "\n" +
            "<p>Atque haec ita iustitiae propria sunt, ut sint virtutum reliquarum communia. Egone quaeris, inquit, quid sentiam? Ad eas enim res ab Epicuro praecepta dantur. Ergo ita: non posse honeste vivi, nisi honeste vivatur? Scio enim esse quosdam, qui quavis lingua philosophari possint; Zenonis est, inquam, hoc Stoici. </p>\n" +
            "\n" +
            "<p>Quae duo sunt, unum facit. Si longus, levis; Nam quibus rebus efficiuntur voluptates, eae non sunt in potestate sapientis. Nam quid possumus facere melius? </p>\n" +
            "\n" +
            "<p>Tum Piso: Quoniam igitur aliquid omnes, quid Lucius noster? Sin laboramus, quis est, qui alienae modum statuat industriae? Tum Triarius: Posthac quidem, inquit, audacius. An est aliquid, quod te sua sponte delectet? Traditur, inquit, ab Epicuro ratio neglegendi doloris. Quid est, quod ab ea absolvi et perfici debeat? Qui autem diffidet perpetuitati bonorum suorum, timeat necesse est, ne aliquando amissis illis sit miser. Et quod est munus, quod opus sapientiae? Piso igitur hoc modo, vir optimus tuique, ut scis, amantissimus. </p>\n" +
            "\n" +
            "<p>Eadem nunc mea adversum te oratio est. Quonam, inquit, modo? Pisone in eo gymnasio, quod Ptolomaeum vocatur, unaque nobiscum Q. A mene tu? Maximas vero virtutes iacere omnis necesse est voluptate dominante. Deinde qui fit, ut ego nesciam, sciant omnes, quicumque Epicurei esse voluerunt? Levatio igitur vitiorum magna fit in iis, qui habent ad virtutem progressionis aliquantum. Quem Tiberina descensio festo illo die tanto gaudio affecit, quanto L. Primum quid tu dicis breve? Nec lapathi suavitatem acupenseri Galloni Laelius anteponebat, sed suavitatem ipsam neglegebat; Propter nos enim illam, non propter eam nosmet ipsos diligimus. Tollitur beneficium, tollitur gratia, quae sunt vincla concordiae. </p>\n" +
            "\n" +
            "<p>Sed tamen omne, quod de re bona dilucide dicitur, mihi praeclare dici videtur. Pollicetur certe. Huius ego nunc auctoritatem sequens idem faciam. Et ille ridens: Video, inquit, quid agas; Atqui reperies, inquit, in hoc quidem pertinacem; Multa sunt dicta ab antiquis de contemnendis ac despiciendis rebus humanis; Portenta haec esse dicit, neque ea ratione ullo modo posse vivi; </p>\n" +
            "\n" +
            "<p>Sed quid ages tandem, si utilitas ab amicitia, ut fit saepe, defecerit? At ille pellit, qui permulcet sensum voluptate. Cum autem in quo sapienter dicimus, id a primo rectissime dicitur. Bork </p>")
}
