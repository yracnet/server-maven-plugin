/**
 * Projecto BASE:
 * https://github.com/yracnet/qualitycode-maven-plugin
 *
 */
package dev.yracnet.server.plugin;

import dev.yracnet.server.model.Service;
import dev.yracnet.server.model.Config;
import java.util.Arrays;
import java.util.Date;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

@Mojo(name = "run", defaultPhase = LifecyclePhase.TEST_COMPILE, requiresDependencyResolution = ResolutionScope.TEST)
public class RunMojo extends AbstractMojo {

    @Parameter(defaultValue = "")
    private String path;
    @Parameter
    private Config config;
    @Parameter()
    private Service[] services;
    @Parameter(defaultValue = "${basedir}/src/main/webapp")
    private String webapp;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("RUN SERVER AT " + new Date());
        if (config == null) {
            config = ServerFactory.createConfigDefault();
        }
        if (services == null || services.length == 0) {
            Service s1 = ServerFactory.createStaticServiceDefault(webapp);
            Service s2 = ServerFactory.createProxyServiceDefault();
            services = new Service[]{s1, s2};
        }
        space();
        ContextHandlerCollection contextHandler = new ContextHandlerCollection();
        Arrays.asList(services)
                .stream()
                .sorted()
                .forEach(it -> {
                    ContextHandler context = JettyFactory.createHandler(path, it);
                    contextHandler.addHandler(context);
                });
        Server server = JettyFactory.createServer(config);
        server.setHandler(contextHandler);
        try {
            server.start();
            while (server.isRunning()) {
            }
        } catch (Exception e) {
            getLog().warn(e);
        }
        space();
    }

    public void space() {
        getLog().info("========================================================================");
    }
}
