import { Component } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { QuestionResponse } from '../../../../models/QuestionResponse';

interface Message {
  text: string;
  isUser: boolean;
}

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  question: string = '';
  messages: Message[] = []; // Array to store chat history
  isLoading: boolean = false;

  constructor(private chatService: ChatService) {}

  askQuestion() {
    if (this.question.trim()) {
      // Add user's question to message history
      this.messages.push({ text: this.question, isUser: true });
      this.isLoading = true;

      // Send question to chat service and handle the response
      this.chatService.chat(this.question).subscribe({
        next: (res: QuestionResponse) => {
          // Add the chatbot's response to message history
          this.messages.push({ text: res.response ?? 'No response received.', isUser: false });
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Error:', err);
          this.messages.push({ text: 'An error occurred. Please try again.', isUser: false });
          this.isLoading = false;
        }
      });

      // Clear the input field
      this.question = '';
    }
  }
}
