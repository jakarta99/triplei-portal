package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

/**
 * 查表: 解約金費率
 */
@Getter
@Setter
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


}
