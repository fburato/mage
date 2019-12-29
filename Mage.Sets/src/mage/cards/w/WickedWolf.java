package mage.cards.w;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.FightTargetSourceEffect;
import mage.abilities.effects.common.TapSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.ControllerPredicate;
import mage.target.TargetPermanent;
import mage.target.common.TargetControlledPermanent;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class WickedWolf extends CardImpl {

    private static final FilterPermanent filter
            = new FilterCreaturePermanent("creature you don't control");
    private static final FilterControlledPermanent filter2
            = new FilterControlledPermanent(SubType.FOOD, "a Food");

    static {
        filter.add(new ControllerPredicate(TargetController.NOT_YOU));
    }

    public WickedWolf(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{G}{G}");

        this.subtype.add(SubType.WOLF);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // When Wicked Wolf enters the battlefield, it fights up to one target creature you don't control.
        Ability ability = new EntersBattlefieldTriggeredAbility(
                new FightTargetSourceEffect().setText("it fights up to one target creature you don't control")
        );
        ability.addTarget(new TargetPermanent(0, 1, filter, false));
        this.addAbility(ability);

        // Sacrifice a Food: Put a +1/+1 counter on Wicked Wolf. It gains indestructible until end of turn. Tap it.
        ability = new SimpleActivatedAbility(
                new AddCountersSourceEffect(CounterType.P1P1.createInstance()),
                new SacrificeTargetCost(new TargetControlledPermanent(filter2))
        );
        ability.addEffect(new GainAbilitySourceEffect(
                IndestructibleAbility.getInstance(), Duration.EndOfTurn
        ).setText("it gains indestructible until end of turn."));
        ability.addEffect(new TapSourceEffect().setText("Tap it"));
        this.addAbility(ability);
    }

    private WickedWolf(final WickedWolf card) {
        super(card);
    }

    @Override
    public WickedWolf copy() {
        return new WickedWolf(this);
    }
}
