package indi.atlantis.framework.vortex.metric;

import indi.atlantis.framework.vortex.Handler;

/**
 * 
 * GenericUserMetricRegistration
 * 
 * @author Jimmy Hoff
 *
 * @version 1.0
 */
public class GenericUserMetricRegistration<V> implements UserMetricRegistrar<V> {

	public GenericUserMetricRegistration(UserMetricSequencer<String, V> metricSequencer, UserTypeHandler<V> typeHandler) {
		this.seconaryMetricSequencer = metricSequencer;
		this.primaryMetricSequencer = new SimpleMetricSequencer<>(metricSequencer.getSpan(), metricSequencer.getSpanUnit(),
				metricSequencer.getBufferSize(), null);
		this.typeHandler = typeHandler;
	}

	private final UserTypeHandler<V> typeHandler;
	private final MetricSequencer<String, UserMetric<V>> primaryMetricSequencer;
	private final MetricSequencer<String, UserMetric<V>> seconaryMetricSequencer;

	@Override
	public String getDataType() {
		return typeHandler.getDataTypeName();
	}

	@Override
	public MetricSequencer<String, UserMetric<V>> getMetricSequencer() {
		return seconaryMetricSequencer;
	}

	@Override
	public Handler getHandler() {
		return new GenericUserMetricHandler<>(typeHandler.getDataTypeName(),
				new GenericUserMetricListener<>(primaryMetricSequencer, typeHandler));
	}

	@Override
	public Handler getSynchronizationHandler() {
		return new GenericUserMetricSynchronizationHandler<>(typeHandler.getDataTypeName() + "-", false,
				new GenericUserMetricListener<>(seconaryMetricSequencer, typeHandler));
	}

	@Override
	public Handler getIncrementalSynchronizationHandler() {
		return new GenericUserMetricSynchronizationHandler<>(typeHandler.getDataTypeName() + "+", true,
				new GenericUserMetricListener<>(seconaryMetricSequencer, typeHandler));
	}

	@Override
	public Synchronizer getSynchronizer() {
		return new GenericUserMetricSynchronizer<>(typeHandler.getDataTypeName() + "-", false,
				new GenericUserMetricListener<>(seconaryMetricSequencer, typeHandler));
	}

	@Override
	public Synchronizer getIncrementalSynchronizer() {
		return new GenericUserMetricSynchronizer<>(typeHandler.getDataTypeName() + "+", true,
				new GenericUserMetricListener<>(primaryMetricSequencer, typeHandler));
	}

}
