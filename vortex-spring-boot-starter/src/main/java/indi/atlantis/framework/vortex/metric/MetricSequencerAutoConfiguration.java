package indi.atlantis.framework.vortex.metric;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import indi.atlantis.framework.vortex.Handler;

/**
 * 
 * MetricSequencerAutoConfiguration
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
@Import({ MetricSequencerController.class })
@Configuration(proxyBeanMethods = false)
public class MetricSequencerAutoConfiguration {

	@Bean
	public Environment primaryEnvironment() {
		return new Environment();
	}

	@Bean
	public Environment secondaryEnvironment() {
		return new Environment();
	}

	@Bean
	public Handler boolMetricHandler(@Qualifier("primaryEnvironment") Environment environment) {
		return new BoolMetricHandler("bool", environment.boolMetricSequencer());
	}

	@Bean
	public Handler boolMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new BoolMetricSynchronizationHandler("bool-", environment.boolMetricSequencer(), false);
	}

	@Bean
	public Handler incrementalBoolMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new BoolMetricSynchronizationHandler("bool+", environment.boolMetricSequencer(), true);
	}

	@Bean
	public Handler decimalMetricHandler(@Qualifier("primaryEnvironment") Environment environment) {
		return new DecimalMetricHandler("decimal", environment.decimalMetricSequencer());
	}

	@Bean
	public Handler decimalMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new DecimalMetricSynchronizationHandler("decimal-", environment.decimalMetricSequencer(), false);
	}

	@Bean
	public Handler incrementalDecimalMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new DecimalMetricSynchronizationHandler("decimal+", environment.decimalMetricSequencer(), true);
	}

	@Bean
	public Handler doubleMetricHandler(@Qualifier("primaryEnvironment") Environment environment) {
		return new DoubleMetricHandler("double", environment.doubleMetricSequencer());
	}

	@Bean
	public Handler doubleMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new DoubleMetricSynchronizationHandler("double-", environment.doubleMetricSequencer(), false);
	}

	@Bean
	public Handler incrementalDoubleMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new DoubleMetricSynchronizationHandler("double+", environment.doubleMetricSequencer(), true);
	}

	@Bean
	public Handler longMetricHandler(@Qualifier("primaryEnvironment") Environment environment) {
		return new LongMetricHandler("long", environment.longMetricSequencer());
	}

	@Bean
	public Handler longMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new LongMetricSynchronizationHandler("long-", environment.longMetricSequencer(), false);
	}

	@Bean
	public Handler incrementalLongMetricSynchronizationHandler(@Qualifier("secondaryEnvironment") Environment environment) {
		return new LongMetricSynchronizationHandler("long+", environment.longMetricSequencer(), true);
	}

	@Bean
	public Synchronizer incrementalBoolMetricSynchronizer(@Qualifier("primaryEnvironment") Environment environment) {
		return new BoolMetricSynchronizer("bool+", environment.boolMetricSequencer(), true);
	}

	@Bean
	public Synchronizer boolMetricSynchronizer(@Qualifier("secondaryEnvironment") Environment environment) {
		return new BoolMetricSynchronizer("bool-", environment.boolMetricSequencer(), false);
	}

	@Bean
	public Synchronizer incrementalDecimalMetricSynchronizer(@Qualifier("primaryEnvironment") Environment environment) {
		return new DecimalMetricSynchronizer("decimal+", environment.decimalMetricSequencer(), true);
	}

	@Bean
	public Synchronizer decimalMetricSynchronizer(@Qualifier("secondaryEnvironment") Environment environment) {
		return new DecimalMetricSynchronizer("decimal-", environment.decimalMetricSequencer(), false);
	}

	@Bean
	public Synchronizer incrementalDoubleMetricSynchronizer(@Qualifier("primaryEnvironment") Environment environment) {
		return new DoubleMetricSynchronizer("double+", environment.doubleMetricSequencer(), true);
	}

	@Bean
	public Synchronizer doubleMetricSynchronizer(@Qualifier("secondaryEnvironment") Environment environment) {
		return new DoubleMetricSynchronizer("double-", environment.doubleMetricSequencer(), false);
	}

	@Bean
	public Synchronizer incrementalLongMetricSynchronizer(@Qualifier("primaryEnvironment") Environment environment) {
		return new LongMetricSynchronizer("long+", environment.longMetricSequencer(), true);
	}

	@Bean
	public Synchronizer longMetricSynchronizer(@Qualifier("secondaryEnvironment") Environment environment) {
		return new LongMetricSynchronizer("long-", environment.longMetricSequencer(), false);
	}

	@Bean
	public IncrementalSynchronizationExecutor incrementalSynchronizationExecutor(
			@Qualifier("incrementalBoolMetricSynchronizer") Synchronizer boolMetricSynchronizer,
			@Qualifier("incrementalLongMetricSynchronizer") Synchronizer longMetricSynchronizer,
			@Qualifier("incrementalDoubleMetricSynchronizer") Synchronizer doubleMetricSynchronizer,
			@Qualifier("incrementalDecimalMetricSynchronizer") Synchronizer decimalMetricSynchronizer) {
		IncrementalSynchronizationExecutor synchronizationExecutor = new IncrementalSynchronizationExecutor();
		synchronizationExecutor.addSynchronizers(boolMetricSynchronizer, longMetricSynchronizer, doubleMetricSynchronizer,
				decimalMetricSynchronizer);
		return synchronizationExecutor;
	}

	@Bean
	public FullSynchronizationExecutor fullSynchronizationExecutor(@Qualifier("boolMetricSynchronizer") Synchronizer boolMetricSynchronizer,
			@Qualifier("longMetricSynchronizer") Synchronizer longMetricSynchronizer,
			@Qualifier("doubleMetricSynchronizer") Synchronizer doubleMetricSynchronizer,
			@Qualifier("decimalMetricSynchronizer") Synchronizer decimalMetricSynchronizer) {
		FullSynchronizationExecutor synchronizationExecutor = new FullSynchronizationExecutor();
		synchronizationExecutor.addSynchronizers(boolMetricSynchronizer, longMetricSynchronizer, doubleMetricSynchronizer,
				decimalMetricSynchronizer);
		return synchronizationExecutor;
	}

}