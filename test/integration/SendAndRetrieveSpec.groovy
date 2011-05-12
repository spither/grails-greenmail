/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import spock.lang.*
import grails.plugin.spock.*

import javax.mail.Message.RecipientType

class SendAndRetrieveSpec extends IntegrationSpec {

	def mailService
	def greenMail
	
	def "send mail and retrieve via greenmail"() {
		when:
		mailService.sendMail {
			to "tester@test.com"
			from "grails@grails.org"
			subject "test"
			text "This is a test"
		}
		
		then:
		greenMail.latestMessage.getRecipients(RecipientType.TO)*.toString()[0] == "tester@test.com"
	}
}