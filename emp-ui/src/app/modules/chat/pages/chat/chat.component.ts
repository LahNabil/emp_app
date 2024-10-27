import { Component } from '@angular/core';
import {ChatService} from "../../services/chat.service";
interface Message {
  text: string;
}
@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.scss'
})
export class ChatComponent {


  question: string = '';
  messages: Message[] = [];

  constructor(private chatService: ChatService) {
  }
  sendQuestion(): void {
    this.messages.push({ text: this.question});

    // Call the chat service to get the response
    this.chatService.chat(this.question).subscribe(
      (response) => {
        console.log(response);
        this.messages.push({ text: response });
      },
      (error) => {
        this.messages.push({ text: 'There was an error. Please try again.'});
      }
    );

    // Clear the input
    this.question = '';
  }
}
