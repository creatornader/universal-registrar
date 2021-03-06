<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:util="http://www.springframework.org/schema/util" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-2.0.xsd">

	<!-- UNIVERSAL REGISTRAR SERVLET -->

	<bean name="UniRegistrar" class="uniregistrar.local.LocalUniRegistrar">
		<property name="drivers">
			<util:map>
				<entry key="did:btcr"><ref bean="DidBtcrDriver" /></entry>
				<entry key="did:sov"><ref bean="DidSovDriver" /></entry>
				<entry key="did:v1"><ref bean="DidV1Driver" /></entry>
			</util:map>
		</property>
	</bean>

	<bean name="RegisterServlet" class="uniregistrar.web.servlet.RegisterServlet">
		<property name="uniRegistrar" ref="UniRegistrar" />
	</bean>

	<bean name="UpdateServlet" class="uniregistrar.web.servlet.UpdateServlet">
		<property name="uniRegistrar" ref="UniRegistrar" />
	</bean>

	<bean name="DeactivateServlet" class="uniregistrar.web.servlet.DeactivateServlet">
		<property name="uniRegistrar" ref="UniRegistrar" />
	</bean>

	<bean name="PropertiesServlet" class="uniregistrar.web.servlet.PropertiesServlet">
		<property name="uniRegistrar" ref="UniRegistrar" />
	</bean>

	<!-- DRIVERS (VIA JAVA API) -->

	<bean id="DidBtcrDriver" class="uniregistrar.driver.did.btcr.DidBtcrDriver">
		<property name="peerMainnet" value="localhost:28333" />
		<property name="peerTestnet" value="localhost:38333" />
		<property name="didDocContinuation">
			<bean class="uniregistrar.driver.did.btcr.diddoccontinuation.LocalFileDIDDocContinuation">
				<property name="basePath" value="/tmp/" />
				<property name="baseUri" value="http://localhost/" />
			</bean>
		</property>
	</bean>

	<bean id="DidSovDriver" class="uniregistrar.driver.did.sov.DidSovDriver">
		<property name="libIndyPath" value="./sovrin/lib/" />
		<property name="poolConfigs" value="_;./sovrin/live.txn;stn;./sovrin/stn.txn;danube;./sovrin/11347-05.txn" />
		<property name="poolVersions" value="_;1;stn;2;danube;2" />
		<property name="walletName" value="default" />
	</bean>

	<bean id="DidV1Driver" class="uniregistrar.driver.http.HttpDriver">
		<property name="resolveUri" value="https://testnet.veres.one/dids/" />
	</bean>

</beans>
