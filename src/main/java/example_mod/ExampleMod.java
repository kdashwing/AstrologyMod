package example_mod;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Astrology.Flare;
import basemod.AutoAdd;
import basemod.AutoComplete;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;

@SpireInitializer
public class ExampleMod implements PostExhaustSubscriber,
	PostBattleSubscriber, PostDungeonInitializeSubscriber,EditCardsSubscriber {

	private int count, totalCount;
	
	private void resetCounts() {
		totalCount = count = 0;
	}
	
	public ExampleMod() {
		BaseMod.subscribe(this);
		resetCounts();
	}
	
	public static void initialize() {
		new ExampleMod();
	}
	

	public void receivePostExhaust(AbstractCard c) {
		count++;
		totalCount++;
	}
	

	public void receivePostBattle(AbstractRoom r) {
		System.out.println(count + " cards were exhausted this battle, " +
			totalCount + " cards have been exhausted so far this act.");
		count = 0;
	}
	

	public void receivePostDungeonInitialize() {
		resetCounts();
	}

	public static final String MyModID = "Astrology";
	
	public void receiveEditCards() {
		// This finds and adds all cards in the same package (or sub-package) as MyAbstractCard
	    // along with marking all added cards as seen
	    new AutoAdd(MyModID)
	        .packageFilter(Flare.class)
	        .setDefaultSeen(true)
	        .cards();
	}
	
}