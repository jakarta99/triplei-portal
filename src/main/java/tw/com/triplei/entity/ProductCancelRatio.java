package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tw.com.triplei.commons.GenericEntity;

/**
 * 查表: 解約金費率
 */
@Entity
@Table(name = "PRODUCT_CANCEL_RATIO")
public class ProductCancelRatio extends GenericEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "INS_AGE")
	private Integer insAge;
	
	@Column(name = "GENDER")
	private String gender;
	

	@Column(name = "CANCEL_RATIO_0")
	private BigDecimal cancelRatio_0;  // 第0年解約金費率
	@Column(name = "CANCEL_RATIO_1")
	private BigDecimal cancelRatio_1;  // 第1年解約金費率
	@Column(name = "CANCEL_RATIO_2")
	private BigDecimal cancelRatio_2;  // 第2年解約金費率
	@Column(name = "CANCEL_RATIO_3")
	private BigDecimal cancelRatio_3;  // 第3年解約金費率
	@Column(name = "CANCEL_RATIO_4")
	private BigDecimal cancelRatio_4;  // 第4年解約金費率
	@Column(name = "CANCEL_RATIO_5")
	private BigDecimal cancelRatio_5;  // 第5年解約金費率
	@Column(name = "CANCEL_RATIO_6")
	private BigDecimal cancelRatio_6;  // 第6年解約金費率
	@Column(name = "CANCEL_RATIO_7")
	private BigDecimal cancelRatio_7;  // 第7年解約金費率
	@Column(name = "CANCEL_RATIO_8")
	private BigDecimal cancelRatio_8;  // 第8年解約金費率
	@Column(name = "CANCEL_RATIO_9")
	private BigDecimal cancelRatio_9;  // 第9年解約金費率
	@Column(name = "CANCEL_RATIO_10")
	private BigDecimal cancelRatio_10;  // 第10年解約金費率
	@Column(name = "CANCEL_RATIO_11")
	private BigDecimal cancelRatio_11;  // 第11年解約金費率
	@Column(name = "CANCEL_RATIO_12")
	private BigDecimal cancelRatio_12;  // 第12年解約金費率
	@Column(name = "CANCEL_RATIO_13")
	private BigDecimal cancelRatio_13;  // 第13年解約金費率
	@Column(name = "CANCEL_RATIO_14")
	private BigDecimal cancelRatio_14;  // 第14年解約金費率
	@Column(name = "CANCEL_RATIO_15")
	private BigDecimal cancelRatio_15;  // 第15年解約金費率
	@Column(name = "CANCEL_RATIO_16")
	private BigDecimal cancelRatio_16;  // 第16年解約金費率
	@Column(name = "CANCEL_RATIO_17")
	private BigDecimal cancelRatio_17;  // 第17年解約金費率
	@Column(name = "CANCEL_RATIO_18")
	private BigDecimal cancelRatio_18;  // 第18年解約金費率
	@Column(name = "CANCEL_RATIO_19")
	private BigDecimal cancelRatio_19;  // 第19年解約金費率
	@Column(name = "CANCEL_RATIO_20")
	private BigDecimal cancelRatio_20;  // 第20年解約金費率
	@Column(name = "CANCEL_RATIO_21")
	private BigDecimal cancelRatio_21;  // 第21年解約金費率
	@Column(name = "CANCEL_RATIO_22")
	private BigDecimal cancelRatio_22;  // 第22年解約金費率
	@Column(name = "CANCEL_RATIO_23")
	private BigDecimal cancelRatio_23;  // 第23年解約金費率
	@Column(name = "CANCEL_RATIO_24")
	private BigDecimal cancelRatio_24;  // 第24年解約金費率
	@Column(name = "CANCEL_RATIO_25")
	private BigDecimal cancelRatio_25;  // 第25年解約金費率
	@Column(name = "CANCEL_RATIO_26")
	private BigDecimal cancelRatio_26;  // 第26年解約金費率
	@Column(name = "CANCEL_RATIO_27")
	private BigDecimal cancelRatio_27;  // 第27年解約金費率
	@Column(name = "CANCEL_RATIO_28")
	private BigDecimal cancelRatio_28;  // 第28年解約金費率
	@Column(name = "CANCEL_RATIO_29")
	private BigDecimal cancelRatio_29;  // 第29年解約金費率
	@Column(name = "CANCEL_RATIO_30")
	private BigDecimal cancelRatio_30;  // 第30年解約金費率
	@Column(name = "CANCEL_RATIO_31")
	private BigDecimal cancelRatio_31;  // 第31年解約金費率
	@Column(name = "CANCEL_RATIO_32")
	private BigDecimal cancelRatio_32;  // 第32年解約金費率
	@Column(name = "CANCEL_RATIO_33")
	private BigDecimal cancelRatio_33;  // 第33年解約金費率
	@Column(name = "CANCEL_RATIO_34")
	private BigDecimal cancelRatio_34;  // 第34年解約金費率
	@Column(name = "CANCEL_RATIO_35")
	private BigDecimal cancelRatio_35;  // 第35年解約金費率
	@Column(name = "CANCEL_RATIO_36")
	private BigDecimal cancelRatio_36;  // 第36年解約金費率
	@Column(name = "CANCEL_RATIO_37")
	private BigDecimal cancelRatio_37;  // 第37年解約金費率
	@Column(name = "CANCEL_RATIO_38")
	private BigDecimal cancelRatio_38;  // 第38年解約金費率
	@Column(name = "CANCEL_RATIO_39")
	private BigDecimal cancelRatio_39;  // 第39年解約金費率
	@Column(name = "CANCEL_RATIO_40")
	private BigDecimal cancelRatio_40;  // 第40年解約金費率
	@Column(name = "CANCEL_RATIO_41")
	private BigDecimal cancelRatio_41;  // 第41年解約金費率
	@Column(name = "CANCEL_RATIO_42")
	private BigDecimal cancelRatio_42;  // 第42年解約金費率
	@Column(name = "CANCEL_RATIO_43")
	private BigDecimal cancelRatio_43;  // 第43年解約金費率
	@Column(name = "CANCEL_RATIO_44")
	private BigDecimal cancelRatio_44;  // 第44年解約金費率
	@Column(name = "CANCEL_RATIO_45")
	private BigDecimal cancelRatio_45;  // 第45年解約金費率
	@Column(name = "CANCEL_RATIO_46")
	private BigDecimal cancelRatio_46;  // 第46年解約金費率
	@Column(name = "CANCEL_RATIO_47")
	private BigDecimal cancelRatio_47;  // 第47年解約金費率
	@Column(name = "CANCEL_RATIO_48")
	private BigDecimal cancelRatio_48;  // 第48年解約金費率
	@Column(name = "CANCEL_RATIO_49")
	private BigDecimal cancelRatio_49;  // 第49年解約金費率
	@Column(name = "CANCEL_RATIO_50")
	private BigDecimal cancelRatio_50;  // 第50年解約金費率
	@Column(name = "CANCEL_RATIO_51")
	private BigDecimal cancelRatio_51;  // 第51年解約金費率
	@Column(name = "CANCEL_RATIO_52")
	private BigDecimal cancelRatio_52;  // 第52年解約金費率
	@Column(name = "CANCEL_RATIO_53")
	private BigDecimal cancelRatio_53;  // 第53年解約金費率
	@Column(name = "CANCEL_RATIO_54")
	private BigDecimal cancelRatio_54;  // 第54年解約金費率
	@Column(name = "CANCEL_RATIO_55")
	private BigDecimal cancelRatio_55;  // 第55年解約金費率
	@Column(name = "CANCEL_RATIO_56")
	private BigDecimal cancelRatio_56;  // 第56年解約金費率
	@Column(name = "CANCEL_RATIO_57")
	private BigDecimal cancelRatio_57;  // 第57年解約金費率
	@Column(name = "CANCEL_RATIO_58")
	private BigDecimal cancelRatio_58;  // 第58年解約金費率
	@Column(name = "CANCEL_RATIO_59")
	private BigDecimal cancelRatio_59;  // 第59年解約金費率
	@Column(name = "CANCEL_RATIO_60")
	private BigDecimal cancelRatio_60;  // 第60年解約金費率
	@Column(name = "CANCEL_RATIO_61")
	private BigDecimal cancelRatio_61;  // 第61年解約金費率
	@Column(name = "CANCEL_RATIO_62")
	private BigDecimal cancelRatio_62;  // 第62年解約金費率
	@Column(name = "CANCEL_RATIO_63")
	private BigDecimal cancelRatio_63;  // 第63年解約金費率
	@Column(name = "CANCEL_RATIO_64")
	private BigDecimal cancelRatio_64;  // 第64年解約金費率
	@Column(name = "CANCEL_RATIO_65")
	private BigDecimal cancelRatio_65;  // 第65年解約金費率
	@Column(name = "CANCEL_RATIO_66")
	private BigDecimal cancelRatio_66;  // 第66年解約金費率
	@Column(name = "CANCEL_RATIO_67")
	private BigDecimal cancelRatio_67;  // 第67年解約金費率
	@Column(name = "CANCEL_RATIO_68")
	private BigDecimal cancelRatio_68;  // 第68年解約金費率
	@Column(name = "CANCEL_RATIO_69")
	private BigDecimal cancelRatio_69;  // 第69年解約金費率
	@Column(name = "CANCEL_RATIO_70")
	private BigDecimal cancelRatio_70;  // 第70年解約金費率
	@Column(name = "CANCEL_RATIO_71")
	private BigDecimal cancelRatio_71;  // 第71年解約金費率
	@Column(name = "CANCEL_RATIO_72")
	private BigDecimal cancelRatio_72;  // 第72年解約金費率
	@Column(name = "CANCEL_RATIO_73")
	private BigDecimal cancelRatio_73;  // 第73年解約金費率
	@Column(name = "CANCEL_RATIO_74")
	private BigDecimal cancelRatio_74;  // 第74年解約金費率
	@Column(name = "CANCEL_RATIO_75")
	private BigDecimal cancelRatio_75;  // 第75年解約金費率
	@Column(name = "CANCEL_RATIO_76")
	private BigDecimal cancelRatio_76;  // 第76年解約金費率
	@Column(name = "CANCEL_RATIO_77")
	private BigDecimal cancelRatio_77;  // 第77年解約金費率
	@Column(name = "CANCEL_RATIO_78")
	private BigDecimal cancelRatio_78;  // 第78年解約金費率
	@Column(name = "CANCEL_RATIO_79")
	private BigDecimal cancelRatio_79;  // 第79年解約金費率
	@Column(name = "CANCEL_RATIO_80")
	private BigDecimal cancelRatio_80;  // 第80年解約金費率
	@Column(name = "CANCEL_RATIO_81")
	private BigDecimal cancelRatio_81;  // 第81年解約金費率
	@Column(name = "CANCEL_RATIO_82")
	private BigDecimal cancelRatio_82;  // 第82年解約金費率
	@Column(name = "CANCEL_RATIO_83")
	private BigDecimal cancelRatio_83;  // 第83年解約金費率
	@Column(name = "CANCEL_RATIO_84")
	private BigDecimal cancelRatio_84;  // 第84年解約金費率
	@Column(name = "CANCEL_RATIO_85")
	private BigDecimal cancelRatio_85;  // 第85年解約金費率
	@Column(name = "CANCEL_RATIO_86")
	private BigDecimal cancelRatio_86;  // 第86年解約金費率
	@Column(name = "CANCEL_RATIO_87")
	private BigDecimal cancelRatio_87;  // 第87年解約金費率
	@Column(name = "CANCEL_RATIO_88")
	private BigDecimal cancelRatio_88;  // 第88年解約金費率
	@Column(name = "CANCEL_RATIO_89")
	private BigDecimal cancelRatio_89;  // 第89年解約金費率
	@Column(name = "CANCEL_RATIO_90")
	private BigDecimal cancelRatio_90;  // 第90年解約金費率
	@Column(name = "CANCEL_RATIO_91")
	private BigDecimal cancelRatio_91;  // 第91年解約金費率
	@Column(name = "CANCEL_RATIO_92")
	private BigDecimal cancelRatio_92;  // 第92年解約金費率
	@Column(name = "CANCEL_RATIO_93")
	private BigDecimal cancelRatio_93;  // 第93年解約金費率
	@Column(name = "CANCEL_RATIO_94")
	private BigDecimal cancelRatio_94;  // 第94年解約金費率
	@Column(name = "CANCEL_RATIO_95")
	private BigDecimal cancelRatio_95;  // 第95年解約金費率
	@Column(name = "CANCEL_RATIO_96")
	private BigDecimal cancelRatio_96;  // 第96年解約金費率
	@Column(name = "CANCEL_RATIO_97")
	private BigDecimal cancelRatio_97;  // 第97年解約金費率
	@Column(name = "CANCEL_RATIO_98")
	private BigDecimal cancelRatio_98;  // 第98年解約金費率
	@Column(name = "CANCEL_RATIO_99")
	private BigDecimal cancelRatio_99;  // 第99年解約金費率
	@Column(name = "CANCEL_RATIO_100")
	private BigDecimal cancelRatio_100;  // 第100年解約金費率
	@Column(name = "CANCEL_RATIO_101")
	private BigDecimal cancelRatio_101;  // 第101年解約金費率
	@Column(name = "CANCEL_RATIO_102")
	private BigDecimal cancelRatio_102;  // 第102年解約金費率
	@Column(name = "CANCEL_RATIO_103")
	private BigDecimal cancelRatio_103;  // 第103年解約金費率
	@Column(name = "CANCEL_RATIO_104")
	private BigDecimal cancelRatio_104;  // 第104年解約金費率
	@Column(name = "CANCEL_RATIO_105")
	private BigDecimal cancelRatio_105;  // 第105年解約金費率
	@Column(name = "CANCEL_RATIO_106")
	private BigDecimal cancelRatio_106;  // 第106年解約金費率
	@Column(name = "CANCEL_RATIO_107")
	private BigDecimal cancelRatio_107;  // 第107年解約金費率
	@Column(name = "CANCEL_RATIO_108")
	private BigDecimal cancelRatio_108;  // 第108年解約金費率
	@Column(name = "CANCEL_RATIO_109")
	private BigDecimal cancelRatio_109;  // 第109年解約金費率
	@Column(name = "CANCEL_RATIO_110")
	private BigDecimal cancelRatio_110;  // 第110年解約金費率
	@Column(name = "CANCEL_RATIO_111")
	private BigDecimal cancelRatio_111;  // 第111年解約金費率


	@Override
	public String toString() {
		return "ProductCancelRatio [id=" + id + ", productId=" + productId + ", insAge=" + insAge + ", gender=" + gender
				+ ", cancelRatio_0=" + cancelRatio_0 + ", cancelRatio_1=" + cancelRatio_1 + ", cancelRatio_2="
				+ cancelRatio_2 + ", cancelRatio_3=" + cancelRatio_3 + ", cancelRatio_4=" + cancelRatio_4
				+ ", cancelRatio_5=" + cancelRatio_5 + ", cancelRatio_6=" + cancelRatio_6 + ", cancelRatio_7="
				+ cancelRatio_7 + ", cancelRatio_8=" + cancelRatio_8 + ", cancelRatio_9=" + cancelRatio_9
				+ ", cancelRatio_10=" + cancelRatio_10 + ", cancelRatio_11=" + cancelRatio_11 + ", cancelRatio_12="
				+ cancelRatio_12 + ", cancelRatio_13=" + cancelRatio_13 + ", cancelRatio_14=" + cancelRatio_14
				+ ", cancelRatio_15=" + cancelRatio_15 + ", cancelRatio_16=" + cancelRatio_16 + ", cancelRatio_17="
				+ cancelRatio_17 + ", cancelRatio_18=" + cancelRatio_18 + ", cancelRatio_19=" + cancelRatio_19
				+ ", cancelRatio_20=" + cancelRatio_20 + ", cancelRatio_21=" + cancelRatio_21 + ", cancelRatio_22="
				+ cancelRatio_22 + ", cancelRatio_23=" + cancelRatio_23 + ", cancelRatio_24=" + cancelRatio_24
				+ ", cancelRatio_25=" + cancelRatio_25 + ", cancelRatio_26=" + cancelRatio_26 + ", cancelRatio_27="
				+ cancelRatio_27 + ", cancelRatio_28=" + cancelRatio_28 + ", cancelRatio_29=" + cancelRatio_29
				+ ", cancelRatio_30=" + cancelRatio_30 + ", cancelRatio_31=" + cancelRatio_31 + ", cancelRatio_32="
				+ cancelRatio_32 + ", cancelRatio_33=" + cancelRatio_33 + ", cancelRatio_34=" + cancelRatio_34
				+ ", cancelRatio_35=" + cancelRatio_35 + ", cancelRatio_36=" + cancelRatio_36 + ", cancelRatio_37="
				+ cancelRatio_37 + ", cancelRatio_38=" + cancelRatio_38 + ", cancelRatio_39=" + cancelRatio_39
				+ ", cancelRatio_40=" + cancelRatio_40 + ", cancelRatio_41=" + cancelRatio_41 + ", cancelRatio_42="
				+ cancelRatio_42 + ", cancelRatio_43=" + cancelRatio_43 + ", cancelRatio_44=" + cancelRatio_44
				+ ", cancelRatio_45=" + cancelRatio_45 + ", cancelRatio_46=" + cancelRatio_46 + ", cancelRatio_47="
				+ cancelRatio_47 + ", cancelRatio_48=" + cancelRatio_48 + ", cancelRatio_49=" + cancelRatio_49
				+ ", cancelRatio_50=" + cancelRatio_50 + ", cancelRatio_51=" + cancelRatio_51 + ", cancelRatio_52="
				+ cancelRatio_52 + ", cancelRatio_53=" + cancelRatio_53 + ", cancelRatio_54=" + cancelRatio_54
				+ ", cancelRatio_55=" + cancelRatio_55 + ", cancelRatio_56=" + cancelRatio_56 + ", cancelRatio_57="
				+ cancelRatio_57 + ", cancelRatio_58=" + cancelRatio_58 + ", cancelRatio_59=" + cancelRatio_59
				+ ", cancelRatio_60=" + cancelRatio_60 + ", cancelRatio_61=" + cancelRatio_61 + ", cancelRatio_62="
				+ cancelRatio_62 + ", cancelRatio_63=" + cancelRatio_63 + ", cancelRatio_64=" + cancelRatio_64
				+ ", cancelRatio_65=" + cancelRatio_65 + ", cancelRatio_66=" + cancelRatio_66 + ", cancelRatio_67="
				+ cancelRatio_67 + ", cancelRatio_68=" + cancelRatio_68 + ", cancelRatio_69=" + cancelRatio_69
				+ ", cancelRatio_70=" + cancelRatio_70 + ", cancelRatio_71=" + cancelRatio_71 + ", cancelRatio_72="
				+ cancelRatio_72 + ", cancelRatio_73=" + cancelRatio_73 + ", cancelRatio_74=" + cancelRatio_74
				+ ", cancelRatio_75=" + cancelRatio_75 + ", cancelRatio_76=" + cancelRatio_76 + ", cancelRatio_77="
				+ cancelRatio_77 + ", cancelRatio_78=" + cancelRatio_78 + ", cancelRatio_79=" + cancelRatio_79
				+ ", cancelRatio_80=" + cancelRatio_80 + ", cancelRatio_81=" + cancelRatio_81 + ", cancelRatio_82="
				+ cancelRatio_82 + ", cancelRatio_83=" + cancelRatio_83 + ", cancelRatio_84=" + cancelRatio_84
				+ ", cancelRatio_85=" + cancelRatio_85 + ", cancelRatio_86=" + cancelRatio_86 + ", cancelRatio_87="
				+ cancelRatio_87 + ", cancelRatio_88=" + cancelRatio_88 + ", cancelRatio_89=" + cancelRatio_89
				+ ", cancelRatio_90=" + cancelRatio_90 + ", cancelRatio_91=" + cancelRatio_91 + ", cancelRatio_92="
				+ cancelRatio_92 + ", cancelRatio_93=" + cancelRatio_93 + ", cancelRatio_94=" + cancelRatio_94
				+ ", cancelRatio_95=" + cancelRatio_95 + ", cancelRatio_96=" + cancelRatio_96 + ", cancelRatio_97="
				+ cancelRatio_97 + ", cancelRatio_98=" + cancelRatio_98 + ", cancelRatio_99=" + cancelRatio_99
				+ ", cancelRatio_100=" + cancelRatio_100 + ", cancelRatio_101=" + cancelRatio_101 + ", cancelRatio_102="
				+ cancelRatio_102 + ", cancelRatio_103=" + cancelRatio_103 + ", cancelRatio_104=" + cancelRatio_104
				+ ", cancelRatio_105=" + cancelRatio_105 + ", cancelRatio_106=" + cancelRatio_106 + ", cancelRatio_107="
				+ cancelRatio_107 + ", cancelRatio_108=" + cancelRatio_108 + ", cancelRatio_109=" + cancelRatio_109
				+ ", cancelRatio_110=" + cancelRatio_110 + ", cancelRatio_111=" + cancelRatio_111 + ", getId()="
				+ getId() + ", getProductId()=" + getProductId() + ", getInsAge()=" + getInsAge() + ", getGender()="
				+ getGender() + ", getCancelRatio_0()=" + getCancelRatio_0() + ", getCancelRatio_1()="
				+ getCancelRatio_1() + ", getCancelRatio_2()=" + getCancelRatio_2() + ", getCancelRatio_3()="
				+ getCancelRatio_3() + ", getCancelRatio_4()=" + getCancelRatio_4() + ", getCancelRatio_5()="
				+ getCancelRatio_5() + ", getCancelRatio_6()=" + getCancelRatio_6() + ", getCancelRatio_7()="
				+ getCancelRatio_7() + ", getCancelRatio_8()=" + getCancelRatio_8() + ", getCancelRatio_9()="
				+ getCancelRatio_9() + ", getCancelRatio_10()=" + getCancelRatio_10() + ", getCancelRatio_11()="
				+ getCancelRatio_11() + ", getCancelRatio_12()=" + getCancelRatio_12() + ", getCancelRatio_13()="
				+ getCancelRatio_13() + ", getCancelRatio_14()=" + getCancelRatio_14() + ", getCancelRatio_15()="
				+ getCancelRatio_15() + ", getCancelRatio_16()=" + getCancelRatio_16() + ", getCancelRatio_17()="
				+ getCancelRatio_17() + ", getCancelRatio_18()=" + getCancelRatio_18() + ", getCancelRatio_19()="
				+ getCancelRatio_19() + ", getCancelRatio_20()=" + getCancelRatio_20() + ", getCancelRatio_21()="
				+ getCancelRatio_21() + ", getCancelRatio_22()=" + getCancelRatio_22() + ", getCancelRatio_23()="
				+ getCancelRatio_23() + ", getCancelRatio_24()=" + getCancelRatio_24() + ", getCancelRatio_25()="
				+ getCancelRatio_25() + ", getCancelRatio_26()=" + getCancelRatio_26() + ", getCancelRatio_27()="
				+ getCancelRatio_27() + ", getCancelRatio_28()=" + getCancelRatio_28() + ", getCancelRatio_29()="
				+ getCancelRatio_29() + ", getCancelRatio_30()=" + getCancelRatio_30() + ", getCancelRatio_31()="
				+ getCancelRatio_31() + ", getCancelRatio_32()=" + getCancelRatio_32() + ", getCancelRatio_33()="
				+ getCancelRatio_33() + ", getCancelRatio_34()=" + getCancelRatio_34() + ", getCancelRatio_35()="
				+ getCancelRatio_35() + ", getCancelRatio_36()=" + getCancelRatio_36() + ", getCancelRatio_37()="
				+ getCancelRatio_37() + ", getCancelRatio_38()=" + getCancelRatio_38() + ", getCancelRatio_39()="
				+ getCancelRatio_39() + ", getCancelRatio_40()=" + getCancelRatio_40() + ", getCancelRatio_41()="
				+ getCancelRatio_41() + ", getCancelRatio_42()=" + getCancelRatio_42() + ", getCancelRatio_43()="
				+ getCancelRatio_43() + ", getCancelRatio_44()=" + getCancelRatio_44() + ", getCancelRatio_45()="
				+ getCancelRatio_45() + ", getCancelRatio_46()=" + getCancelRatio_46() + ", getCancelRatio_47()="
				+ getCancelRatio_47() + ", getCancelRatio_48()=" + getCancelRatio_48() + ", getCancelRatio_49()="
				+ getCancelRatio_49() + ", getCancelRatio_50()=" + getCancelRatio_50() + ", getCancelRatio_51()="
				+ getCancelRatio_51() + ", getCancelRatio_52()=" + getCancelRatio_52() + ", getCancelRatio_53()="
				+ getCancelRatio_53() + ", getCancelRatio_54()=" + getCancelRatio_54() + ", getCancelRatio_55()="
				+ getCancelRatio_55() + ", getCancelRatio_56()=" + getCancelRatio_56() + ", getCancelRatio_57()="
				+ getCancelRatio_57() + ", getCancelRatio_58()=" + getCancelRatio_58() + ", getCancelRatio_59()="
				+ getCancelRatio_59() + ", getCancelRatio_60()=" + getCancelRatio_60() + ", getCancelRatio_61()="
				+ getCancelRatio_61() + ", getCancelRatio_62()=" + getCancelRatio_62() + ", getCancelRatio_63()="
				+ getCancelRatio_63() + ", getCancelRatio_64()=" + getCancelRatio_64() + ", getCancelRatio_65()="
				+ getCancelRatio_65() + ", getCancelRatio_66()=" + getCancelRatio_66() + ", getCancelRatio_67()="
				+ getCancelRatio_67() + ", getCancelRatio_68()=" + getCancelRatio_68() + ", getCancelRatio_69()="
				+ getCancelRatio_69() + ", getCancelRatio_70()=" + getCancelRatio_70() + ", getCancelRatio_71()="
				+ getCancelRatio_71() + ", getCancelRatio_72()=" + getCancelRatio_72() + ", getCancelRatio_73()="
				+ getCancelRatio_73() + ", getCancelRatio_74()=" + getCancelRatio_74() + ", getCancelRatio_75()="
				+ getCancelRatio_75() + ", getCancelRatio_76()=" + getCancelRatio_76() + ", getCancelRatio_77()="
				+ getCancelRatio_77() + ", getCancelRatio_78()=" + getCancelRatio_78() + ", getCancelRatio_79()="
				+ getCancelRatio_79() + ", getCancelRatio_80()=" + getCancelRatio_80() + ", getCancelRatio_81()="
				+ getCancelRatio_81() + ", getCancelRatio_82()=" + getCancelRatio_82() + ", getCancelRatio_83()="
				+ getCancelRatio_83() + ", getCancelRatio_84()=" + getCancelRatio_84() + ", getCancelRatio_85()="
				+ getCancelRatio_85() + ", getCancelRatio_86()=" + getCancelRatio_86() + ", getCancelRatio_87()="
				+ getCancelRatio_87() + ", getCancelRatio_88()=" + getCancelRatio_88() + ", getCancelRatio_89()="
				+ getCancelRatio_89() + ", getCancelRatio_90()=" + getCancelRatio_90() + ", getCancelRatio_91()="
				+ getCancelRatio_91() + ", getCancelRatio_92()=" + getCancelRatio_92() + ", getCancelRatio_93()="
				+ getCancelRatio_93() + ", getCancelRatio_94()=" + getCancelRatio_94() + ", getCancelRatio_95()="
				+ getCancelRatio_95() + ", getCancelRatio_96()=" + getCancelRatio_96() + ", getCancelRatio_97()="
				+ getCancelRatio_97() + ", getCancelRatio_98()=" + getCancelRatio_98() + ", getCancelRatio_99()="
				+ getCancelRatio_99() + ", getCancelRatio_100()=" + getCancelRatio_100() + ", getCancelRatio_101()="
				+ getCancelRatio_101() + ", getCancelRatio_102()=" + getCancelRatio_102() + ", getCancelRatio_103()="
				+ getCancelRatio_103() + ", getCancelRatio_104()=" + getCancelRatio_104() + ", getCancelRatio_105()="
				+ getCancelRatio_105() + ", getCancelRatio_106()=" + getCancelRatio_106() + ", getCancelRatio_107()="
				+ getCancelRatio_107() + ", getCancelRatio_108()=" + getCancelRatio_108() + ", getCancelRatio_109()="
				+ getCancelRatio_109() + ", getCancelRatio_110()=" + getCancelRatio_110() + ", getCancelRatio_111()="
				+ getCancelRatio_111() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getInsAge() {
		return insAge;
	}

	public void setInsAge(Integer insAge) {
		this.insAge = insAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getCancelRatio_0() {
		return cancelRatio_0;
	}

	public void setCancelRatio_0(BigDecimal cancelRatio_0) {
		this.cancelRatio_0 = cancelRatio_0;
	}

	public BigDecimal getCancelRatio_1() {
		return cancelRatio_1;
	}

	public void setCancelRatio_1(BigDecimal cancelRatio_1) {
		this.cancelRatio_1 = cancelRatio_1;
	}

	public BigDecimal getCancelRatio_2() {
		return cancelRatio_2;
	}

	public void setCancelRatio_2(BigDecimal cancelRatio_2) {
		this.cancelRatio_2 = cancelRatio_2;
	}

	public BigDecimal getCancelRatio_3() {
		return cancelRatio_3;
	}

	public void setCancelRatio_3(BigDecimal cancelRatio_3) {
		this.cancelRatio_3 = cancelRatio_3;
	}

	public BigDecimal getCancelRatio_4() {
		return cancelRatio_4;
	}

	public void setCancelRatio_4(BigDecimal cancelRatio_4) {
		this.cancelRatio_4 = cancelRatio_4;
	}

	public BigDecimal getCancelRatio_5() {
		return cancelRatio_5;
	}

	public void setCancelRatio_5(BigDecimal cancelRatio_5) {
		this.cancelRatio_5 = cancelRatio_5;
	}

	public BigDecimal getCancelRatio_6() {
		return cancelRatio_6;
	}

	public void setCancelRatio_6(BigDecimal cancelRatio_6) {
		this.cancelRatio_6 = cancelRatio_6;
	}

	public BigDecimal getCancelRatio_7() {
		return cancelRatio_7;
	}

	public void setCancelRatio_7(BigDecimal cancelRatio_7) {
		this.cancelRatio_7 = cancelRatio_7;
	}

	public BigDecimal getCancelRatio_8() {
		return cancelRatio_8;
	}

	public void setCancelRatio_8(BigDecimal cancelRatio_8) {
		this.cancelRatio_8 = cancelRatio_8;
	}

	public BigDecimal getCancelRatio_9() {
		return cancelRatio_9;
	}

	public void setCancelRatio_9(BigDecimal cancelRatio_9) {
		this.cancelRatio_9 = cancelRatio_9;
	}

	public BigDecimal getCancelRatio_10() {
		return cancelRatio_10;
	}

	public void setCancelRatio_10(BigDecimal cancelRatio_10) {
		this.cancelRatio_10 = cancelRatio_10;
	}

	public BigDecimal getCancelRatio_11() {
		return cancelRatio_11;
	}

	public void setCancelRatio_11(BigDecimal cancelRatio_11) {
		this.cancelRatio_11 = cancelRatio_11;
	}

	public BigDecimal getCancelRatio_12() {
		return cancelRatio_12;
	}

	public void setCancelRatio_12(BigDecimal cancelRatio_12) {
		this.cancelRatio_12 = cancelRatio_12;
	}

	public BigDecimal getCancelRatio_13() {
		return cancelRatio_13;
	}

	public void setCancelRatio_13(BigDecimal cancelRatio_13) {
		this.cancelRatio_13 = cancelRatio_13;
	}

	public BigDecimal getCancelRatio_14() {
		return cancelRatio_14;
	}

	public void setCancelRatio_14(BigDecimal cancelRatio_14) {
		this.cancelRatio_14 = cancelRatio_14;
	}

	public BigDecimal getCancelRatio_15() {
		return cancelRatio_15;
	}

	public void setCancelRatio_15(BigDecimal cancelRatio_15) {
		this.cancelRatio_15 = cancelRatio_15;
	}

	public BigDecimal getCancelRatio_16() {
		return cancelRatio_16;
	}

	public void setCancelRatio_16(BigDecimal cancelRatio_16) {
		this.cancelRatio_16 = cancelRatio_16;
	}

	public BigDecimal getCancelRatio_17() {
		return cancelRatio_17;
	}

	public void setCancelRatio_17(BigDecimal cancelRatio_17) {
		this.cancelRatio_17 = cancelRatio_17;
	}

	public BigDecimal getCancelRatio_18() {
		return cancelRatio_18;
	}

	public void setCancelRatio_18(BigDecimal cancelRatio_18) {
		this.cancelRatio_18 = cancelRatio_18;
	}

	public BigDecimal getCancelRatio_19() {
		return cancelRatio_19;
	}

	public void setCancelRatio_19(BigDecimal cancelRatio_19) {
		this.cancelRatio_19 = cancelRatio_19;
	}

	public BigDecimal getCancelRatio_20() {
		return cancelRatio_20;
	}

	public void setCancelRatio_20(BigDecimal cancelRatio_20) {
		this.cancelRatio_20 = cancelRatio_20;
	}

	public BigDecimal getCancelRatio_21() {
		return cancelRatio_21;
	}

	public void setCancelRatio_21(BigDecimal cancelRatio_21) {
		this.cancelRatio_21 = cancelRatio_21;
	}

	public BigDecimal getCancelRatio_22() {
		return cancelRatio_22;
	}

	public void setCancelRatio_22(BigDecimal cancelRatio_22) {
		this.cancelRatio_22 = cancelRatio_22;
	}

	public BigDecimal getCancelRatio_23() {
		return cancelRatio_23;
	}

	public void setCancelRatio_23(BigDecimal cancelRatio_23) {
		this.cancelRatio_23 = cancelRatio_23;
	}

	public BigDecimal getCancelRatio_24() {
		return cancelRatio_24;
	}

	public void setCancelRatio_24(BigDecimal cancelRatio_24) {
		this.cancelRatio_24 = cancelRatio_24;
	}

	public BigDecimal getCancelRatio_25() {
		return cancelRatio_25;
	}

	public void setCancelRatio_25(BigDecimal cancelRatio_25) {
		this.cancelRatio_25 = cancelRatio_25;
	}

	public BigDecimal getCancelRatio_26() {
		return cancelRatio_26;
	}

	public void setCancelRatio_26(BigDecimal cancelRatio_26) {
		this.cancelRatio_26 = cancelRatio_26;
	}

	public BigDecimal getCancelRatio_27() {
		return cancelRatio_27;
	}

	public void setCancelRatio_27(BigDecimal cancelRatio_27) {
		this.cancelRatio_27 = cancelRatio_27;
	}

	public BigDecimal getCancelRatio_28() {
		return cancelRatio_28;
	}

	public void setCancelRatio_28(BigDecimal cancelRatio_28) {
		this.cancelRatio_28 = cancelRatio_28;
	}

	public BigDecimal getCancelRatio_29() {
		return cancelRatio_29;
	}

	public void setCancelRatio_29(BigDecimal cancelRatio_29) {
		this.cancelRatio_29 = cancelRatio_29;
	}

	public BigDecimal getCancelRatio_30() {
		return cancelRatio_30;
	}

	public void setCancelRatio_30(BigDecimal cancelRatio_30) {
		this.cancelRatio_30 = cancelRatio_30;
	}

	public BigDecimal getCancelRatio_31() {
		return cancelRatio_31;
	}

	public void setCancelRatio_31(BigDecimal cancelRatio_31) {
		this.cancelRatio_31 = cancelRatio_31;
	}

	public BigDecimal getCancelRatio_32() {
		return cancelRatio_32;
	}

	public void setCancelRatio_32(BigDecimal cancelRatio_32) {
		this.cancelRatio_32 = cancelRatio_32;
	}

	public BigDecimal getCancelRatio_33() {
		return cancelRatio_33;
	}

	public void setCancelRatio_33(BigDecimal cancelRatio_33) {
		this.cancelRatio_33 = cancelRatio_33;
	}

	public BigDecimal getCancelRatio_34() {
		return cancelRatio_34;
	}

	public void setCancelRatio_34(BigDecimal cancelRatio_34) {
		this.cancelRatio_34 = cancelRatio_34;
	}

	public BigDecimal getCancelRatio_35() {
		return cancelRatio_35;
	}

	public void setCancelRatio_35(BigDecimal cancelRatio_35) {
		this.cancelRatio_35 = cancelRatio_35;
	}

	public BigDecimal getCancelRatio_36() {
		return cancelRatio_36;
	}

	public void setCancelRatio_36(BigDecimal cancelRatio_36) {
		this.cancelRatio_36 = cancelRatio_36;
	}

	public BigDecimal getCancelRatio_37() {
		return cancelRatio_37;
	}

	public void setCancelRatio_37(BigDecimal cancelRatio_37) {
		this.cancelRatio_37 = cancelRatio_37;
	}

	public BigDecimal getCancelRatio_38() {
		return cancelRatio_38;
	}

	public void setCancelRatio_38(BigDecimal cancelRatio_38) {
		this.cancelRatio_38 = cancelRatio_38;
	}

	public BigDecimal getCancelRatio_39() {
		return cancelRatio_39;
	}

	public void setCancelRatio_39(BigDecimal cancelRatio_39) {
		this.cancelRatio_39 = cancelRatio_39;
	}

	public BigDecimal getCancelRatio_40() {
		return cancelRatio_40;
	}

	public void setCancelRatio_40(BigDecimal cancelRatio_40) {
		this.cancelRatio_40 = cancelRatio_40;
	}

	public BigDecimal getCancelRatio_41() {
		return cancelRatio_41;
	}

	public void setCancelRatio_41(BigDecimal cancelRatio_41) {
		this.cancelRatio_41 = cancelRatio_41;
	}

	public BigDecimal getCancelRatio_42() {
		return cancelRatio_42;
	}

	public void setCancelRatio_42(BigDecimal cancelRatio_42) {
		this.cancelRatio_42 = cancelRatio_42;
	}

	public BigDecimal getCancelRatio_43() {
		return cancelRatio_43;
	}

	public void setCancelRatio_43(BigDecimal cancelRatio_43) {
		this.cancelRatio_43 = cancelRatio_43;
	}

	public BigDecimal getCancelRatio_44() {
		return cancelRatio_44;
	}

	public void setCancelRatio_44(BigDecimal cancelRatio_44) {
		this.cancelRatio_44 = cancelRatio_44;
	}

	public BigDecimal getCancelRatio_45() {
		return cancelRatio_45;
	}

	public void setCancelRatio_45(BigDecimal cancelRatio_45) {
		this.cancelRatio_45 = cancelRatio_45;
	}

	public BigDecimal getCancelRatio_46() {
		return cancelRatio_46;
	}

	public void setCancelRatio_46(BigDecimal cancelRatio_46) {
		this.cancelRatio_46 = cancelRatio_46;
	}

	public BigDecimal getCancelRatio_47() {
		return cancelRatio_47;
	}

	public void setCancelRatio_47(BigDecimal cancelRatio_47) {
		this.cancelRatio_47 = cancelRatio_47;
	}

	public BigDecimal getCancelRatio_48() {
		return cancelRatio_48;
	}

	public void setCancelRatio_48(BigDecimal cancelRatio_48) {
		this.cancelRatio_48 = cancelRatio_48;
	}

	public BigDecimal getCancelRatio_49() {
		return cancelRatio_49;
	}

	public void setCancelRatio_49(BigDecimal cancelRatio_49) {
		this.cancelRatio_49 = cancelRatio_49;
	}

	public BigDecimal getCancelRatio_50() {
		return cancelRatio_50;
	}

	public void setCancelRatio_50(BigDecimal cancelRatio_50) {
		this.cancelRatio_50 = cancelRatio_50;
	}

	public BigDecimal getCancelRatio_51() {
		return cancelRatio_51;
	}

	public void setCancelRatio_51(BigDecimal cancelRatio_51) {
		this.cancelRatio_51 = cancelRatio_51;
	}

	public BigDecimal getCancelRatio_52() {
		return cancelRatio_52;
	}

	public void setCancelRatio_52(BigDecimal cancelRatio_52) {
		this.cancelRatio_52 = cancelRatio_52;
	}

	public BigDecimal getCancelRatio_53() {
		return cancelRatio_53;
	}

	public void setCancelRatio_53(BigDecimal cancelRatio_53) {
		this.cancelRatio_53 = cancelRatio_53;
	}

	public BigDecimal getCancelRatio_54() {
		return cancelRatio_54;
	}

	public void setCancelRatio_54(BigDecimal cancelRatio_54) {
		this.cancelRatio_54 = cancelRatio_54;
	}

	public BigDecimal getCancelRatio_55() {
		return cancelRatio_55;
	}

	public void setCancelRatio_55(BigDecimal cancelRatio_55) {
		this.cancelRatio_55 = cancelRatio_55;
	}

	public BigDecimal getCancelRatio_56() {
		return cancelRatio_56;
	}

	public void setCancelRatio_56(BigDecimal cancelRatio_56) {
		this.cancelRatio_56 = cancelRatio_56;
	}

	public BigDecimal getCancelRatio_57() {
		return cancelRatio_57;
	}

	public void setCancelRatio_57(BigDecimal cancelRatio_57) {
		this.cancelRatio_57 = cancelRatio_57;
	}

	public BigDecimal getCancelRatio_58() {
		return cancelRatio_58;
	}

	public void setCancelRatio_58(BigDecimal cancelRatio_58) {
		this.cancelRatio_58 = cancelRatio_58;
	}

	public BigDecimal getCancelRatio_59() {
		return cancelRatio_59;
	}

	public void setCancelRatio_59(BigDecimal cancelRatio_59) {
		this.cancelRatio_59 = cancelRatio_59;
	}

	public BigDecimal getCancelRatio_60() {
		return cancelRatio_60;
	}

	public void setCancelRatio_60(BigDecimal cancelRatio_60) {
		this.cancelRatio_60 = cancelRatio_60;
	}

	public BigDecimal getCancelRatio_61() {
		return cancelRatio_61;
	}

	public void setCancelRatio_61(BigDecimal cancelRatio_61) {
		this.cancelRatio_61 = cancelRatio_61;
	}

	public BigDecimal getCancelRatio_62() {
		return cancelRatio_62;
	}

	public void setCancelRatio_62(BigDecimal cancelRatio_62) {
		this.cancelRatio_62 = cancelRatio_62;
	}

	public BigDecimal getCancelRatio_63() {
		return cancelRatio_63;
	}

	public void setCancelRatio_63(BigDecimal cancelRatio_63) {
		this.cancelRatio_63 = cancelRatio_63;
	}

	public BigDecimal getCancelRatio_64() {
		return cancelRatio_64;
	}

	public void setCancelRatio_64(BigDecimal cancelRatio_64) {
		this.cancelRatio_64 = cancelRatio_64;
	}

	public BigDecimal getCancelRatio_65() {
		return cancelRatio_65;
	}

	public void setCancelRatio_65(BigDecimal cancelRatio_65) {
		this.cancelRatio_65 = cancelRatio_65;
	}

	public BigDecimal getCancelRatio_66() {
		return cancelRatio_66;
	}

	public void setCancelRatio_66(BigDecimal cancelRatio_66) {
		this.cancelRatio_66 = cancelRatio_66;
	}

	public BigDecimal getCancelRatio_67() {
		return cancelRatio_67;
	}

	public void setCancelRatio_67(BigDecimal cancelRatio_67) {
		this.cancelRatio_67 = cancelRatio_67;
	}

	public BigDecimal getCancelRatio_68() {
		return cancelRatio_68;
	}

	public void setCancelRatio_68(BigDecimal cancelRatio_68) {
		this.cancelRatio_68 = cancelRatio_68;
	}

	public BigDecimal getCancelRatio_69() {
		return cancelRatio_69;
	}

	public void setCancelRatio_69(BigDecimal cancelRatio_69) {
		this.cancelRatio_69 = cancelRatio_69;
	}

	public BigDecimal getCancelRatio_70() {
		return cancelRatio_70;
	}

	public void setCancelRatio_70(BigDecimal cancelRatio_70) {
		this.cancelRatio_70 = cancelRatio_70;
	}

	public BigDecimal getCancelRatio_71() {
		return cancelRatio_71;
	}

	public void setCancelRatio_71(BigDecimal cancelRatio_71) {
		this.cancelRatio_71 = cancelRatio_71;
	}

	public BigDecimal getCancelRatio_72() {
		return cancelRatio_72;
	}

	public void setCancelRatio_72(BigDecimal cancelRatio_72) {
		this.cancelRatio_72 = cancelRatio_72;
	}

	public BigDecimal getCancelRatio_73() {
		return cancelRatio_73;
	}

	public void setCancelRatio_73(BigDecimal cancelRatio_73) {
		this.cancelRatio_73 = cancelRatio_73;
	}

	public BigDecimal getCancelRatio_74() {
		return cancelRatio_74;
	}

	public void setCancelRatio_74(BigDecimal cancelRatio_74) {
		this.cancelRatio_74 = cancelRatio_74;
	}

	public BigDecimal getCancelRatio_75() {
		return cancelRatio_75;
	}

	public void setCancelRatio_75(BigDecimal cancelRatio_75) {
		this.cancelRatio_75 = cancelRatio_75;
	}

	public BigDecimal getCancelRatio_76() {
		return cancelRatio_76;
	}

	public void setCancelRatio_76(BigDecimal cancelRatio_76) {
		this.cancelRatio_76 = cancelRatio_76;
	}

	public BigDecimal getCancelRatio_77() {
		return cancelRatio_77;
	}

	public void setCancelRatio_77(BigDecimal cancelRatio_77) {
		this.cancelRatio_77 = cancelRatio_77;
	}

	public BigDecimal getCancelRatio_78() {
		return cancelRatio_78;
	}

	public void setCancelRatio_78(BigDecimal cancelRatio_78) {
		this.cancelRatio_78 = cancelRatio_78;
	}

	public BigDecimal getCancelRatio_79() {
		return cancelRatio_79;
	}

	public void setCancelRatio_79(BigDecimal cancelRatio_79) {
		this.cancelRatio_79 = cancelRatio_79;
	}

	public BigDecimal getCancelRatio_80() {
		return cancelRatio_80;
	}

	public void setCancelRatio_80(BigDecimal cancelRatio_80) {
		this.cancelRatio_80 = cancelRatio_80;
	}

	public BigDecimal getCancelRatio_81() {
		return cancelRatio_81;
	}

	public void setCancelRatio_81(BigDecimal cancelRatio_81) {
		this.cancelRatio_81 = cancelRatio_81;
	}

	public BigDecimal getCancelRatio_82() {
		return cancelRatio_82;
	}

	public void setCancelRatio_82(BigDecimal cancelRatio_82) {
		this.cancelRatio_82 = cancelRatio_82;
	}

	public BigDecimal getCancelRatio_83() {
		return cancelRatio_83;
	}

	public void setCancelRatio_83(BigDecimal cancelRatio_83) {
		this.cancelRatio_83 = cancelRatio_83;
	}

	public BigDecimal getCancelRatio_84() {
		return cancelRatio_84;
	}

	public void setCancelRatio_84(BigDecimal cancelRatio_84) {
		this.cancelRatio_84 = cancelRatio_84;
	}

	public BigDecimal getCancelRatio_85() {
		return cancelRatio_85;
	}

	public void setCancelRatio_85(BigDecimal cancelRatio_85) {
		this.cancelRatio_85 = cancelRatio_85;
	}

	public BigDecimal getCancelRatio_86() {
		return cancelRatio_86;
	}

	public void setCancelRatio_86(BigDecimal cancelRatio_86) {
		this.cancelRatio_86 = cancelRatio_86;
	}

	public BigDecimal getCancelRatio_87() {
		return cancelRatio_87;
	}

	public void setCancelRatio_87(BigDecimal cancelRatio_87) {
		this.cancelRatio_87 = cancelRatio_87;
	}

	public BigDecimal getCancelRatio_88() {
		return cancelRatio_88;
	}

	public void setCancelRatio_88(BigDecimal cancelRatio_88) {
		this.cancelRatio_88 = cancelRatio_88;
	}

	public BigDecimal getCancelRatio_89() {
		return cancelRatio_89;
	}

	public void setCancelRatio_89(BigDecimal cancelRatio_89) {
		this.cancelRatio_89 = cancelRatio_89;
	}

	public BigDecimal getCancelRatio_90() {
		return cancelRatio_90;
	}

	public void setCancelRatio_90(BigDecimal cancelRatio_90) {
		this.cancelRatio_90 = cancelRatio_90;
	}

	public BigDecimal getCancelRatio_91() {
		return cancelRatio_91;
	}

	public void setCancelRatio_91(BigDecimal cancelRatio_91) {
		this.cancelRatio_91 = cancelRatio_91;
	}

	public BigDecimal getCancelRatio_92() {
		return cancelRatio_92;
	}

	public void setCancelRatio_92(BigDecimal cancelRatio_92) {
		this.cancelRatio_92 = cancelRatio_92;
	}

	public BigDecimal getCancelRatio_93() {
		return cancelRatio_93;
	}

	public void setCancelRatio_93(BigDecimal cancelRatio_93) {
		this.cancelRatio_93 = cancelRatio_93;
	}

	public BigDecimal getCancelRatio_94() {
		return cancelRatio_94;
	}

	public void setCancelRatio_94(BigDecimal cancelRatio_94) {
		this.cancelRatio_94 = cancelRatio_94;
	}

	public BigDecimal getCancelRatio_95() {
		return cancelRatio_95;
	}

	public void setCancelRatio_95(BigDecimal cancelRatio_95) {
		this.cancelRatio_95 = cancelRatio_95;
	}

	public BigDecimal getCancelRatio_96() {
		return cancelRatio_96;
	}

	public void setCancelRatio_96(BigDecimal cancelRatio_96) {
		this.cancelRatio_96 = cancelRatio_96;
	}

	public BigDecimal getCancelRatio_97() {
		return cancelRatio_97;
	}

	public void setCancelRatio_97(BigDecimal cancelRatio_97) {
		this.cancelRatio_97 = cancelRatio_97;
	}

	public BigDecimal getCancelRatio_98() {
		return cancelRatio_98;
	}

	public void setCancelRatio_98(BigDecimal cancelRatio_98) {
		this.cancelRatio_98 = cancelRatio_98;
	}

	public BigDecimal getCancelRatio_99() {
		return cancelRatio_99;
	}

	public void setCancelRatio_99(BigDecimal cancelRatio_99) {
		this.cancelRatio_99 = cancelRatio_99;
	}

	public BigDecimal getCancelRatio_100() {
		return cancelRatio_100;
	}

	public void setCancelRatio_100(BigDecimal cancelRatio_100) {
		this.cancelRatio_100 = cancelRatio_100;
	}

	public BigDecimal getCancelRatio_101() {
		return cancelRatio_101;
	}

	public void setCancelRatio_101(BigDecimal cancelRatio_101) {
		this.cancelRatio_101 = cancelRatio_101;
	}

	public BigDecimal getCancelRatio_102() {
		return cancelRatio_102;
	}

	public void setCancelRatio_102(BigDecimal cancelRatio_102) {
		this.cancelRatio_102 = cancelRatio_102;
	}

	public BigDecimal getCancelRatio_103() {
		return cancelRatio_103;
	}

	public void setCancelRatio_103(BigDecimal cancelRatio_103) {
		this.cancelRatio_103 = cancelRatio_103;
	}

	public BigDecimal getCancelRatio_104() {
		return cancelRatio_104;
	}

	public void setCancelRatio_104(BigDecimal cancelRatio_104) {
		this.cancelRatio_104 = cancelRatio_104;
	}

	public BigDecimal getCancelRatio_105() {
		return cancelRatio_105;
	}

	public void setCancelRatio_105(BigDecimal cancelRatio_105) {
		this.cancelRatio_105 = cancelRatio_105;
	}

	public BigDecimal getCancelRatio_106() {
		return cancelRatio_106;
	}

	public void setCancelRatio_106(BigDecimal cancelRatio_106) {
		this.cancelRatio_106 = cancelRatio_106;
	}

	public BigDecimal getCancelRatio_107() {
		return cancelRatio_107;
	}

	public void setCancelRatio_107(BigDecimal cancelRatio_107) {
		this.cancelRatio_107 = cancelRatio_107;
	}

	public BigDecimal getCancelRatio_108() {
		return cancelRatio_108;
	}

	public void setCancelRatio_108(BigDecimal cancelRatio_108) {
		this.cancelRatio_108 = cancelRatio_108;
	}

	public BigDecimal getCancelRatio_109() {
		return cancelRatio_109;
	}

	public void setCancelRatio_109(BigDecimal cancelRatio_109) {
		this.cancelRatio_109 = cancelRatio_109;
	}

	public BigDecimal getCancelRatio_110() {
		return cancelRatio_110;
	}

	public void setCancelRatio_110(BigDecimal cancelRatio_110) {
		this.cancelRatio_110 = cancelRatio_110;
	}

	public BigDecimal getCancelRatio_111() {
		return cancelRatio_111;
	}

	public void setCancelRatio_111(BigDecimal cancelRatio_111) {
		this.cancelRatio_111 = cancelRatio_111;
	}

}
