package no.nav.nais.mot.bmot009

import org.apache.sshd.common.NamedFactory
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory
import org.apache.sshd.server.Command
import org.apache.sshd.server.SshServer
import org.apache.sshd.server.auth.UserAuth
import org.apache.sshd.server.auth.password.UserAuthPasswordFactory
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import org.apache.sshd.server.scp.ScpCommandFactory
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory

import java.nio.file.Paths
import java.util.ArrayList

class SFTPServerConfig {

    fun configure(host: String, port: Int, path: String): SshServer {
        val userAuthFactories = ArrayList<NamedFactory<UserAuth>>()
        userAuthFactories.add(UserAuthPasswordFactory())

        val sftpCommandFactory = ArrayList<NamedFactory<Command>>()
        sftpCommandFactory.add(SftpSubsystemFactory())

        val sshd = SshServer.setUpDefaultServer()
        sshd.port = port
        sshd.host = host
        sshd.keyPairProvider = SimpleGeneratorHostKeyProvider()
        sshd.commandFactory = ScpCommandFactory()
        sshd.userAuthFactories = userAuthFactories
        sshd.subsystemFactories = sftpCommandFactory
        sshd.setPasswordAuthenticator { username, password, _ ->
            if (username == "admin" && password == "admin") {
                val classLoader = SFTPServerConfig::class.java.classLoader
                   sshd.fileSystemFactory = VirtualFileSystemFactory(Paths.get(classLoader.getResource(path)!!.toURI()))
            }
            true
        }

        return sshd
    }
}